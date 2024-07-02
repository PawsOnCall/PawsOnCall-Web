package com.pawsoncall.web.http.request;

import jakarta.validation.constraints.NotBlank;

public class GoogleLoginRequest {
	@NotBlank
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
