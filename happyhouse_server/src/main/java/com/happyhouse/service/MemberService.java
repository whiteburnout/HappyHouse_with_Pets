package com.happyhouse.service;

import com.happyhouse.vo.Member;

public interface MemberService {

	public Member login(Member member);
	public void logout();
	public int modify(Member member);
	public int delete(String id);
	public Member mypage(String id);
	public int join(Member member);
	public Member check(String id);
}
