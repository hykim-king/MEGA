package com.pcwk.ehr.login.domain;

public class LoginDTO {
	private String userId;//가입한 사용자 ID
    private String password;//관리자 및 사용자 비밀번호
    
    public LoginDTO() {}

	public LoginDTO(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDTO [userId=" + userId + ", password=" + password + "]";
	}

    
}
