/**
 * Package Name : com.pcwk.ehr.mypage.domain <br/>
 * Class Name: ExerciseInputDTO.java <br/>
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

public class ExerciseInputDTO {
	
	private String exerciseName; //운동명
	
	//유산소 운동용
	private String gender; //성별
	private int weight; //사용자 체중
	private int duration; //운동시간
	
	//근력 운동용
	private String region; //운동부위
	private int strenthWeight; //덤벨 무게
	private int setCount; //세트 수
	private int repsPerSet; //세트 당 횟수
	

	public ExerciseInputDTO() {
	}


	/**
	 * @param exerciseName
	 * @param gender
	 * @param weight
	 * @param duration
	 * @param region
	 * @param strenthWeight
	 * @param setCount
	 * @param repsPerSet
	 */
	public ExerciseInputDTO(String exerciseName, String gender, int weight, int duration, String region,
			int strenthWeight, int setCount, int repsPerSet) {
		super();
		this.exerciseName = exerciseName;
		this.gender = gender;
		this.weight = weight;
		this.duration = duration;
		this.region = region;
		this.strenthWeight = strenthWeight;
		this.setCount = setCount;
		this.repsPerSet = repsPerSet;
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
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}


	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
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


	@Override
	public String toString() {
		return "ExerciseInputDTO [exerciseName=" + exerciseName + ", gender=" + gender + ", weight=" + weight
				+ ", duration=" + duration + ", region=" + region + ", strenthWeight=" + strenthWeight + ", setCount="
				+ setCount + ", repsPerSet=" + repsPerSet + "]";
	}
	
}
