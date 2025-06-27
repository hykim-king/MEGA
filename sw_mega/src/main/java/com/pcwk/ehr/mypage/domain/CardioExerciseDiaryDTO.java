/**
 * Package Name : com.pcwk.ehr.mypage.domain <br/>
 * Class Name: CardioExerciseDTO.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-26<br/>
 *
 * ------------------------------------------<br/>
 * @author :user
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.mypage.domain;

public class CardioExerciseDiaryDTO {
	
	private String edCode; //운동일지 코드
	private String userId; //사용자Id
	private String eCode; //운동 코드
	private String exerciseName; //운동명
	private String gender; //성별
	private int cardioWeight; //체중
	private int duration; //운동시간
	private String regDt; //등록일
	
	public CardioExerciseDiaryDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param edCode
	 * @param userId
	 * @param eCode
	 * @param exerciseName
	 * @param gender
	 * @param cardioWeight
	 * @param duration
	 * @param regDt
	 */
	public CardioExerciseDiaryDTO(String edCode, String userId, String eCode, String exerciseName, String gender,
			int cardioWeight, int duration, String regDt) {
		super();
		this.edCode = edCode;
		this.userId = userId;
		this.eCode = eCode;
		this.exerciseName = exerciseName;
		this.gender = gender;
		this.cardioWeight = cardioWeight;
		this.duration = duration;
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
	 * @return the exerciseName
	 */
	public String getExerciseName() {
		return exerciseName;
	}

	/**
	 * @param exerciseName the exerciseName to set
	 */
	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the cardioWeight
	 */
	public int getCardioWeight() {
		return cardioWeight;
	}

	/**
	 * @param cardioWeight the cardioWeight to set
	 */
	public void setCardioWeight(int cardioWeight) {
		this.cardioWeight = cardioWeight;
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
		return "CardioExerciseDiaryDTO [edCode=" + edCode + ", userId=" + userId + ", eCode=" + eCode
				+ ", exerciseName=" + exerciseName + ", gender=" + gender + ", cardioWeight=" + cardioWeight
				+ ", duration=" + duration + ", regDt=" + regDt + "]";
	}

}
