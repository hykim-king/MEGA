package com.pcwk.ehr.membership.domain;   

import java.util.Date;

public class MembershipDTO {

    private String userId;
    private String adminId;
    private String email;
    private String password;
    private Date   birth;            // 생년월일
    private String emailAuth;        // 이메일 인증 여부
    private String emailAuthToken;
    private int    grade;            // 1:BASIC 2:SILVER 3:GOLD
    private String profileImage;
    private Date   regDt;            // 가입일 

    public MembershipDTO() {}

	public MembershipDTO(String userId, String adminId, String email, String password, Date birth, String emailAuth,
			String emailAuthToken, int grade, String profileImage, Date regDt) {
		super();
		this.userId = userId;
		this.adminId = adminId;
		this.email = email;
		this.password = password;
		this.birth = birth;
		this.emailAuth = emailAuth;
		this.emailAuthToken = emailAuthToken;
		this.grade = grade;
		this.profileImage = profileImage;
		this.regDt = regDt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getEmailAuth() {
		return emailAuth;
	}

	public void setEmailAuth(String emailAuth) {
		this.emailAuth = emailAuth;
	}

	public String getEmailAuthToken() {
		return emailAuthToken;
	}

	public void setEmailAuthToken(String emailAuthToken) {
		this.emailAuthToken = emailAuthToken;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "MembershipDTO [userId=" + userId + ", adminId=" + adminId + ", email=" + email + ", password="
				+ password + ", birth=" + birth + ", emailAuth=" + emailAuth + ", emailAuthToken=" + emailAuthToken
				+ ", grade=" + grade + ", profileImage=" + profileImage + ", regDt=" + regDt + "]";
	}

    
}
