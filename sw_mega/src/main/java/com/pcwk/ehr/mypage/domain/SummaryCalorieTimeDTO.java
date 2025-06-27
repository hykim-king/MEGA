/**
 * Package Name : com.pcwk.ehr.mypage.domain <br/>
 * Class Name: SummaryCarorieTimeDTO.java <br/>
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

public class SummaryCalorieTimeDTO {
	
	private int duration; //운동시간
	private int calBurned; //소모 칼로리

	public SummaryCalorieTimeDTO() {
	}

	/**
	 * @param duration
	 * @param calBurned
	 */
	public SummaryCalorieTimeDTO(int duration, int calBurned) {
		super();
		this.duration = duration;
		this.calBurned = calBurned;
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
	 * @return the calBurned
	 */
	public int getCalBurned() {
		return calBurned;
	}

	/**
	 * @param calBurned the calBurned to set
	 */
	public void setCalBurned(int calBurned) {
		this.calBurned = calBurned;
	}

	@Override
	public String toString() {
		return "SummaryCarorieTimeDTO [duration=" + duration + ", calBurned=" + calBurned + "]";
	}

}
