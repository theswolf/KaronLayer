package test.config.mvc;

import javax.servlet.ServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler  {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(Exception.class)
    public void handleConflict(Exception e,ServletResponse response)  {
		//LoggerFactory.getLogger(GlobalControllerExceptionHandler.class).debug("******* Handled");
    	LoggerFactory.getLogger(GlobalControllerExceptionHandler.class).debug("Handled exception of type: "+e.getClass());
		LoggerFactory.getLogger(GlobalControllerExceptionHandler.class).error(e.getMessage());
		//e.printStackTrace(response.getWriter());
		
	
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 500
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public void handleValidation(Exception e,ServletResponse response) {
    	handleConflict(e,response);
    }
}
