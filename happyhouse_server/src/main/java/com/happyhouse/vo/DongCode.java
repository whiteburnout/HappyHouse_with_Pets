package com.happyhouse.vo;

public class DongCode {
	String dongcode;
	String city;
	String gugun;
	String dong;
	public DongCode() {
		super();
	}
	public DongCode(String dongcode, String city, String gugun, String dong) {
		super();
		this.dongcode = dongcode;
		this.city = city;
		this.gugun = gugun;
		this.dong = dong;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	
	
}
