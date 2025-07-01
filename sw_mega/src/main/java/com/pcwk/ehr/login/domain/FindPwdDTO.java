package com.pcwk.ehr.login.domain;

public class FindPwdDTO {
	private String userId;
    private String email;
    
    
    public FindPwdDTO() {}
	/**
	 * @param userId
	 * @param email
	 * @param password
	 */
	public FindPwdDTO(String userId, String email) {
		super();
		this.userId = userId;
		this.email = email;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "FindPwdDTO [userId=" + userId + ", email=" + email + "]";
	}
	

    
}
