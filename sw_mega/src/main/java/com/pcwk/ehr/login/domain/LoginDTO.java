package com.pcwk.ehr.login.domain;

public class LoginDTO {
	private String userId;//가입한 사용자 ID
    private String password;//관리자 및 사용자 비밀번호
    
    public LoginDTO() {}
    

	/**
	 * @param userId
	 * @param password
	 */
	public LoginDTO(String userId, String password) {
		super();
		this.userId = userId;
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
		return "LoginDTO [userId=" + userId + ", password=" + password + "]";
	}

    
	
    
}
