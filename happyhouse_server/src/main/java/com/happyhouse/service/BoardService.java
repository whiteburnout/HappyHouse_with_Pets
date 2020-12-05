package com.happyhouse.service;

import java.util.List;

import com.happyhouse.vo.Board;

public interface BoardService {

	List<Board> list(int i);
	String create(Board board);
	Board pick(int no);
	String modify(Board board);
	String remove(int no);

}
