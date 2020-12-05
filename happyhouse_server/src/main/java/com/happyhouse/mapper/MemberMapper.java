package com.happyhouse.mapper;

import com.happyhouse.vo.Member;

public interface MemberMapper {

	public Member selectOne(Member member);
	public int update(Member member);
	public int delete(String id);
	public int insert(Member member);
	public Member check(String id);
}
