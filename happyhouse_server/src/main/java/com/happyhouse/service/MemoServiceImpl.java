package com.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyhouse.dao.MemoDao;
import com.happyhouse.vo.Memo;

@Service
public class MemoServiceImpl implements MemoService {
	
	@Autowired
	MemoDao dao;

	@Override
	public void writeMemo(Memo memo) {
		dao.insert(memo);
	}

	@Override
	public List<Memo> listMemo(String boardno) {
		return dao.selectAll(boardno);
	}

	@Override
	public void deleteMemo(String memono) {
		dao.delete(memono);
	}

	@Override
	public void modifyMemo(Memo memo) {
		dao.update(memo);
	}
	
}
