package test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import test.config.Config;
import test.config.security.JWTTokenAuthService;
import test.config.security.UserService;
import test.controller.comunication.model.SignInRequest;
import test.controller.comunication.model.SignResponse;
import test.persistence.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class HelloControllerITTest {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloControllerITTest.class);

    @Value("${local.server.port}")
    private int port;

	private URL base;
	private RestTemplate template;
	
	private  User user;
	
	@Autowired
	@Qualifier("UserService")
	private  UserService us;
	
	/*@BeforeClass
	public static void beforeClass() {
		user = new PersistedUser("test", "test", Arrays.asList(new SimpleGrantedAuthority("user")));
		us.addUser(user);
	}*/

	@Before
	public void setUp() throws Exception {
		Collection<GrantedAuthority> coll = new ArrayList<GrantedAuthority>();
		coll.add(new SimpleGrantedAuthority("user"));
		user = new User("test", "test", coll);
		us.addUser(user);
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	}
	
	@After
	public void after() {
		
		us.getRepo().findAll().forEach(user -> 
		logger.info(user.toString()));
		us.getRepo().deleteAll();
	}
	
	public String parametrizedUrl(String... path) throws Exception {
		StringBuilder sb = new StringBuilder();
		Arrays.asList(path).forEach(
				pat -> sb.append(pat).append("/")
				);
		return this.base.toString().concat(sb.toString());
	}
	
	public String createTokenForUser(User user,Date expire) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, Config.secret)
                .setExpiration(expire)
                .compact();
    }
	
	public Date fromNow(int minutes) {
		return new Date(System.currentTimeMillis()+minutes*60*1000);
	}

	@Test
	public void getHello() throws Exception {
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
	}
	
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
}
