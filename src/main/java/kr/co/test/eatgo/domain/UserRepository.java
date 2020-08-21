package kr.co.test.eatgo.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findAll();

	User save(String name, String email);
	
	Optional<User> findById(Long id);
	
}
