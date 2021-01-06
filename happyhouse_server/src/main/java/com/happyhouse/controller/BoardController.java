package com.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.service.BoardService;
import com.happyhouse.vo.Board;

@RestController
@CrossOrigin
public class BoardController {

	@Autowired
	BoardService service;
	
	//리스트 조회
	@GetMapping(value = "/board/{type}")
	public List<Board> list(@PathVariable String type)
	{
		System.out.println("BoardController[list] : " + type);
		List<Board> li = null;
		if(type.equals("notice")) {
			li = service.list(0);
		} else if(type.equals("general")) {
			li = service.list(1);
		}
		System.out.println("result : " + li.size());
		return li;
	}
	//조회수 증가
	@GetMapping(value = "/board/item/{no}")
	public Board pick(@PathVariable int no)
	{
		Board b = service.pick(no);
		return b;
	}
	
	//글생성
	@PostMapping(value="/board")
	public Map<String, String> create(@RequestBody Board board)
	{	
		Map<String, String> result = new HashMap<>();
		result.put("result", service.create(board));
		return result;
	}
	
	//글수정
	@PutMapping(value="/board")
	public Map<String, String> modify(@RequestBody Board board)
	{
		Map<String, String> result = new HashMap<>();
		result.put("result", service.modify(board));
		return result;
	}
	
	//글삭제
	@DeleteMapping(value="/board/{no}")
	public Map<String, String> delete(@PathVariable int no)
	{
		Map<String, String> result = new HashMap<>();
		result.put("result", service.remove(no));
		return result;
	}
}
