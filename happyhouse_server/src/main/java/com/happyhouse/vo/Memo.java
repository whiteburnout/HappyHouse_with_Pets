package com.happyhouse.vo;

public class Memo {
	
	String memono;
	String id;
	String comment;
	String memotime;
	String boardno;
	
	public Memo() {
		super();
	}

	public Memo(String memono, String id, String comment, String memotime, String boardno) {
		super();
		this.memono = memono;
		this.id = id;
		this.comment = comment;
		this.memotime = memotime;
		this.boardno = boardno;
	}

	public String getMemono() {
		return memono;
	}

	public void setMemono(String memono) {
		this.memono = memono;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMemotime() {
		return memotime;
	}

	public void setMemotime(String memotime) {
		this.memotime = memotime;
	}

	public String getBoardno() {
		return boardno;
	}

	public void setBoardno(String boardno) {
		this.boardno = boardno;
	}
	
	
}
