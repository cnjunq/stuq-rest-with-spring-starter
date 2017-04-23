package io.junq.stuq.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SimpleRestController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/hello", produces = "text/html")
	public String hello() {
		return "Hello from Spring Boot.";
	}
}
