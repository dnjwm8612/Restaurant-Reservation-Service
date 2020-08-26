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
public class SessionController {
	
	@Autowired
	UserCustomerService userCustomerService;
	
	@PostMapping("/session")
	public ResponseEntity<?> create(@RequestBody SessionRequestDto resource) throws URISyntaxException{
		
		String email = resource.getEmail();
		String password= resource.getPassword();
		
		User user = userCustomerService.authenticate(email, password);
		
		String accessToken = user.getAccessToken();
		
		String url = "/session"; 
		
		return ResponseEntity.created(new URI(url)).body(SessionResponseDto.builder().accessToken(accessToken).build());
	}
}
