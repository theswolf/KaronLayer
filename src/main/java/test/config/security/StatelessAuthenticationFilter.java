package test.config.security;

import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class StatelessAuthenticationFilter extends GenericFilterBean {

    private final JWTTokenAuthService authenticationService;

    public StatelessAuthenticationFilter(JWTTokenAuthService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        try {
        	Authentication authentication = authenticationService.getAuthentication(httpRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
        
        catch(ExpiredJwtException e) {
        	HttpServletResponse resp = (HttpServletResponse) response;
        	resp.setStatus(HttpStatus.PRECONDITION_FAILED.value());
        	e.printStackTrace(resp.getWriter());
        }
        
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
