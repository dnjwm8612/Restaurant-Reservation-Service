package kr.co.test.eatgo.application;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.test.eatgo.domain.User;
import kr.co.test.eatgo.domain.UserCustomerRepository;

@Service
@Transactional
public class UserCustomerService {

	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserCustomerRepository userCustomerRepository;
	
	public UserCustomerService(UserCustomerRepository userCustomerRepository, PasswordEncoder passwordEncoder) {
		this.userCustomerRepository = userCustomerRepository; 
		this.passwordEncoder = passwordEncoder;
	}

	public User registerUser(String email, String name, String password) {
		Optional<User> existed =  userCustomerRepository.findByEmail(email);
		if(existed.isPresent()) {
			throw new EmailUserExistedException(email);
		}
		
		User user = User.builder().id(1004L).email("tester@example.com").name("Tester").password(passwordEncoder.encode(password)).build(); 
		return userCustomerRepository.save(user);
	}

	public User authenticate(String email, String password) {
		User user = userCustomerRepository.findByEmail(email).orElseThrow(() -> new EmailNotExistedException(email));
		
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new PasswordWrongException();
		}
		
		return user;
	}

}
