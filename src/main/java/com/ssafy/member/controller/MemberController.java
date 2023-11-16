package com.ssafy.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.member.model.service.MemberService;

@RestController
@RequestMapping("/user")
public class MemberController {
	
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private MemberService memberService;

	private HashUtill hashUtill;
	
	public MemberController(MemberService memberService) {
		super();
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
	public ResponseEntity<?> login(@RequestParam Map<String, String> map, @RequestParam(name = "saveid", required = false) String saveid, Model model, HttpSession session, HttpServletResponse response) {
		// 비밀번호를 해싱 처리 하는 부분
		map.put("userpwd",hashUtill.Hashing(map.get("userpwd"), map.get("userId")));
		logger.debug("login map : {}", map);
		try {
			MemberDto memberDto = memberService.loginMember(map);
			if(memberDto != null) {
				session.setAttribute("userinfo", memberDto);
				
				Cookie cookie = new Cookie("ssafy_id", map.get("userid"));
//				cookie.setPath("/board");
				cookie.setPath("/");
				if("ok".equals(saveid)) {
					cookie.setMaxAge(60*60*24*365*40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				return ResponseEntity
						.status(HttpStatus.OK)
//						.body("로그인 성공 / 쿠키 확인해보기");
						.body(memberDto);
			} else {
//				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return ResponseEntity
						.status(HttpStatus.OK)
						.body("로그인 실패!! / 쿠키 확인해보기");
			}
		} catch (Exception e) {
			e.printStackTrace();
//			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("로그인 예외 발생!!");
		}
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
