package com.pcwk.ehr.mypage.domain;

public class FoodDTO {
	
	private String foodName; //음식이름
	private int stGrams; //기준그람
	private int cal; //칼로리
	private int carb; //탄수화물
	private int fat; //지방
	private int prot; //단백질
	private int na; //나트륨
	
	// 사용자가 입력한 실제 섭취량
	private int grams;

	// 계산된 결과
	private int totalCal;
	private int totalCarb;
	private int totalFat;
	private int totalProt;
	private int totalNa;
	
	private int totalCnt; //총글수 
	private String userId;
	
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
	 * @param foodName
	 * @param stGrams
	 * @param cal
	 * @param carb
	 * @param fat
	 * @param prot
	 * @param na
	 * @param grams
	 */
	public FoodDTO(String foodName, int stGrams, int cal, int carb, int fat, int prot, int na, int grams) {
		super();
		this.foodName = foodName;
		this.stGrams = stGrams;
		this.cal = cal;
		this.carb = carb;
		this.fat = fat;
		this.prot = prot;
		this.na = na;
		this.grams = grams;
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
	 * @return the totalCnt
	 */
	public int getTotalCnt() {
		return totalCnt;
	}

	/**
	 * @param totalCnt the totalCnt to set
	 */
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	/**
	 * @return the totalNa
	 */
	public int getTotalNa() {
		return totalNa;
	}

	/**
	 * @param totalNa the totalNa to set
	 */
	public void setTotalNa(int totalNa) {
		this.totalNa = totalNa;
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
	 * @return the totalCarb
	 */
	public int getTotalCarb() {
		return totalCarb;
	}

	/**
	 * @param totalCarb the totalCarb to set
	 */
	public void setTotalCarb(int totalCarb) {
		this.totalCarb = totalCarb;
	}

	/**
	 * @return the totalFat
	 */
	public int getTotalFat() {
		return totalFat;
	}

	/**
	 * @param totalFat the totalFat to set
	 */
	public void setTotalFat(int totalFat) {
		this.totalFat = totalFat;
	}

	/**
	 * @return the totalProt
	 */
	public int getTotalProt() {
		return totalProt;
	}

	/**
	 * @param totalProt the totalProt to set
	 */
	public void setTotalProt(int totalProt) {
		this.totalProt = totalProt;
	}

	@Override
	public String toString() {
		return "FoodDTO [foodName=" + foodName + ", stGrams=" + stGrams + ", cal=" + cal + ", carb=" + carb + ", fat="
				+ fat + ", prot=" + prot + ", na=" + na + ", grams=" + grams + ", totalCal=" + totalCal + ", totalCarb="
				+ totalCarb + ", totalFat=" + totalFat + ", totalProt=" + totalProt + ", totalNa=" + totalNa
				+ ", totalCnt=" + totalCnt + ", userId=" + userId + "]";
	}

	
}
