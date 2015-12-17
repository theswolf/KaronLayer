package core.september.karonlayer.service.document;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orientechnologies.orient.core.db.OPartitionedDatabasePool;
import com.orientechnologies.orient.core.db.OPartitionedDatabasePoolFactory;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.server.OServer;

import core.september.karonlayer.config.Config;


@Service
public class DocumentService {
	@Autowired
    private OServer server;
	
	@Autowired
	@Qualifier("dbConnectionUrl")
	public String dbConnectionUrl;
	
	@Value("${orient.db.server.database.user}")
	private String user;

	@Value("${orient.db.server.database.password}")
	private String password;
	
	private ODatabaseDocumentTx db; // = new ODatabaseDocumentTx("remote:localhost/petshop").open("admin", "admin");
	/*connect remote:localhost:{port}/{db} {user} {password}
	where:
	  port     : the port that the binary server listens on
	             (first free port from 2424-2430 according to the configuration above)
	  db       : the database name to connect to (defaults to "db" and can be set using <entry name="server.database.path" value="db"/> in the configuration
	  user     : the user to connect with (this is NOT the same as root user in the configuration)
	  password : the user to connect with (this is NOT the same as root password in the configuration)*/
	@PostConstruct
	public void init() {
		
		OPartitionedDatabasePool oPartitionedDatabasePool = new OPartitionedDatabasePool(dbConnectionUrl, user, password);
		
		/*db = oPartitionedDatabasePool.acquire();
		ODocument doc = new ODocument("Person");
		doc.field( "name", "Luke" );
		doc.field( "surname", "Skywalker" );
		doc.field( "city", new ODocument("City").field("name","Rome").field("country", "Italy") );

		// SAVE THE DOCUMENT
		doc.save();

		db.close();*/
		
	}


}
