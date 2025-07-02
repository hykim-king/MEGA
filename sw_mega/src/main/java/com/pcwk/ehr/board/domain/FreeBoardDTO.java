package com.pcwk.ehr.board.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FreeBoardDTO {
	private int fbCode;//자유게시판 코드
	private String userId;//사용자_ID
	private String title;//제목
	private String content;//내용
	private int viewCount;//조회수
	private Date cDt;//작성일
	private Date upDt;//수정일
	
	//기본 생성자
	public FreeBoardDTO() {}
	
	public FreeBoardDTO(String userId, String title, String content, int viewCount, Date cDt, Date upDt) {
		super();
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.cDt = cDt;
		this.upDt = upDt;
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
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
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
		return "FreeBoardDTO [fbCode=" + fbCode + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", viewCount=" + viewCount + ", cDt=" + cDt + ", upDt=" + upDt + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
