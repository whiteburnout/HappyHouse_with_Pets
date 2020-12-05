package com.happyhouse.vo;

public class Member {
	int no;
	String id;
	String pass;
	String email;
	
	public Member() {
	}
	
	public Member(int no, String id, String pass, String email) {
		this.no = no;
		this.id = id;
		this.pass = pass;
		this.email = email;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
