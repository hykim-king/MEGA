package com.pcwk.ehr.login.domain;

public class MainDTO {
	private String username;
    private String welcomeMessage;

    public MainDTO() {}

	public MainDTO(String username, String welcomeMessage) {
		super();
		this.username = username;
		this.welcomeMessage = welcomeMessage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	@Override
	public String toString() {
		return "MainDTO [username=" + username + ", welcomeMessage=" + welcomeMessage + "]";
	}
    
    

}
