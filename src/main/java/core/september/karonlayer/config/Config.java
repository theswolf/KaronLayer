package core.september.karonlayer.config;

public class Config {
	
	public enum Router{
		home("login");
		private String viewName;
		private Router(String view) {
			this.viewName = view;
		}
		
		public String getView() {
			return viewName;
		}
	}
	
	public static final String staticPrefix = "/web/";
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
		//staticPrefix+"**"
		
	};
	public static int orientDbPoolCapacity = 10;
	
	

}
