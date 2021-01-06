package com.happyhouse.dao;

import java.util.List;

import com.happyhouse.vo.Memo;

public interface MemoDao {
	public void insert(Memo memo);
	public List<Memo> selectAll(String boardno);
	public void delete(String memono);
	public void update(Memo memo);
}
