package com.pcwk.ehr.findid;

public class FindIdDTO {
	private String email;
    private String birth; // YYYY-MM-DD
    
    public FindIdDTO() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getBirth() { return birth; }
    public void setBirth(String birth) { this.birth = birth; }
	/**
	 * @param email
	 * @param birth
	 */
	public FindIdDTO(String email, String birth) {
		super();
		this.email = email;
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "FindIdDTO [email=" + email + ", birth=" + birth + "]";
	}
    
    
}
