package kr.co.test.eatgo.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.test.eatgo.domain.User;
import kr.co.test.eatgo.domain.UserCustomerRepository;

class UserCustomerServiceTest {

	UserCustomerService userCustomerService;
	
	@Mock
	UserCustomerRepository userCustomerRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		userCustomerService = new UserCustomerService(userCustomerRepository);
	}
	
	@Test
	public void registerUser() {
		String email = "tester@example.com";
		String name= "Tester";
		String password ="test";
		
		userCustomerService.registerUser(email, name, password);

		verify(userCustomerRepository).save(any());
		
	}
	
	@org.junit.Test(expected =  EmailUserExistedException.class)
	public void registerUserWithExistedEmail() {
		String email = "tester@example.com";
		String name= "Tester";
		String password ="test";
		
		User user= User.builder().build();
		
		given(userCustomerRepository.findByEmail(email)).willReturn(Optional.of(user));
		
		userCustomerService.registerUser(email, name, password);

		verify(userCustomerRepository, never()).save(any());
		
	}

}
