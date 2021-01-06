package com.happyhouse.mapper;

import java.sql.SQLException;

import com.happyhouse.vo.Member;

public interface MemberMapper {

	public Member selectOne(Member member);
	public Member userInfo(String userid);
	public int update(Member member);
	public int delete(String id);
	public int insert(Member member);
	public Member check(String id);
}
