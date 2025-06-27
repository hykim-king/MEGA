/**
 * Package Name : com.pcwk.ehr.mypage.domain <br/>
 * Class Name: FoodDiaryDTO.java <br/>
 * Description:  <br/>
 * Modification imformation : <br/> 
 * ------------------------------------------<br/>
 * 최초 생성일 : 2025-06-25<br/>
 *
 * ------------------------------------------<br/>
 * @author :PC
 * @since  :2024-09-09
 * @version: 0.5
 */
package com.pcwk.ehr.mypage.domain;


public class FoodDiaryDTO {

	private String userId; //사용자 Id
	private String foodName; //음식이름
	private int grams; //섭취그람
	private String mealTime; //식사시간구분
	private String regDt; //등록일
	
	
	public FoodDiaryDTO() {
	}


	/**
	 * @param userId
	 * @param foodName
	 * @param grams
	 * @param mealTime
	 * @param regDt
	 */
	public FoodDiaryDTO(String userId, String foodName, int grams, String mealTime, String regDt) {
		super();
		this.userId = userId;
		this.foodName = foodName;
		this.grams = grams;
		this.mealTime = mealTime;
		this.regDt = regDt;
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
	 * @return the mealTime
	 */
	public String getMealTime() {
		return mealTime;
	}


	/**
	 * @param mealTime the mealTime to set
	 */
	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
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
		return "FoodDiaryDTO [userId=" + userId + ", foodName=" + foodName + ", grams=" + grams + ", mealTime="
				+ mealTime + ", regDt=" + regDt + "]";
	}
	
}
