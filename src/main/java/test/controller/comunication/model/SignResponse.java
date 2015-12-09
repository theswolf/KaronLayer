package test.controller.comunication.model;

import java.io.Serializable;

public class SignResponse implements Serializable{

	private String token;
	private String renewToken;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRenewToken() {
		return renewToken;
	}
	public void setRenewToken(String renewToken) {
		this.renewToken = renewToken;
	}
	
	
}
