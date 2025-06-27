/**
 * Package Name : com.pcwk.ehr.mypage.domain <br/>
 * Class Name: FoodInputDTO.java <br/>
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

public class FoodInputDTO {
	
    private String foodName;
    private int gram;
    
	public FoodInputDTO() {
	}

	/**
	 * @param foodName
	 * @param gram
	 */
	public FoodInputDTO(String foodName, int gram) {
		super();
		this.foodName = foodName;
		this.gram = gram;
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
	 * @return the gram
	 */
	public int getGram() {
		return gram;
	}

	/**
	 * @param gram the gram to set
	 */
	public void setGram(int gram) {
		this.gram = gram;
	}

	@Override
	public String toString() {
		return "FoodInputDTO [foodName=" + foodName + ", gram=" + gram + "]";
	}
	
}
