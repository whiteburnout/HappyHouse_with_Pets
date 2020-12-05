package com.happyhouse.dao;

import com.happyhouse.vo.Member;

public interface MemberDao {

	public Member selectOne(Member member);
	public int update(Member member);
	public int delete(String id);
	public int insert(Member member);
	public Member check(String id);
}
