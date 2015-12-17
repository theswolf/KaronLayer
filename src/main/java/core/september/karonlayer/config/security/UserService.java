package core.september.karonlayer.config.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import core.september.karonlayer.persistence.model.Domain;
import core.september.karonlayer.persistence.model.User;
import core.september.karonlayer.persistence.repository.DomainRepo;
import core.september.karonlayer.persistence.repository.PersistedUserRepo;




@Service("UserService")
public class UserService implements UserDetailsService {

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    //private final HashMap<String, User> userMap = new HashMap<String, User>();
    @Autowired
    private PersistedUserRepo userRepo;
    @Autowired
    private DomainRepo domainRepo;
    
    public UserService() {
    	LoggerFactory.getLogger(this.getClass()).info("UserService was created");
    }

    @Override
    public final User loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepo.findByUsername(username);//userMap.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        detailsChecker.check(user);
        return user;
    }

    public User addUser(User user) throws Exception {
    	if(userRepo.findByUsername(user.getUsername()) == null ) {
    		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    		return userRepo.save(user);
    	}
    	throw new Exception("User exists");
    		
    }
    
    public Domain addUserToDomain(Domain domain,boolean isOwner,User... user) throws Exception {
    	    	
    	if(domain.getUser() == null) {
    		Set<User> userList = new HashSet<User>();
    		domain.setUser(userList);
    	}
    	domain.getUser().addAll(Arrays.asList(user));
    	if(isOwner) {
    		if(domain.getOwner() == null) {
        		Set<User> ownerList = new HashSet<User>();
        		domain.setOwner(ownerList);
        	}
        	domain.getOwner().addAll(Arrays.asList(user));
    	}
    	return domainRepo.save(domain);
    }
    
    
    
    public User currentUser() {
    	UserAuthentication auth = (UserAuthentication) SecurityContextHolder.getContext().getAuthentication();
    	return auth.getDetails();
    }
    
    
    public List<Domain> findByUser(User user) {
    	return domainRepo.findByUser(user);
    }
    
    public List<Domain> findByUsername(String username) {
    	return domainRepo.findByUsername(username);
    }
    
    public List<Domain> findByOwnername(String username) {
    	return domainRepo.findByOwner(username);
    }
}


