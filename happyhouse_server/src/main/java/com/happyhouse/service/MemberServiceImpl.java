package com.happyhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyhouse.dao.MemberDao;
import com.happyhouse.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDao Dao;

	@Override
	public Member login(Member member) {
		return Dao.selectOne(member);
	}
	
	@Override
	public Member userInfo(String userid) throws Exception {
		return Dao.userInfo(userid);
	}
	
	@Override
	public int join(Member member) {
		return Dao.insert(member);
	}
	@Override
	public Member check(String id) {
		return Dao.check(id);
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int modify(Member member) {
		return Dao.update(member);
	}

	@Override
	public int delete(String id) {
		return Dao.delete(id);
	}

	@Override
	public Member mypage(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
