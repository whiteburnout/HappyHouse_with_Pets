package com.happyhouse.dao;

import java.util.List;

import com.happyhouse.vo.Board;

public interface BoardDao {

	List<Board> list(int type);
	String create(Board board);
	Board pick(int no);
	String modify(Board board);
	String remove(int no);
	void countUp(int no);

}
