package com.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyhouse.dao.BoardDao;
import com.happyhouse.dao.BoardDaoImpl;
import com.happyhouse.vo.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDao dao;
	
	@Override
	public List<Board> list(int type) {
		return dao.list(type);
	}

	@Override
	public String create(Board board) {
		return dao.create(board);
	}

	@Override
	public Board pick(int no) {
		dao.countUp(no);
		return dao.pick(no);
	}

	@Override
	public String modify(Board board) {
		return dao.modify(board);
	}

	@Override
	public String remove(int no) {
		return dao.remove(no);
	}

}
