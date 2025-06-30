package com.pcwk.ehr.login.domain;

public class FindIdDTO {
	private String email;
  
    
    public FindIdDTO() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

	/**
	 * @param email
	 * @param birth
	 */
	public FindIdDTO(String email, String birth) {
		super();
		this.email = email;
		
	}

	@Override
	public String toString() {
		return "FindIdDTO [email=" + email + "]";
	}

    
    
}
