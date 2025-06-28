package com.pcwk.ehr.mypage.domain;

public class FoodDTO {
	
	private String foodName; //음식이름
	private int stGrams; //기준그람
	private int cal; //칼로리
	private int carb; //탄수화물
	private int fat; //지방
	private int prot; //단백질
	private int na; //나트륨
	
	public FoodDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param foodName
	 * @param stGrams
	 * @param cal
	 * @param carb
	 * @param fat
	 * @param prot
	 * @param na
	 */
	public FoodDTO(String foodName, int stGrams, int cal, int carb, int fat, int prot, int na) {
		super();
		this.foodName = foodName;
		this.stGrams = stGrams;
		this.cal = cal;
		this.carb = carb;
		this.fat = fat;
		this.prot = prot;
		this.na = na;
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
	 * @return the stGrams
	 */
	public int getStGrams() {
		return stGrams;
	}

	/**
	 * @param stGrams the stGrams to set
	 */
	public void setStGrams(int stGrams) {
		this.stGrams = stGrams;
	}

	/**
	 * @return the cal
	 */
	public int getCal() {
		return cal;
	}

	/**
	 * @param cal the cal to set
	 */
	public void setCal(int cal) {
		this.cal = cal;
	}

	/**
	 * @return the carb
	 */
	public int getCarb() {
		return carb;
	}

	/**
	 * @param carb the carb to set
	 */
	public void setCarb(int carb) {
		this.carb = carb;
	}

	/**
	 * @return the fat
	 */
	public int getFat() {
		return fat;
	}

	/**
	 * @param fat the fat to set
	 */
	public void setFat(int fat) {
		this.fat = fat;
	}

	/**
	 * @return the prot
	 */
	public int getProt() {
		return prot;
	}

	/**
	 * @param prot the prot to set
	 */
	public void setProt(int prot) {
		this.prot = prot;
	}

	/**
	 * @return the na
	 */
	public int getNa() {
		return na;
	}

	/**
	 * @param na the na to set
	 */
	public void setNa(int na) {
		this.na = na;
	}

	@Override
	public String toString() {
		return "FoodDTO [foodName=" + foodName + ", stGrams=" + stGrams + ", cal=" + cal + ", carb=" + carb + ", fat="
				+ fat + ", prot=" + prot + ", na=" + na + "]";
	}


}
