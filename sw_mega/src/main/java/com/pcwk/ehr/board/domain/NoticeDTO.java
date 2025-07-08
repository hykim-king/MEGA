package com.pcwk.ehr.board.domain;

import org.springframework.stereotype.Component;
import com.pcwk.ehr.cmn.DTO;

@Component
public class NoticeDTO extends DTO {
	private int noCode;       // 공지사항 코드
	private String userId;    // 사용자_ID
	private String title;     // 제목
	private String content;   // 내용
	private int viewCount;    // 조회수
	private String cDt;       // 작성일 (수정)
	private String upDt;      // 수정일 (수정)

	// 기본 생성자
	public NoticeDTO() {}

	public NoticeDTO(String userId, String title, String content, int viewCount, String cDt, String upDt) {
		super();
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.cDt = cDt;
		this.upDt = upDt;
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

	public String getcDt() {
		return cDt;
	}

	public void setcDt(String cDt) {
		this.cDt = cDt;
	}

	public String getUpDt() {
		return upDt;
	}

	public void setUpDt(String upDt) {
		this.upDt = upDt;
	}

	@Override
	public String toString() {
		return "NoticeDTO [noCode=" + noCode + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", viewCount=" + viewCount + ", cDt=" + cDt + ", upDt=" + upDt + ", toString()=" + super.toString()
				+ "]";
	}
}
