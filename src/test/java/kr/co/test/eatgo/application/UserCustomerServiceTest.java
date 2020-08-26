package kr.co.test.eatgo.application;

import static org.hamcrest.MatcherAssert.*; 
import static org.mockito.ArgumentMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

import java.security.spec.PSSParameterSpec;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.test.eatgo.domain.User;
import kr.co.test.eatgo.domain.UserCustomerRepository;

class UserCustomerServiceTest {

	UserCustomerService userCustomerService;
	
	@Mock
	UserCustomerRepository userCustomerRepository;
	
	@Mock 
	PasswordEncoder passwordEncoder;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		userCustomerService = new UserCustomerService(userCustomerRepository, passwordEncoder);
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
	
	@Test
	public void authenticateWithValidAttributes() {
		String email = "tester@example.com";
		String password ="test";
		
		User mockUser= User.builder().email(email).build();
		given(userCustomerRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
		
		given(passwordEncoder.matches(any(), any())).willReturn(true);
		
		User user = userCustomerService.authenticate(email, password);
		
		assertThat(user.getEmail(), is(email));
	}
	
	@org.junit.Test(expected = EmailNotExistedException.class)
	public void authenticateWithNotExistedEmail() {
		String email = "x@example.com";
		String password ="test";
		
		given(userCustomerRepository.findByEmail(email)).willReturn(Optional.empty());
		
		userCustomerService.authenticate(email, password);
		
	}
	
	@org.junit.Test(expected = PasswordWrongException.class)
	public void authenticateWithWrongPassword() {
		String email = "tester@example.com";
		String password ="x";
		
		User mockUser= User.builder().email(email).build();
		
		given(userCustomerRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
		
		given(passwordEncoder.matches(any(), any())).willReturn(false);
		
		userCustomerService.authenticate(email, password);
	}

}
