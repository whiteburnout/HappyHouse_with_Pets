package com.happyhouse.mapper;

import java.util.List;

import com.happyhouse.vo.Memo;

public interface MemoMapper {
	public void insert(Memo memo);
	public List<Memo> selectAll(String boardno);
	public void delete(String memono);
	public void update(Memo memo);
}
