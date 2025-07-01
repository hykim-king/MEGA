package com.pcwk.ehr.board.domain;

public class L_ReactionDTO {

	private int reactionCode;// 좋아요/싫어요 코드
	private String userId;// 사용자_ID
	private String reactionType; // L: 좋아요 / D: 싫어요
	private String targetType;// 대상 게시글 유형 : 'Notice', 'Free_Board', etc
	private int targetCode;// 대상 게시글 및 댓글 유형

//기본 생성자
	public L_ReactionDTO() {
	}

	public L_ReactionDTO(String userId, String reactionType, String targetType, int targetCode) {
		super();
		this.userId = userId;
		this.reactionType = reactionType;
		this.targetType = targetType;
		this.targetCode = targetCode;
	}

	public int getReactionCode() {
		return reactionCode;
	}

	public void setReactionCode(int reactionCode) {
		this.reactionCode = reactionCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReactionType() {
		return reactionType;
	}

	public void setReactionType(String reactionType) {
		this.reactionType = reactionType;
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
		return "L_ReactionDTO [reactionCode=" + reactionCode + ", userId=" + userId + ", reactionType=" + reactionType
				+ ", targetType=" + targetType + ", targetCode=" + targetCode + ", toString()=" + super.toString()
				+ "]";
	}

}
