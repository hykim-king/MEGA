package com.pcwk.ehr.login.domain;

public class FindIdDTO {
	private String email;//아이디조회를 위한 email
  
    //기본 생성자
    public FindIdDTO() {}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}




	public FindIdDTO(String email) {
		super();
		this.email = email;
	}


	@Override
	public String toString() {
		return "FindIdDTO [email=" + email + "]";
	}

    
    
}
