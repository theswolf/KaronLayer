package core.september.karonlayer.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;



import core.september.karonlayer.Application;
import core.september.karonlayer.persistence.model.App;
import core.september.karonlayer.persistence.model.Domain;
import core.september.karonlayer.persistence.model.User;
import core.september.karonlayer.persistence.repository.AppRepo;
import core.september.karonlayer.service.data.AppDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class DataIntegrationTest extends AbstractCommonTestUtils{


	User test1,test2;
	Domain tDomain1,tDomain2;

	@Autowired
	private AppRepo appRepo;
	
	@Autowired
	private AppDataService appDataService;


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
		tDomain1 = us.addUserToDomain(tDomain1, false,test1);

		tDomain2 = new Domain();
		tDomain2.setAlias("tDomain2");
		tDomain2.setUser(new HashSet<User>(Arrays.asList(test1,test2)));

		tDomain2 = us.addUserToDomain(tDomain2, false, test1,test2);
		tDomain2 = us.addUserToDomain(tDomain2, true, test1);



	}


	@After
	public void after() {
		appRepo.deleteAll();
		super.after();
	}

	@Test
	public void checkDomainConstraints() {

		List<Domain> oneDomain = us.findByUser(test2);
		assertThat(oneDomain.size(), equalTo(1));

		List<Domain> twoDomain = us.findByUser(test1);
		assertThat(twoDomain.size(), equalTo(2));

	}

	@Test
	public void checkRepoForeignConstraints() {

		List<Domain> oneDomain = us.findByUsername(test2.getUsername());
		assertThat(oneDomain.size(), equalTo(1));

		List<Domain> twoDomain = us.findByUsername(test1.getUsername());
		assertThat(twoDomain.size(), equalTo(2));

	}

	@Test
	public void checkOwnerConstraints() {

		List<Domain> noDomain = us.findByOwnername(test2.getUsername());
		assertThat(noDomain.size(), equalTo(0));

		List<Domain> oneDomain = us.findByOwnername(test1.getUsername());
		assertThat(oneDomain.size(), equalTo(1));
		assertThat(oneDomain.get(0).getUser().size(), equalTo(2));

	}

	@Test
	public void testAppRepo() {
		App app = new App();

		Map<String,String> defaultConf = Collections.emptyMap();
		defaultConf.put("test.value.one", "test.description.one");
		defaultConf.put("test.value.two", "test.description.two");

		app.setDomain(tDomain1);
		app.setDescription("tDomain1");
		app.setName(app.getDescription());
		app.setDefaultConf(defaultConf);

		appRepo.save(app);

		App app2 = new App();


		app2.setDomain(tDomain1);
		app2.setDescription("tDomain1 bis");
		app2.setName(app2.getDescription());
		app2.setDefaultConf(defaultConf);
		
		appRepo.save(app2);
		
		assertThat(((Collection<?>)appRepo.findAll()).size(), equalTo(2));
	}
	
	@Test(expected= javax.validation.ConstraintViolationException.class)
	public void testAppRepoNoDomain() {
		App app = new App();

		Map<String,String> defaultConf = Collections.emptyMap();
		defaultConf.put("test.value.one", "test.description.one");
		defaultConf.put("test.value.two", "test.description.two");

		//app.setDomain(tDomain1);
		app.setDescription("tDomain1");
		app.setName(app.getDescription());
		app.setDefaultConf(defaultConf);

		appRepo.save(app);

		/*App app2 = new App();


		app2.setDomain(tDomain1);
		app2.setDescription("tDomain1 bis");
		app2.setName(app2.getDescription());
		app2.setDefaultConf(defaultConf);
		
		appRepo.save(app2);*/
	}






}
