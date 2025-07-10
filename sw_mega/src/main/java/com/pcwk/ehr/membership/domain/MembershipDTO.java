package com.pcwk.ehr.membership.domain; // 소문자 dto

import java.util.Date;

import javax.validation.constraints.Email;
//import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class MembershipDTO {

	@NotBlank(message = "아이디는 필수입니다.")
	@Size(min = 4, max = 30, message = "아이디는 4~30자여야 합니다.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문+숫자만 가능합니다.")
	private String userId;
	private String adminId;

	@NotBlank(message = "이메일은 필수입니다.")
	@Email(message = "이메일 형식을 확인해주세요.")
	private String email;

	// @NotBlank(message = "비밀번호는 필수입니다.")
	// @Size(min = 6, max = 50, message = "비밀번호는 6~50자여야 합니다.")
	// private String password;

	@NotNull(message = "생년월일은 필수입니다.")
	@Past(message = "생년월일은 과거 날짜여야 합니다.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth; // 생년월일

	@NotBlank(message = "이메일 인증 여부(Y/N)가 필요합니다.")
	@Pattern(regexp = "^[YN]$", message = "이메일 인증 여부는 Y 또는 N이어야 합니다.")
	private String emailAuth = "N";
	private String emailAuthToken;
	private int grade; // 1:BASIC 2:SILVER 3:GOLD
	private String profileImage;
	private Date regDt; // 가입일

	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+]).{8,16}$", message = "비밀번호는 8~16자, 영문/숫자/특수문자를 모두 포함해야 합니다.")
	private String password;

	public MembershipDTO() {
	}

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
