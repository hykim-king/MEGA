package com.pcwk.ehr.mypage.domain;

public class FoodDiaryOutDTO {
	
	private int fdCode; //음식일지 코드 
	private String userId; //사용자 Id
	private String foodName; //음식이름
	private int grams; //섭취그람
	private String mealType; //식사시간구분
	private String regDt; //등록일
	
	//Food 테이블 조인해서 가져올 값
	private int stGrams; //기준그람
	private int cal; //칼로리
	private int carb; //탄수화물
	private int fat; //지방
	private int prot; //단백질
	private int na; //나트륨
	
	//service 단에서 섭취그람 적용한 영양정보 값
	private int totalCal;   // 총 섭취 칼로리
	private int totalCarb;  // 총 섭취 탄수화물
	private int totalFat;   // 총 섭취 지방
	private int totalProt;  // 총 섭취 단백질
	private int totalNa;    // 총 섭취 나트륨
	
	public FoodDiaryOutDTO() {
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @param fdCode
	 * @param userId
	 * @param foodName
	 * @param grams
	 * @param mealType
	 * @param regDt
	 * @param stGrams
	 * @param cal
	 * @param carb
	 * @param fat
	 * @param prot
	 * @param na
	 */
	public FoodDiaryOutDTO(int fdCode, String userId, String foodName, int grams, String mealType, String regDt,
			int stGrams, int cal, int carb, int fat, int prot, int na) {
		super();
		this.fdCode = fdCode;
		this.userId = userId;
		this.foodName = foodName;
		this.grams = grams;
		this.mealType = mealType;
		this.regDt = regDt;
		this.stGrams = stGrams;
		this.cal = cal;
		this.carb = carb;
		this.fat = fat;
		this.prot = prot;
		this.na = na;
	}



	/**
	 * @param fdCode
	 * @param userId
	 * @param foodName
	 * @param grams
	 * @param mealType
	 * @param regDt
	 * @param stGrams
	 * @param cal
	 * @param carb
	 * @param fat
	 * @param prot
	 * @param na
	 * @param totalCal
	 * @param totalCarb
	 * @param totalFat
	 * @param totalProt
	 * @param totalNa
	 */
	public FoodDiaryOutDTO(int fdCode, String userId, String foodName, int grams, String mealType, String regDt,
			int stGrams, int cal, int carb, int fat, int prot, int na, int totalCal, int totalCarb, int totalFat,
			int totalProt, int totalNa) {
		super();
		this.fdCode = fdCode;
		this.userId = userId;
		this.foodName = foodName;
		this.grams = grams;
		this.mealType = mealType;
		this.regDt = regDt;
		this.stGrams = stGrams;
		this.cal = cal;
		this.carb = carb;
		this.fat = fat;
		this.prot = prot;
		this.na = na;
		this.totalCal = totalCal;
		this.totalCarb = totalCarb;
		this.totalFat = totalFat;
		this.totalProt = totalProt;
		this.totalNa = totalNa;
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

	@Override
	public String toString() {
		return "FoodDiaryOutDTO [fdCode=" + fdCode + ", userId=" + userId + ", foodName=" + foodName + ", grams="
				+ grams + ", mealType=" + mealType + ", regDt=" + regDt + ", stGrams=" + stGrams + ", cal=" + cal
				+ ", carb=" + carb + ", fat=" + fat + ", prot=" + prot + ", na=" + na + ", totalCal=" + totalCal
				+ ", totalCarb=" + totalCarb + ", totalFat=" + totalFat + ", totalProt=" + totalProt + ", totalNa="
				+ totalNa + "]";
	}



}
