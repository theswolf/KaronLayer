package core.september.karonlayer.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import core.september.karonlayer.Application;
import core.september.karonlayer.config.security.JWTTokenAuthService;
import core.september.karonlayer.controller.comunication.model.SignInRequest;
import core.september.karonlayer.controller.comunication.model.SignResponse;
import core.september.karonlayer.controller.comunication.model.SignUpRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class AuthIntegrationTest extends AbstractCommonTestUtils{
	
	@Test
	public void getSecure() throws Exception {
		
		//RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set(JWTTokenAuthService.AUTH_HEADER_NAME, createTokenForUser(user,fromNow(5)));

		HttpEntity<Void> entity = new HttpEntity<Void>(headers);
		
		ResponseEntity<String> response = template.exchange(parametrizedUrl("hello"), HttpMethod.GET, entity, String.class);
		
		assertThat(response.getBody(), equalTo("is ok"));
	}
	
	@Test
	public void getExpired() throws Exception {
		
		//RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set(JWTTokenAuthService.AUTH_HEADER_NAME, createTokenForUser(user,fromNow(-5)));

		HttpEntity<Void> entity = new HttpEntity<Void>(headers);
		
		ResponseEntity<String> response = template.exchange(parametrizedUrl("hello"), HttpMethod.GET, entity, String.class);
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.PRECONDITION_FAILED));
	}
	
	@Test
	public void signin() throws Exception {
		
		SignInRequest req = new SignInRequest();
		req.setPassword("test");
		req.setUsername("test");
		
		HttpEntity<SignInRequest> entity = new HttpEntity<SignInRequest>(req);
		
		
		ResponseEntity<SignResponse> response = template.exchange(parametrizedUrl("signin"), HttpMethod.POST, entity, SignResponse.class);
		
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}
	
	
	@Test
	public void signinNull() throws Exception {
		
		SignInRequest req = new SignInRequest();
		//req.setPassword("test");
		//req.setUsername("test");
		
		HttpEntity<SignInRequest> entity = new HttpEntity<SignInRequest>(req);
		
		
		ResponseEntity<Object> response = template.exchange(parametrizedUrl("signin"), HttpMethod.POST, entity, Object.class);
		System.out.println(response.getBody());
		assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void signUpValidationFault() throws Exception {
		
		SignUpRequest req = new SignUpRequest();
		req.setPassword("testa");
		req.setConfirmPassword("test");
		req.setUser("test");
		
		HttpEntity<SignUpRequest> entity = new HttpEntity<SignUpRequest>(req);
		ResponseEntity<Object> response = template.exchange(parametrizedUrl("signup"), HttpMethod.POST, entity, Object.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void signUpUserExixts() throws Exception {
		
		SignUpRequest req = new SignUpRequest();
		req.setPassword("test");
		req.setConfirmPassword("test");
		req.setUser("test");
		
		HttpEntity<SignUpRequest> entity = new HttpEntity<SignUpRequest>(req);
		ResponseEntity<Object> response = template.exchange(parametrizedUrl("signup"), HttpMethod.POST, entity, Object.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_ACCEPTABLE));
	}
	
	@Test
	public void signUpUserOk() throws Exception {
		
		SignUpRequest req = new SignUpRequest();
		req.setPassword("test");
		req.setConfirmPassword("test");
		req.setUser("armando");
		
		HttpEntity<SignUpRequest> entity = new HttpEntity<SignUpRequest>(req);
		ResponseEntity<Object> response = template.exchange(parametrizedUrl("signup"), HttpMethod.POST, entity, Object.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}
}
