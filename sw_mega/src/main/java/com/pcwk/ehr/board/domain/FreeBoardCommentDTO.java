package com.pcwk.ehr.board.domain;

import java.util.Date;

public class FreeBoardCommentDTO {
	private int commentedCode;// 댓글 코드
	private int fbCode;// 자유게시판 코드
	private String userId;// 사용자_ID
	private String content;// 내용
	private Date cDt;// 작성일
	private Date upDt;// 수정일
	private int likeCount;
	private int dislikeCount;

	// 기본 생성자
	public FreeBoardCommentDTO() {
	}

	public FreeBoardCommentDTO(int fbCode, String userId, String content) {
		super();
		this.fbCode = fbCode;
		this.userId = userId;
		this.content = content;
	}

	public int getCommentedCode() {
		return commentedCode;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getDislikeCount() {
		return dislikeCount;
	}

	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
	}

	public void setCommentedCode(int commentedCode) {
		this.commentedCode = commentedCode;
	}

	public int getFbCode() {
		return fbCode;
	}

	public void setFbCode(int fbCode) {
		this.fbCode = fbCode;
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
		return "FreeBoardCommentDTO [commentedCode=" + commentedCode + ", fbCode=" + fbCode + ", userId=" + userId
				+ ", content=" + content + ", cDt=" + cDt + ", upDt=" + upDt + ", likeCount=" + likeCount
				+ ", dislikeCount=" + dislikeCount + ", toString()=" + super.toString() + "]";
	}

	
	

}
