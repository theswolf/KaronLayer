package test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredController {

	@RequestMapping("/hello")
	public String isOk() {
		return "is ok";
	}
}

