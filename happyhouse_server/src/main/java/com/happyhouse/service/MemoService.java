package com.happyhouse.service;

import java.util.List;

import com.happyhouse.vo.Memo;

public interface MemoService {
	public void writeMemo(Memo memo);
	public List<Memo> listMemo(String boardno);
	public void deleteMemo(String memono);
	public void modifyMemo(Memo memo);
}
