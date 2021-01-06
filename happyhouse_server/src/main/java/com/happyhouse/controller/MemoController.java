package com.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.service.MemoService;
import com.happyhouse.vo.Memo;

@RestController
@CrossOrigin
public class MemoController {

	@Autowired
	MemoService service;
	
	//댓글 리스트
	@GetMapping(value = "/memo/{boardno}")
	public List<Memo> listMemo(@PathVariable String boardno){
		return service.listMemo(boardno);
	}
	
	//댓글 등록
	@PostMapping(value = "/memo")
	public void writeMemo(@RequestBody Memo memo){
		service.writeMemo(memo);
	}
	
	//댓글 수정
	@PutMapping(value = "/memo")
	public void modifyMemo(@RequestBody Memo memo){
		service.modifyMemo(memo);
	}
	
	//댓글 삭제
	@DeleteMapping(value = "/memo/{memono}")
	public void deleteMemo(@PathVariable String memono){
		service.deleteMemo(memono);
	}
}
