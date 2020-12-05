package com.happyhouse.mapper;

import java.util.List;

import com.happyhouse.vo.Board;

public interface BoardMapper {

	List<Board> selectAll(int type);
	int insert(Board board);
	Board selectOne(int no);
	int update(Board board);
	int delete(int no);
	void countUp(int no);

}
