package core.september.karonlayer.test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.NotNull;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import core.september.karonlayer.config.Config;
import core.september.karonlayer.persistence.model.User;

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
	public void testTokenCreation(){
		User user = new User("user","user",Arrays.asList(new SimpleGrantedAuthority("test")));
		String token =  createTokenForUser(user,fromNow(500));
		LoggerFactory.getLogger(this.getClass()).info(token);
		assertThat(token, notNullValue());
	}
	
	
	
}

