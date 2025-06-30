package com.pcwk.ehr.login.domain;

public class LoginDTO {
	private String userId;
    private String password;
    private String email;
    private String adminId;
    
    public LoginDTO() {}
	/**
	 * @param userId
	 * @param password
	 * @param email
	 * @param adminId
	 */
	public LoginDTO(String userId, String password, String email, String adminId) {
		super();
		this.userId = userId;
		this.password = password;
		this.email = email;
		this.adminId = adminId;
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
	 * @return the adminId
	 */
	public String getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	@Override
	public String toString() {
		return "LoginDTO [userId=" + userId + ", password=" + password + ", email=" + email + ", adminId=" + adminId
				+ "]";
	}
    
    
}
