package com.happyhouse.vo;

public class Review {

	String no;
	String id;
	String title;
	String content;
	String wdate;
	String houseno;
	String rating;
	
	public Review() {
	}

	public Review(String no, String id, String title, String content, String wdate, String houseno, String rating) {
		this.no = no;
		this.id = id;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.houseno = houseno;
		this.rating = rating;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public String getHouseno() {
		return houseno;
	}

	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	
	
}
