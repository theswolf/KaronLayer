package test.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import test.persistence.model.User;


@Service
public class JWTTokenAuthService {

    public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    private final TokenHandler tokenHandler;
   
    //private final UserService userService;
   
   
    @Autowired
    public JWTTokenAuthService( @Qualifier("UserService") UserService userService) {
    	//this.userService = userService;
        tokenHandler = tokenHandler(userService);
    }
    
    @Bean
    public TokenHandler tokenHandler( @Qualifier("UserService") UserService userService) {
    	return new TokenHandler(userService);
    }

    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null) {
            final User user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}

