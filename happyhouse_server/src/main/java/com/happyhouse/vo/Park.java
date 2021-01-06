package com.happyhouse.vo;

public class Park {
	
	String parkid;
	String parkphone;
	String parkaddr;
	String parkname;
	String parkimg;
	String parkX;
	String parkY;
	
	public Park() {
		
	}

	public Park(String parkid, String parkphone, String parkaddr, String parkname, String parkimg, String parkX,
			String parkY) {
		this.parkid = parkid;
		this.parkphone = parkphone;
		this.parkaddr = parkaddr;
		this.parkname = parkname;
		this.parkimg = parkimg;
		this.parkX = parkX;
		this.parkY = parkY;
	}

	public String getParkid() {
		return parkid;
	}

	public void setParkid(String parkid) {
		this.parkid = parkid;
	}

	public String getParkphone() {
		return parkphone;
	}

	public void setParkphone(String parkphone) {
		this.parkphone = parkphone;
	}

	public String getParkaddr() {
		return parkaddr;
	}

	public void setParkaddr(String parkaddr) {
		this.parkaddr = parkaddr;
	}

	public String getParkname() {
		return parkname;
	}

	public void setParkname(String parkname) {
		this.parkname = parkname;
	}

	public String getParkimg() {
		return parkimg;
	}

	public void setParkimg(String parkimg) {
		this.parkimg = parkimg;
	}

	public String getParkX() {
		return parkX;
	}

	public void setParkX(String parkX) {
		this.parkX = parkX;
	}

	public String getParkY() {
		return parkY;
	}

	public void setParkY(String parkY) {
		this.parkY = parkY;
	}
	
	
}
