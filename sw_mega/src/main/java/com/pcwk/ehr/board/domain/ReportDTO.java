package com.pcwk.ehr.board.domain;

public class ReportDTO {

	private int reportCode;// 신고코드
	private String userId;// 사용자_ID
	private String reason;// 신고 사유
	private String targetType; // 대상 게시글 유형 : NOTICE, FREE_BOARD, etc.
	private int targetCode; // 대상 게시글 및 댓글 유형

	// 기본 생성자
	public ReportDTO() {
	}

	public ReportDTO(int reportCode, String userId, String reason, String targetType, int targetCode) {
		super();
		this.reportCode = reportCode;
		this.userId = userId;
		this.reason = reason;
		this.targetType = targetType;
		this.targetCode = targetCode;
	}

	public int getReportCode() {
		return reportCode;
	}

	public void setReportCode(int reportCode) {
		this.reportCode = reportCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public int getTargetCode() {
		return targetCode;
	}

	public void setTargetCode(int targetCode) {
		this.targetCode = targetCode;
	}

	@Override
	public String toString() {
		return "ReportDTO [reportCode=" + reportCode + ", userId=" + userId + ", reason=" + reason + ", targetType="
				+ targetType + ", targetCode=" + targetCode + ", toString()=" + super.toString() + "]";
	}

}
