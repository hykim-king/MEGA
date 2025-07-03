package com.pcwk.ehr.mypage.domain;

public class ExerciseDTO {

	private int eCode; //운동 코드
	private String exerciseName; //운동명
	private String exerciseType; //운동타입
	private String region; //운동부위
	private String gender; //성별
	private Integer  weight; //기준체중
	private int  calBurn; //소모칼로리
	
	//단건조회 서비스 로직 
	private int duration; //운동시간(분)
	private int strenthWeight; //덤벨 무게
	private int setCount; //세트 수 
	private int repsPerSet; //세트 당 횟수 
	private int totalCal; //총 소모 칼로리
	
	public ExerciseDTO() {
		// TODO Auto-generated constructor stub
	}

	public ExerciseDTO(String exerciseName, String exerciseType, String region, String gender, Integer weight,
			int calBurn) {
		super();
		this.exerciseName = exerciseName;
		this.exerciseType = exerciseType;
		this.region = region;
		this.gender = gender;
		this.weight = weight;
		this.calBurn = calBurn;
	}
	
	//유산소 운동 계산용
	public ExerciseDTO(String exerciseName, String exerciseType, String region, String gender,
			Integer weight, int calBurn, int duration) {
		super();
		this.exerciseName = exerciseName;
		this.exerciseType = exerciseType;
		this.region = region;
		this.gender = gender;
		this.weight = weight;
		this.calBurn = calBurn;
		this.duration = duration;
	}
	
	//근력 운동 계산용
	public ExerciseDTO(String exerciseName, String exerciseType, String region, String gender,
			Integer weight, int calBurn, int strenthWeight, int setCount, int repsPerSet) {
		super();
		this.exerciseName = exerciseName;
		this.exerciseType = exerciseType;
		this.region = region;
		this.gender = gender;
		this.weight = weight;
		this.calBurn = calBurn;
		this.strenthWeight = strenthWeight;
		this.setCount = setCount;
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
	 * @return the totalCal
	 */
	public int getTotalCal() {
		return totalCal;
	}

	/**
	 * @param totalCal the totalCal to set
	 */
	public void setTotalCal(int totalCal) {
		this.totalCal = totalCal;
	}

	/**
	 * @return the eCode
	 */
	public int geteCode() {
		return eCode;
	}

	/**
	 * @param eCode the eCode to set
	 */
	public void seteCode(int eCode) {
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
	public Integer getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Integer weight) {
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
				+ ", region=" + region + ", gender=" + gender + ", weight=" + weight + ", calBurn=" + calBurn
				+ ", duration=" + duration + ", strenthWeight=" + strenthWeight + ", setCount=" + setCount
				+ ", repsPerSet=" + repsPerSet + ", totalCal=" + totalCal + "]";
	}

	
}
