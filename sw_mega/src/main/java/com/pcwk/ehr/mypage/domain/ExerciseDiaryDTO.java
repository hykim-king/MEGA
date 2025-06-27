/**
 * Package Name : com.pcwk.ehr.mypage.domain <br/>
 * Class Name: ExerciseDiaryDTO.java <br/>
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


public class ExerciseDiaryDTO {
	
	private String edCode; //운동일지 코드
	private String userId; //사용자Id
	private String eCode; //운동 코드
	private String exerciseName; //운동명
	private String region; //운동부위
	private String gender; //성별
	private int cardioWeight; //체중
	private int strenthWeight; //덤벨 무게
	private int setCount; //세트 수
	private int repsPerSet; //세트 당 횟수
	private int duration; //운동시간
	private int calBurned; //소모 칼로리
	private String regDt; //등록일
	

	public ExerciseDiaryDTO() {
	}


	/**
	 * @param edCode
	 * @param userId
	 * @param eCode
	 * @param exerciseName
	 * @param region
	 * @param gender
	 * @param cardioWeight
	 * @param strenthWeight
	 * @param setCount
	 * @param repsPerSet
	 * @param duration
	 * @param calBurned
	 * @param regDt
	 */
	public ExerciseDiaryDTO(String edCode, String userId, String eCode, String exerciseName, String region,
			String gender, int cardioWeight, int strenthWeight, int setCount, int repsPerSet, int duration,
			int calBurned, String regDt) {
		super();
		this.edCode = edCode;
		this.userId = userId;
		this.eCode = eCode;
		this.exerciseName = exerciseName;
		this.region = region;
		this.gender = gender;
		this.cardioWeight = cardioWeight;
		this.strenthWeight = strenthWeight;
		this.setCount = setCount;
		this.repsPerSet = repsPerSet;
		this.duration = duration;
		this.calBurned = calBurned;
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
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}


	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
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
	 * @return the strenthWeight
	 */
	public int getStrenthWeight() {
		return strenthWeight;
	}


	/**
	 * @param strenthWeight the strenthWeight to set
	 */
	public void setStrenthWeight(int strenthWeight) {
		this.strenthWeight = strenthWeight;
	}


	/**
	 * @return the setCount
	 */
	public int getSetCount() {
		return setCount;
	}


	/**
	 * @param setCount the setCount to set
	 */
	public void setSetCount(int setCount) {
		this.setCount = setCount;
	}


	/**
	 * @return the repsPerSet
	 */
	public int getRepsPerSet() {
		return repsPerSet;
	}


	/**
	 * @param repsPerSet the repsPerSet to set
	 */
	public void setRepsPerSet(int repsPerSet) {
		this.repsPerSet = repsPerSet;
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
	 * @return the calBurned
	 */
	public int getCalBurned() {
		return calBurned;
	}


	/**
	 * @param calBurned the calBurned to set
	 */
	public void setCalBurned(int calBurned) {
		this.calBurned = calBurned;
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
		return "ExerciseDiaryDTO [edCode=" + edCode + ", userId=" + userId + ", eCode=" + eCode + ", exerciseName="
				+ exerciseName + ", region=" + region + ", gender=" + gender + ", cardioWeight=" + cardioWeight
				+ ", strenthWeight=" + strenthWeight + ", setCount=" + setCount + ", repsPerSet=" + repsPerSet
				+ ", duration=" + duration + ", calBurned=" + calBurned + ", regDt=" + regDt + "]";
	}
	
}
