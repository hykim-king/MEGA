package com.pcwk.ehr.mypage.domain;

public class FoodDiaryDTO {
	
	private int fdCode; //음식일지 코드 
	private String userId; //사용자 Id
	private String foodName; //음식이름
	private int grams; //섭취그람
	private String mealType; //식사시간구분
	private String regDt; //등록일

	public FoodDiaryDTO() {
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * @param fdCode
	 * @param userId
	 * @param foodName
	 * @param grams
	 * @param mealType
	 * @param regDt
	 */
	public FoodDiaryDTO(String userId, String foodName, int grams, String mealType, String regDt) {
		super();
		this.userId = userId;
		this.foodName = foodName;
		this.grams = grams;
		this.mealType = mealType;
		this.regDt = regDt;
	}



	/**
	 * @return the fdCode
	 */
	public int getFdCode() {
		return fdCode;
	}

	/**
	 * @param fdCode the fdCode to set
	 */
	public void setFdCode(int fdCode) {
		this.fdCode = fdCode;
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
	 * @return the foodName
	 */
	public String getFoodName() {
		return foodName;
	}

	/**
	 * @param foodName the foodName to set
	 */
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	/**
	 * @return the grams
	 */
	public int getGrams() {
		return grams;
	}

	/**
	 * @param grams the grams to set
	 */
	public void setGrams(int grams) {
		this.grams = grams;
	}

	/**
	 * @return the mealType
	 */
	public String getMealType() {
		return mealType;
	}

	/**
	 * @param mealType the mealType to set
	 */
	public void setMealType(String mealType) {
		this.mealType = mealType;
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
		return "FoodDiaryDTO [fdCode=" + fdCode + ", userId=" + userId + ", foodName=" + foodName + ", grams=" + grams
				+ ", mealType=" + mealType + ", regDt=" + regDt + "]";
	}
	
}
