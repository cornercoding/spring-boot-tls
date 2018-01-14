package oer.codingcorner.springboottlsdemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SSLTestController {
	
	@RequestMapping(value="/test/ssl")
	public String testSSL() {
		return "Success";
	}

}
