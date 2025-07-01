package com.pcwk.ehr.board.domain;

import java.util.Date;

public class NoticeCommentDTO {
	private int commentedCode;// 댓글코드
	private int noCode;// 공지사항 코드
	private String userId;// 사용자_ID
	private String content;// 내용
	private Date cDt;// 작성일
	private Date upDt;// 수정일

	// 기본 생성자
	public NoticeCommentDTO() {
	}

	public NoticeCommentDTO(int noCode, String userId, String content) {
		super();
		this.noCode = noCode;
		this.userId = userId;
		this.content = content;
		
	}

	public int getCommentedCode() {
		return commentedCode;
	}

	public void setCommentedCode(int commentedCode) {
		this.commentedCode = commentedCode;
	}

	public int getNoCode() {
		return noCode;
	}

	public void setNoCode(int noCode) {
		this.noCode = noCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getcDt() {
		return cDt;
	}

	public void setcDt(Date cDt) {
		this.cDt = cDt;
	}

	public Date getUpDt() {
		return upDt;
	}

	public void setUpDt(Date upDt) {
		this.upDt = upDt;
	}

	@Override
	public String toString() {
		return "NoticeCommentDTO [commentedCode=" + commentedCode + ", noCode=" + noCode + ", userId=" + userId
				+ ", content=" + content + ", cDt=" + cDt + ", upDt=" + upDt + ", toString()=" + super.toString() + "]";
	}

}
