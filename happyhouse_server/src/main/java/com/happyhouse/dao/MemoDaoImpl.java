package com.happyhouse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happyhouse.mapper.MemoMapper;
import com.happyhouse.vo.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	@Autowired
	MemoMapper mapper;
	
	@Override
	public void insert(Memo memo) {
		mapper.insert(memo);
	}

	@Override
	public List<Memo> selectAll(String boardno) {
		return mapper.selectAll(boardno);
	}

	@Override
	public void delete(String memono) {
		mapper.delete(memono);
	}

	@Override
	public void update(Memo memo) {
		mapper.update(memo);
	}
	
}
