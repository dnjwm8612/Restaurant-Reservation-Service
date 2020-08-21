package kr.co.test.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.test.eatgo.application.UserService;
import kr.co.test.eatgo.domain.User;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> list(){
		List<User> users = userService.getUsers();
		return users;
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException{
		String name = resource.getName();
		String email = resource.getEmail();
		
		User user = userService.addUser(name,  email);
		
		String url = "/users/" + user.getId();
		return ResponseEntity.created(new URI(url)).body("{}");
	}
	
	@PatchMapping("/users/{userId}")
	public String update(@PathVariable("userId") Long id, @RequestBody User resource) {
		User user = userService.updateUser(id, resource.getName(), resource.getEmail(), resource.getLevel());
		
		
		return "{}";
	}
}
