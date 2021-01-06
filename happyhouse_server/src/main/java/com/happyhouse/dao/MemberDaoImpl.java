package com.happyhouse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.happyhouse.mapper.MemberMapper;
import com.happyhouse.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	MemberMapper mapper;

	//login, mypage
	@Override
	public Member selectOne(Member member) {
		
		return mapper.selectOne(member);
	}
	
	//token check
	@Override
	public Member userInfo(String userid) {
		
		return mapper.userInfo(userid);
	}
	
	//idcheck
	@Override
	public Member check(String id) {
		
		return mapper.check(id);
	}

	//modify
	@Override
	public int update(Member member) {
		
		return mapper.update(member);
	}

	//delete
	@Override
	public int delete(String id) {
		
		return mapper.delete(id);
	}

	@Override
	public int insert(Member member) {
		
		return mapper.insert(member);
	}
}
