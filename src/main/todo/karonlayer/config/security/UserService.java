package core.september.karonlayer.config.security;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import core.september.karonlayer.persistence.model.User;
import core.september.karonlayer.persistence.repository.PersistedUserRepo;




@Service("UserService")
public class UserService implements UserDetailsService {

    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
    //private final HashMap<String, User> userMap = new HashMap<String, User>();
    @Autowired
    private PersistedUserRepo userRepo;
    
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

    public void addUser(User user) {
    	if(userRepo.findByUsername(user.getUsername()) == null )
    		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    		userRepo.save(user);
    }
    
    public PersistedUserRepo getRepo() {
    	return userRepo;
    }
}


