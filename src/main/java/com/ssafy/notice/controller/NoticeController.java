package com.ssafy.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ssafy.notice.model.dto.NoticeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.notice.model.service.NoticeService;
import com.ssafy.member.model.dto.MemberDto;
import com.ssafy.util.PageNavigation;

@RestController
@RequestMapping("/notice")
public class NoticeController {
	
	private NoticeService noticeService;
	private final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}


	@GetMapping("/")
	public ResponseEntity<String> index() {
		logger.debug("-------------root");
		return new ResponseEntity<String>("hello", HttpStatus.OK);
	}
	
	@GetMapping("/write")
	public ResponseEntity write(@RequestParam Map<String, String> map) {
		logger.debug("write call parameter {}", map);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(map);
	}
	
	@PostMapping("/write")
	public ResponseEntity write(@RequestBody NoticeDto noticeDto, HttpSession session) throws Exception {
		logger.debug("boardDto = {}", noticeDto);
		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
		if (memberDto != null && isAdmin(memberDto.getUserId())) {
			// 관리자인지 확인
			noticeService.writeArticle(noticeDto);
			return ResponseEntity
					.status(HttpStatus.OK)
					.build();
		} 
		
		return ResponseEntity
				.status(HttpStatus.UNAUTHORIZED)
				.build();
		
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> list(@RequestParam Map<String, String> map) throws Exception {
		logger.debug("list parameter pgno : {}", map.get("pgno"));
		List<NoticeDto> list = noticeService.listArticle(map);
		PageNavigation pageNavigation = noticeService.makePageNavigation(map);
		Map<String, Object> body = new HashMap<>();
		body.put("articles", list);
		body.put("navigation", pageNavigation);
		body.put("pgno", map.get("pgno"));
		body.put("key", map.get("key"));
		body.put("word", map.get("word"));
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(body);
	}
	
	@GetMapping("/view")
	public ResponseEntity<?> view(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map)
			throws Exception {
		logger.debug("view articleNo : {}", articleNo);
		NoticeDto noticeDto = noticeService.getArticle(articleNo);
		noticeService.updateHit(articleNo);
		
		Map<String, Object> body = new HashMap<>();
		body.put("article", noticeDto);
		body.put("pgno", map.get("pgno"));
		body.put("key", map.get("key"));
		body.put("word", map.get("word"));

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(body);
	}
	
	@GetMapping("/modify")
	public ResponseEntity<?> modify(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map)
			throws Exception {
		logger.debug("modify articleNo : {}", articleNo);
		NoticeDto noticeDto = noticeService.getArticle(articleNo);
		Map<String, Object> body = new HashMap<>();
		body.put("article", noticeDto);
		body.put("pgno", map.get("pgno"));
		body.put("key", map.get("key"));
		body.put("word", map.get("word"));

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(body);
	}

	@PostMapping("/modify")
	public ResponseEntity<?> modify(@RequestBody NoticeDto noticeDto, @RequestParam Map<String, String> map) throws Exception {
		logger.debug("modify boardDto : {}", noticeDto);
		noticeService.modifyArticle(noticeDto);
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(map);
	}
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestParam("articleno") int articleNo, @RequestParam Map<String, String> map) throws Exception {
		logger.debug("delete articleNo : {}", articleNo);
		
		noticeService.deleteArticle(articleNo);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(map);

	}

	private boolean isAdmin(String userId) {
		if(userId.equals("admin")) {
			return true;
		}
		return false;
	}	

}
