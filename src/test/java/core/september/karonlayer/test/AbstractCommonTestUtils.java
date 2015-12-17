package core.september.karonlayer.test;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.client.RestTemplate;

import core.september.karonlayer.config.Config;
import core.september.karonlayer.config.security.UserService;
import core.september.karonlayer.persistence.model.User;
import core.september.karonlayer.persistence.repository.DomainRepo;
import core.september.karonlayer.persistence.repository.PersistedUserRepo;

public abstract class AbstractCommonTestUtils {

	@Value("${local.server.port}")
	protected int port;

	protected URL base;
	protected RestTemplate template;

	protected static  User user;

	@Autowired
	@Qualifier("UserService")
	protected  UserService us;
	
	@Autowired
	protected PersistedUserRepo userRepo;
	@Autowired
	protected DomainRepo domainRepo;

	protected Logger logger() {
		return LoggerFactory.getLogger(this.getClass());
	}

	@Before
	public void setUp() throws Exception {
		Collection<GrantedAuthority> coll = new ArrayList<GrantedAuthority>();
		coll.add(new SimpleGrantedAuthority("user"));
		user = new User("test", "test", Arrays.asList(new SimpleGrantedAuthority("user")));
		us.addUser(user);
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
	
		//set interceptors/requestFactory
		/*ClientHttpRequestInterceptor ri = new LoggingRequestInterceptor();
		List<ClientHttpRequestInterceptor> ris = new ArrayList<ClientHttpRequestInterceptor>();
		ris.add(ri);
		template.setInterceptors(ris);
		template.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));*/
	}

	@After
	public void after() {
		domainRepo.deleteAll();
		userRepo.deleteAll();
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

}
