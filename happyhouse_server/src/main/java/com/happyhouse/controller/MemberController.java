package com.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.service.MemberService;
import com.happyhouse.vo.Member;
import com.happyhouse.service.JwtService;

//http://localhost/user/
@RestController
@CrossOrigin
public class MemberController {
	
	@Autowired
	private JwtService jwtService;
	
	
	@Autowired
	MemberService service;
	
	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
	//로그인
	@PostMapping(value="/user")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Member member)
	{
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			Member loginUser = service.login(member);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/user/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				Member memberDto = service.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", "FAIL");
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	//회원가입
	@PostMapping(value="/user/join")
	public String join(@RequestBody Member member)
	{
		service.join(member);
		return null;
	}
	
	//아이디 중복검사
	@GetMapping(value="/user/check/{id}")
	public Member idcheck(@PathVariable String id)
	{
		return service.check(id);
	}
	
//	//로그아웃
//	@GetMapping(value="/user")
//	public String logout()
//	{
//		service.logout();
//		return null;
//	}
	
	//회원 정보 수정
	@PutMapping(value="/user")
	public Member modify(@RequestBody Member member)
	{
		System.out.println("수정");
		service.modify(member);
		return member;
	}
	
	//회원 정보 삭제
	@DeleteMapping(value="/user/{id}")
	public String delete(@PathVariable String id)
	{
		service.delete(id);
		return null;
	}
	
	//회원 정보 조회(마이페이지)
//	@GetMapping(value="/user/info")
//	public ResponseEntity<Map<String, Object>> mypage(HttpServletRequest req)
//	{
//		System.out.println("info");
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = HttpStatus.ACCEPTED;
//		System.out.println(">>>>>> " + jwtService.get(req.getHeader("auth-token")));
//		try {
//			// 사용자에게 전달할 정보이다.
////			String info = memberService.getServerInfo();
//			
//			resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
////
////			resultMap.put("status", true);
////			resultMap.put("info", info);
//			status = HttpStatus.ACCEPTED;
//		} catch (RuntimeException e) {
//			logger.error("정보조회 실패 : {}", e);
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		return new ResponseEntity<Map<String, Object>>(resultMap, status);
//	}
}
