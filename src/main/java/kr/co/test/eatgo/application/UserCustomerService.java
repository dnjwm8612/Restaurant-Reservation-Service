package kr.co.test.eatgo.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.co.test.eatgo.domain.User;
import kr.co.test.eatgo.domain.UserCustomerRepository;

public class UserCustomerService {

	@Autowired
	UserCustomerRepository userCustomerRepository;
	
	public UserCustomerService(UserCustomerRepository userCustomerRepository) {
		this.userCustomerRepository = userCustomerRepository; 
	}

	public User registerUser(String email, String name, String password) {
		Optional<User> existed =  userCustomerRepository.findByEmail(email);
		if(existed.isPresent()) {
			throw new EmailUserExistedException(email);
		}
		
		BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		
		User user = User.builder().id(1004L).email("tester@example.com").name("Tester").password(encodedPassword).build(); 
		return userCustomerRepository.save(user);
	}

}
