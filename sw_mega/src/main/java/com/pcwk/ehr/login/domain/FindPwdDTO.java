package com.pcwk.ehr.login.domain;

public class FindPwdDTO {
	private String userId;
    private String email;
    private String password; // 임시비번
    
    public FindPwdDTO() {}
	/**
	 * @param userId
	 * @param email
	 * @param password
	 */
	public FindPwdDTO(String userId, String email, String password) {
		super();
		this.userId = userId;
		this.email = email;
		this.password = password;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "FindPwdDTO [userId=" + userId + ", email=" + email + ", password=" + password + "]";
	}
    
    
}
