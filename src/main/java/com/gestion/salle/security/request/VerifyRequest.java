package com.gestion.salle.security.request;

public class VerifyRequest {

	private Boolean isActive;
	
	private String verifyEmail;
	
	public String getVerifyEmail() {
		return verifyEmail;
	}

	public void setVerifyEmail(String verifyEmail) {
		this.verifyEmail = verifyEmail;
	}

	public Boolean getIsVerifyEmail() {
		return isVerifyEmail;
	}

	public void setIsVerifyEmail(Boolean isVerifyEmail) {
		this.isVerifyEmail = isVerifyEmail;
	}

	private Boolean isVerifyEmail;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}
