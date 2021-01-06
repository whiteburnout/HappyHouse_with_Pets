package com.happyhouse.vo;

public class Pharmacy {
	
	String pharid;
	String pharphone;
	String pharaddr;
	String pharname;
	String pharX;
	String pharY;
	public Pharmacy() {
		
	}
	
	public Pharmacy(String pharid, String pharphone, String pharaddr, String pharname, String pharX, String pharY) {
		this.pharid = pharid;
		this.pharphone = pharphone;
		this.pharaddr = pharaddr;
		this.pharname = pharname;
		this.pharX = pharX;
		this.pharY = pharY;
	}

	public String getPharid() {
		return pharid;
	}

	public void setPharid(String pharid) {
		this.pharid = pharid;
	}

	public String getPharphone() {
		return pharphone;
	}

	public void setPharphone(String pharphone) {
		this.pharphone = pharphone;
	}

	public String getPharaddr() {
		return pharaddr;
	}

	public void setPharaddr(String pharaddr) {
		this.pharaddr = pharaddr;
	}

	public String getPharname() {
		return pharname;
	}

	public void setPharname(String pharname) {
		this.pharname = pharname;
	}

	public String getPharX() {
		return pharX;
	}

	public void setPharX(String pharX) {
		this.pharX = pharX;
	}

	public String getPharY() {
		return pharY;
	}

	public void setPharY(String pharY) {
		this.pharY = pharY;
	}
	
	
	
}
