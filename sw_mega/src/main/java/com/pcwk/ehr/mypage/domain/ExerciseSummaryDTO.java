package com.pcwk.ehr.mypage.domain;

public class ExerciseSummaryDTO {
	
	 private String userId;
	 private String regDt;
	 private int totalDuration;
	 private int totalCalories;
	 
	 public ExerciseSummaryDTO() {
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the regDt
	 */
	public String getRegDt() {
		return regDt;
	}

	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	/**
	 * @return the totalDuration
	 */
	public int getTotalDuration() {
		return totalDuration;
	}

	/**
	 * @param totalDuration the totalDuration to set
	 */
	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	/**
	 * @return the totalCalories
	 */
	public int getTotalCalories() {
		return totalCalories;
	}

	/**
	 * @param totalCalories the totalCalories to set
	 */
	public void setTotalCalories(int totalCalories) {
		this.totalCalories = totalCalories;
	}

	@Override
	public String toString() {
		return "ExerciseSummaryDTO [userId=" + userId + ", regDt=" + regDt + ", totalDuration=" + totalDuration
				+ ", totalCalories=" + totalCalories + "]";
	}

}
