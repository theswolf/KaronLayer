package core.september.karonlayer.test;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import core.september.karonlayer.config.security.UserService;
import core.september.karonlayer.persistence.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
		MockServletContext.class,
		UserService.class		})
@WebAppConfiguration
public class UnitTest {

	private MockMvc mvc;
	
	@Autowired
	private UserService userService;
	
	private User user;

	
	@Test
	public void getHello() throws Exception {
		/*mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));*/
	}
	
	@Before
	public void setUp() throws Exception {
		//mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
		/*Collection<GrantedAuthority> coll = new ArrayList<GrantedAuthority>();
		coll.add(new SimpleGrantedAuthority("user"));
		user = new User("test", "test", Arrays.asList(new SimpleGrantedAuthority("user")));
		userService.addUser(user);*/
	}
	
	
	
}

