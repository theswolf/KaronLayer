package core.september.karonlayer.config.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import core.september.karonlayer.config.Config;
import core.september.karonlayer.config.Config.Router;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {

	private Logger logger = LoggerFactory.getLogger(MvcConfig.class);
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	for(Router route: Router.values()) {
    		registry.addViewController(Config.staticPrefix.concat(route.name())).setViewName(route.getView());
    		logger.debug("Registered".concat(Config.staticPrefix.concat(route.name())).concat(" on ").concat(route.getView()));
    	}
        
        //registry.addViewController("/").setViewName("home");
        //registry.addViewController("/hello").setViewName("hello");
        //registry.addViewController("/login").setViewName("login");
    }
}

