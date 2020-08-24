package kr.co.test.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.test.eatgo.application.UserCustomerService;
import kr.co.test.eatgo.domain.User;

@RestController
public class UserCustomerController {

	@Autowired
	UserCustomerService userCustomerService;
	
	@PostMapping("/users")
	public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
		User user = userCustomerService.registerUser(resource.getEmail(), resource.getName(), resource.getPassword());
		
		String url= "/users/" + user.getId();
		return ResponseEntity.created(new URI(url)).body("{}");
	}
	
}
