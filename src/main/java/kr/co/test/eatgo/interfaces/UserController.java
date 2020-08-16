package kr.co.test.eatgo.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.test.eatgo.domain.User;

@RestController
public class UserController {

	@GetMapping("/users")
	public List<User> list(){
		return null;
	}
	
}
