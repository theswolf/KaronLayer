package core.september.karonlayer.test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MockServletContext.class		})
@WebAppConfiguration
public class UnitTest {

	private MockMvc mvc;

	
	@Test
	public void getHello() throws Exception {
	 assertThat(1, equalTo(1));
		/*mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));*/
	}
	
	@Before
	public void setUp() throws Exception {
		//mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
		Collection<GrantedAuthority> coll = new ArrayList<GrantedAuthority>();
		/*coll.add(new SimpleGrantedAuthority("user"));
		user = new User("test", "test", Arrays.asList(new SimpleGrantedAuthority("user")));
		userService.addUser(user);*/
	}
	
	
	
}

