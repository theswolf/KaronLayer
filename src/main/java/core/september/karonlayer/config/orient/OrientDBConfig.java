package core.september.karonlayer.config.orient;

import java.io.File;
import java.util.regex.Pattern;

import javax.annotation.PreDestroy;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.orientechnologies.orient.client.remote.OServerAdmin;
import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.OServerMain;

@Configuration
public class OrientDBConfig {

	@Autowired
	private ResourceLoader resourceLoader;

	@Value("${orient.db.server.database.path}")
	private String orientPath;

	@Value("${orient.db.server.database.user}")
	private String user;

	@Value("${orient.db.server.database.password}")
	private String password;
	
	@Value("${orient.db.server.database.binary.port.range}")
	private String binaryPortRange;
	
	@Value("${orient.db.server.database.http.port.range}")
	private String httpPortRange;
	
	@Value("${orient.db.server.database.name}")
	private String dbName;
	
	private OServer server;
	
	@Bean
	@Qualifier("dbConnectionUrl")
	public String dbConnectionUrl() {
		/*connect remote:localhost:{port}/{db} {user} {password}
		where:
		  port     : the port that the binary server listens on
		             (first free port from 2424-2430 according to the configuration above)
		  db       : the database name to connect to (defaults to "db" and can be set using <entry name="server.database.path" value="db"/> in the configuration
		  user     : the user to connect with (this is NOT the same as root user in the configuration)
		  password : the user to connect with (this is NOT the same as root password in the configuration)*/
		String pattern = Pattern.quote(File.separator);
		String[] pathVariables = orientPath.split(pattern);
		StringBuilder sb = new StringBuilder();
		sb.append("remote:localhost:")
		.append("2424").append("/")
		//.append(pathVariables[pathVariables.length-1]);
		.append(dbName);
		//.append(" ").append(user)
		//.append(" ").append(password);
		return sb.toString();
	}

	@Bean
	public OServer server() throws Exception{
		try {

			System.setProperty("orient.db.server.database.path", orientPath);
			System.setProperty("orient.db.server.database.user", user);
			System.setProperty("orient.db.server.database.password", password);
			System.setProperty("ORIENTDB_ROOT_PASSWORD", password);
			
			

			Resource resource = resourceLoader.getResource("classpath:orient.xml");
			server = OServerMain.create();
			server.startup(resource.getFile());
			server.activate(); 
			
			OServerAdmin serverAdmin = new OServerAdmin(dbConnectionUrl()).connect(user, password);
			if(!serverAdmin.existsDatabase("plocal")) {
				serverAdmin.createDatabase(dbName, "document", "plocal");
			}
			
			
			return server;
		}

		catch(Exception e) {
			LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
			throw e;
		}
	}
	
	@PreDestroy
	public void release() {
		server.shutdown();
	}
}
