package core.september.karonlayer.config;

public class Config {

	public static final String secret = "tooManySecret";
	public static final String[] authUrls = new String[] {
		"/",      
		"/favicon.ico",
		"**/*.html",
		"**/*.css",  
		"**/*.js",         
		"/auth/**",    
		"/signin/**",
		"/signup/**"
		
	};

}
