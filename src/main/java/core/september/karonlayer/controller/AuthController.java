package core.september.karonlayer.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import core.september.karonlayer.config.AppRuntimeException;
import core.september.karonlayer.config.security.TokenHandler;
import core.september.karonlayer.config.security.UserService;
import core.september.karonlayer.controller.comunication.model.SignInRequest;
import core.september.karonlayer.controller.comunication.model.SignResponse;
import core.september.karonlayer.controller.comunication.model.SignUpRequest;
import core.september.karonlayer.persistence.model.User;



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
		else throw new AppRuntimeException("wrong mail or password");
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public SignResponse signup(@RequestBody @Valid SignUpRequest request) throws Exception {
		
		try {
			User user = userService.loadUserByUsername(request.getUser());
			if(user != null) throw new AppRuntimeException("User already exists");
			return null;
		}
		
		catch(UsernameNotFoundException unfe) {
			User user = new User(request.getUser(), request.getUser(), Arrays.asList(new SimpleGrantedAuthority("user")));
			user = userService.addUser(user);
			SignResponse resp = new SignResponse();
			resp.setToken(tokenHandler.createTokenForUser(user));
			resp.setRenewToken(tokenHandler.createRenewTokenForUser(user));
			return resp;
		}
		
		
	}

}
