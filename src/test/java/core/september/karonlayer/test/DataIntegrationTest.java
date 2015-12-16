package core.september.karonlayer.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import core.september.karonlayer.Application;
import core.september.karonlayer.config.security.UserService;
import core.september.karonlayer.persistence.model.Domain;
import core.september.karonlayer.persistence.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class DataIntegrationTest {
	
	private static final Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

	User test1,test2;
	Domain tDomain1,tDomain2;
	
    @Value("${local.server.port}")
    private int port;
	
	@Autowired
	@Qualifier("UserService")
	private  UserService us;
	


	@Before
	public void setUp() throws Exception {
		Collection<GrantedAuthority> coll = new ArrayList<GrantedAuthority>();
		coll.add(new SimpleGrantedAuthority("user"));
		test1 = new User("test", "test", Arrays.asList(new SimpleGrantedAuthority("user")));
		test1 = us.addUser(test1);
		
		test2 = new User("test2", "test2", Arrays.asList(new SimpleGrantedAuthority("user")));
		test2 = us.addUser(test2);
		
		tDomain1 = new Domain();
		tDomain1.setAlias("tDomain1");
		tDomain1.setUser(Arrays.asList(test1));
		
		tDomain2 = new Domain();
		tDomain2.setAlias("tDomain2");
		tDomain2.setUser(Arrays.asList(test1,test2));
		
		us.getDomainRepo().save(tDomain1);
		us.getDomainRepo().save(tDomain2);

	}
	
	@After
	public void after() {
		
		us.getUserRepo().findAll().forEach(user -> 
		logger.info(user.toString()));
		us.getDomainRepo().deleteAll();
		us.getUserRepo().deleteAll();
		
	}
	
	
	@Test
	public void checkDomainConstraints() {
		
		List<Domain> oneDomain = us.getDomainRepo().findByUser(test2);
		assertThat(oneDomain.size(), equalTo(1));
		
		List<Domain> twoDomain = us.getDomainRepo().findByUser(test1);
		assertThat(twoDomain.size(), equalTo(2));
		
	}
	
	@Test
	public void checkRepoForeignConstraints() {
		
		List<Domain> oneDomain = us.getDomainRepo().findByUsername(test2.getUsername());
		assertThat(oneDomain.size(), equalTo(1));
		
		List<Domain> twoDomain = us.getDomainRepo().findByUsername(test1.getUsername());
		assertThat(twoDomain.size(), equalTo(2));
		
	}
	
	

	
	
	
	
}
