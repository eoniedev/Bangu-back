package com.ssafy.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.util.HashUtill;
import com.ssafy.util.JWTUtil;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private MemberService memberService;
	private JWTUtil jwtUtil;
	private HashUtill hashUtill;
	
	public MemberController(MemberService memberService, JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
		this.memberService = memberService;
		hashUtill = HashUtill.getInstance();
	}
	
	// GET 매핑
	@GetMapping("/{userid}")
	public ResponseEntity<?> idCheck(@PathVariable("userid") String userId) throws Exception {
		logger.debug("idCheck userid : {}", userId);
		int cnt = memberService.idCheck(userId);
		String result = cnt == 1 ? "idCheck 성공" : "idCheck 실패";
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(result);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		// 만약 session에 getAttribute("userinfo") 값이 있으면 
		// 세션 지워주고 true 반환하고 없으면 false반환? 근데 이게 필요있나?
		// Vue입장에서도 true면 다시 리다이렉트 해줘야할 듯
		session.invalidate();
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(true);
	}
	
	/*
	 * @GetMapping("/list") public String list() { return
	 * "redirect:/assets/list.html"; }
	 */
	
	// POST 매핑
	@PostMapping("/join")
	public ResponseEntity<?> join(MemberDto memberDto, Model model) {
		logger.debug("memberDto info : {}", memberDto);
		// 비밀번호 해싱 처리
		String hashedPassword = hashUtill.Hashing(memberDto.getUserPwd(), memberDto.getUserId());
		memberDto.setUserPwd(hashedPassword);
		try {
			memberService.joinMember(memberDto);
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("회원가입 성공");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("회원가입실패");
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		logger.debug("login user : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberDto.setUserPwd(hashUtill.Hashing(memberDto.getUserPwd(), memberDto.getUserId()));
			MemberDto loginUser = memberService.login(memberDto);
			if(loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				logger.debug("access token : {}", accessToken);
				logger.debug("refresh token : {}", refreshToken);
				
//				발급받은 refresh token을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);
				
//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			} 
			
		} catch (Exception e) {
			logger.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.getMember(userId);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable ("userId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		logger.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	@PostMapping("/adminUserList")
	public ResponseEntity<?> adminUserList(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		MemberDto user = (MemberDto) session.getAttribute("userinfo");
		String userId = null;
		if(user != null) {
			userId = user.getUserId();
		}
		
//		if(user.getUserId().equals("admin")) {
		if("admin".equals(userId)) {
			// DB에서 유저 정보 얻어오기
			try {
				List<MemberDto> users = memberService.listMember(new HashMap<String, String>());
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(users);
			}catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity
						.status(HttpStatus.OK)
						.body("/adminUserList 오류 발생 {MemberController.java}");
			}
			
		}else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("admin이 아니라서 요청 거부");
		}
	}
	
	@PostMapping("/modify")
	public ResponseEntity<?> modify(MemberDto memberDto, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDto user = (MemberDto) session.getAttribute("userinfo");
		String userId = null;
		// 비밀번호 해싱 처리
		String hashedPassword = hashUtill.Hashing(memberDto.getUserPwd(), memberDto.getUserId());
		memberDto.setUserPwd(hashedPassword);
		if(user != null) {
			userId = user.getUserId();
		}
		
		if("admin".equals(userId) || memberDto.getUserId().equals(userId)) {
			// DB에서 유저 정보 얻어오기
			try {
				memberService.updateMember(memberDto);
				return ResponseEntity
						.status(HttpStatus.OK)
						.body("글 수정 완료");
			}catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity
						.status(HttpStatus.OK)
						.body("글작성 오류");
			}
			
		}else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("유저를 수정할 권한이 없습니다.");
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> delete(String idToDel, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDto user = (MemberDto) session.getAttribute("userinfo");
		String userId = null;
		if(user != null) {
			userId = user.getUserId();
		}
		
		if("admin".equals(userId) || idToDel.equals(userId)) {
			// DB에서 유저 정보 얻어오기
			try {
				memberService.deleteMember(idToDel);
				return ResponseEntity
						.status(HttpStatus.OK)
						.body("유저 삭제 완료");
			}catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity
						.status(HttpStatus.OK)
						.body("유저 삭제 예외 뜸");
			}
			
		}else {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("유저를 삭제할 권한이 없습니다.");
		}
	}


}
