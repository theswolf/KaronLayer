package test.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.config.security.TokenHandler;
import test.config.security.UserService;
import test.controller.comunication.model.SignInRequest;
import test.controller.comunication.model.SignResponse;
import test.persistence.model.User;


@RestController
public class AuthController {
	
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	@Qualifier("UserService")
	private UserService userService;
	@Autowired
	private TokenHandler tokenHandler;
	
	private BCryptPasswordEncoder getBCryptPasswordEncoder() {
		if(encoder == null) {
			encoder = new BCryptPasswordEncoder();
		}
		return encoder;
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public SignResponse login(@RequestBody @Valid SignInRequest request) throws Exception {
		User user = userService.loadUserByUsername(request.getUsername());
		if(getBCryptPasswordEncoder().matches(request.getPassword(), user.getPassword())) {
			SignResponse resp = new SignResponse();
			resp.setToken(tokenHandler.createTokenForUser(user));
			resp.setRenewToken(tokenHandler.createRenewTokenForUser(user));
			return resp;
		}
		else throw new Exception("wrong mail or password");
	}

}
