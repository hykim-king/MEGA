package com.pcwk.ehr.mypage.domain;

public class NutritionSummaryDTO {

	
	 private String userId;
	 private String regDt;

	 private double totalCal;
	 private double totalCarb;
	 private double totalFat;
	 private double totalProt;
	 private double totalNa;
	 
	 public NutritionSummaryDTO() {
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
	 * @return the totalCal
	 */
	public double getTotalCal() {
		return totalCal;
	}

	/**
	 * @param totalCal the totalCal to set
	 */
	public void setTotalCal(double totalCal) {
		this.totalCal = totalCal;
	}

	/**
	 * @return the totalCarb
	 */
	public double getTotalCarb() {
		return totalCarb;
	}

	/**
	 * @param totalCarb the totalCarb to set
	 */
	public void setTotalCarb(double totalCarb) {
		this.totalCarb = totalCarb;
	}

	/**
	 * @return the totalFat
	 */
	public double getTotalFat() {
		return totalFat;
	}

	/**
	 * @param totalFat the totalFat to set
	 */
	public void setTotalFat(double totalFat) {
		this.totalFat = totalFat;
	}

	/**
	 * @return the totalProt
	 */
	public double getTotalProt() {
		return totalProt;
	}

	/**
	 * @param totalProt the totalProt to set
	 */
	public void setTotalProt(double totalProt) {
		this.totalProt = totalProt;
	}

	/**
	 * @return the totalNa
	 */
	public double getTotalNa() {
		return totalNa;
	}

	/**
	 * @param totalNa the totalNa to set
	 */
	public void setTotalNa(double totalNa) {
		this.totalNa = totalNa;
	}

	@Override
	public String toString() {
		return "NutritionSummaryDTO [userId=" + userId + ", regDt=" + regDt + ", totalCal=" + totalCal + ", totalCarb="
				+ totalCarb + ", totalFat=" + totalFat + ", totalProt=" + totalProt + ", totalNa=" + totalNa + "]";
	}
	 
	 
}
