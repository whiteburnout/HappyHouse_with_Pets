package com.happyhouse.vo;

public class Hospital {
	String hosid;
	String hosphone;
	String hosaddr;
	String hosname;
	String hosX;
	String hosY;
	
	public Hospital() {
		
	}

	public Hospital(String hosid, String hosphone, String hosaddr, String hosname, String hosX, String hosY) {
		this.hosid = hosid;
		this.hosphone = hosphone;
		this.hosaddr = hosaddr;
		this.hosname = hosname;
		this.hosX = hosX;
		this.hosY = hosY;
	}

	public String getHosid() {
		return hosid;
	}

	public void setHosid(String hosid) {
		this.hosid = hosid;
	}

	public String getHosphone() {
		return hosphone;
	}

	public void setHosphone(String hosphone) {
		this.hosphone = hosphone;
	}

	public String getHosaddr() {
		return hosaddr;
	}

	public void setHosaddr(String hosaddr) {
		this.hosaddr = hosaddr;
	}

	public String getHosname() {
		return hosname;
	}

	public void setHosname(String hosname) {
		this.hosname = hosname;
	}

	public String getHosX() {
		return hosX;
	}

	public void setHosX(String hosX) {
		this.hosX = hosX;
	}

	public String getHosY() {
		return hosY;
	}

	public void setHosY(String hosY) {
		this.hosY = hosY;
	}
	
	
	
}
