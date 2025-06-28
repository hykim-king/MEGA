package com.pcwk.ehr.mypage.domain;

public class ExerciseDTO {

	private String eCode; //운동 코드
	private String exerciseName; //운동명
	private String exerciseType; //운동타입
	private String region; //운동부위
	private String gender; //성별
	private int weight; //기준체중
	private int calBurn; //소모칼로리
	
	public ExerciseDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param eCode
	 * @param exerciseName
	 * @param exerciseType
	 * @param region
	 * @param gender
	 * @param weight
	 * @param calBurn
	 */
	public ExerciseDTO(String eCode, String exerciseName, String exerciseType, String region, String gender, int weight,
			int calBurn) {
		super();
		this.eCode = eCode;
		this.exerciseName = exerciseName;
		this.exerciseType = exerciseType;
		this.region = region;
		this.gender = gender;
		this.weight = weight;
		this.calBurn = calBurn;
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
	 * @return the exerciseType
	 */
	public String getExerciseType() {
		return exerciseType;
	}

	/**
	 * @param exerciseType the exerciseType to set
	 */
	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
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
	 * @return the calBurn
	 */
	public int getCalBurn() {
		return calBurn;
	}

	/**
	 * @param calBurn the calBurn to set
	 */
	public void setCalBurn(int calBurn) {
		this.calBurn = calBurn;
	}

	@Override
	public String toString() {
		return "ExerciseDTO [eCode=" + eCode + ", exerciseName=" + exerciseName + ", exerciseType=" + exerciseType
				+ ", region=" + region + ", gender=" + gender + ", weight=" + weight + ", calBurn=" + calBurn + "]";
	}
	
}
