package com.happyhouse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happyhouse.mapper.BoardMapper;
import com.happyhouse.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	BoardMapper mapper;
	
	@Override
	public List<Board> list(int type) {
		return mapper.selectAll(type);
	}

	@Override
	public String create(Board board) {
		return ""+mapper.insert(board);
	}

	@Override
	public Board pick(int no) {
		return mapper.selectOne(no);
	}

	@Override
	public String modify(Board board) {
		return ""+mapper.update(board);
	}

	@Override
	public String remove(int no) {
		return ""+mapper.delete(no);
	}

	@Override
	public void countUp(int no) {
		System.out.println("countup");
		mapper.countUp(no);
	}

}
