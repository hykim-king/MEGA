package com.pcwk.ehr.mypage.domain;

public class ExerciseDiaryDTO {
	
	private String edCode; //운동일지 코드
	private String userId; //사용자Id
	private String eCode; //운동 코드
	private Integer cardioWeight; //체중
	private Integer strenthWeight; //덤벨 무게
	private int duration; //운동시간
	private Integer setCount; //세트 수
	private Integer repsPerSet; //세트 당 횟수
	private String regDt; //등록일
	
	public ExerciseDiaryDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param edCode
	 * @param userId
	 * @param eCode
	 * @param cardioWeight
	 * @param strenthWeight
	 * @param duration
	 * @param setCount
	 * @param repsPerSet
	 * @param regDt
	 */
	public ExerciseDiaryDTO(String userId, String eCode, Integer cardioWeight, Integer strenthWeight,
			int duration, Integer setCount, Integer repsPerSet, String regDt) {
		super();
		this.userId = userId;
		this.eCode = eCode;
		this.cardioWeight = cardioWeight;
		this.strenthWeight = strenthWeight;
		this.duration = duration;
		this.setCount = setCount;
		this.repsPerSet = repsPerSet;
		this.regDt = regDt;
	}

	/**
	 * @return the edCode
	 */
	public String getEdCode() {
		return edCode;
	}

	/**
	 * @param edCode the edCode to set
	 */
	public void setEdCode(String edCode) {
		this.edCode = edCode;
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
	 * @return the eCode
	 */
	public String geteCode() {
		return eCode;
	}

	/**
	 * @param eCode the eCode to set
	 */
	public void seteCode(String eCode) {
		this.eCode = eCode;
	}

	/**
	 * @return the cardioWeight
	 */
	public Integer getCardioWeight() {
		return cardioWeight;
	}

	/**
	 * @param cardioWeight the cardioWeight to set
	 */
	public void setCardioWeight(Integer cardioWeight) {
		this.cardioWeight = cardioWeight;
	}

	/**
	 * @return the strenthWeight
	 */
	public Integer getStrenthWeight() {
		return strenthWeight;
	}

	/**
	 * @param strenthWeight the strenthWeight to set
	 */
	public void setStrenthWeight(Integer strenthWeight) {
		this.strenthWeight = strenthWeight;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the setCount
	 */
	public Integer getSetCount() {
		return setCount;
	}

	/**
	 * @param setCount the setCount to set
	 */
	public void setSetCount(Integer setCount) {
		this.setCount = setCount;
	}

	/**
	 * @return the repsPerSet
	 */
	public Integer getRepsPerSet() {
		return repsPerSet;
	}

	/**
	 * @param repsPerSet the repsPerSet to set
	 */
	public void setRepsPerSet(Integer repsPerSet) {
		this.repsPerSet = repsPerSet;
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

	@Override
	public String toString() {
		return "ExerciseDiaryDTO [edCode=" + edCode + ", userId=" + userId + ", eCode=" + eCode + ", cardioWeight="
				+ cardioWeight + ", strenthWeight=" + strenthWeight + ", duration=" + duration + ", setCount="
				+ setCount + ", repsPerSet=" + repsPerSet + ", regDt=" + regDt + "]";
	}

}
