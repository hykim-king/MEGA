/**
 * Package Name : com.pcwk.ehr.mypage.domain <br/>
 * Class Name: NutritionDTO.java <br/>
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

public class SummaryNutritionDTO {
	
	private int cal; //섭취칼로리
	private int carb; //탄수화물
	private int fat; //지방
	private int prot; //단백질
	private int na; //나트륨

	/**
	 * 
	 */
	public SummaryNutritionDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cal
	 * @param carb
	 * @param fat
	 * @param prot
	 * @param na
	 */
	public SummaryNutritionDTO(int cal, int carb, int fat, int prot, int na) {
		super();
		this.cal = cal;
		this.carb = carb;
		this.fat = fat;
		this.prot = prot;
		this.na = na;
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
		return "NutritionDTO [cal=" + cal + ", carb=" + carb + ", fat=" + fat + ", prot=" + prot + ", na=" + na + "]";
	}
	
	
}
