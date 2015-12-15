package core.september.karonlayer.controller.comunication.model;

import javax.validation.constraints.NotNull;

import core.september.karonlayer.config.validation.constraints.Matches;


@Matches(field="password", verifyField="confirmPassword")
public class SignUpRequest {

	@NotNull
	private String user;
	
	@NotNull
	private String password;
	@NotNull
	private String confirmPassword;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
}
