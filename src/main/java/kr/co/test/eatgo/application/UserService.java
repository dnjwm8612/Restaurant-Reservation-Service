package kr.co.test.eatgo.application;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.test.eatgo.domain.User;
import kr.co.test.eatgo.domain.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	public User addUser(String name, String email) {
		User user = User.builder().name(name).email(email).build();
		
		userRepository.save(user);
		
		return user;
	}

	public User updateUser(Long id, String name, String email, Long level) {
		User user = userRepository.findById(id).orElse(null);
		
		user.setName(name);
		user.setEmail(email);
		user.setLevel(level);
		
		return user;
	}

	public User deactiveUser(Long id) {
		User user = userRepository.findById(id).orElse(null);
		user.deactivate();
		return user;
	}

}
