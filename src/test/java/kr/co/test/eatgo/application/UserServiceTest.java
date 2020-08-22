package kr.co.test.eatgo.application;

import static org.mockito.BDDMockito.*; 
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.test.eatgo.domain.User;
import kr.co.test.eatgo.domain.UserRepository;

class UserServiceTest {

	UserService userService;
	
	@Mock
	public UserRepository userRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		userService = new UserService(userRepository);
		
	}
	
	@Test
	public void getUser() {
		List<User> users = new ArrayList<User>();
		users.add(User.builder().name("tester").email("tester@example.com").level(1L).build());
		
		given(userService.getUsers()).willReturn(users);
		
		User user = users.get(0);
		assertThat(user.getName(), is("tester"));
		
	}
	
	@Test
	public void addUser() {
		String name="admin";
		String email= "admin@example.com";
		User user = userService.addUser(name, email);
		verify(userRepository).save(any());
		
		assertThat(user.getName(), is("admin"));
	}
	
	@Test
	public void updateUser() {
		Long id= 1004L;
		String name="Superman";
		String email= "admin@example.com";
		Long level= 100L;
		
		User mockUser = User.builder().id(id).email(email).name("admin").level(1L).build();
		
		given(userRepository.findById(id)).willReturn(Optional.of(mockUser));
		
		User user = userService.updateUser(id, name, email, level);
		
		verify(userRepository).findById(eq(id));
		
		assertThat(user.getName(), is("Superman"));
		assertThat(user.isAdmin(), is(true));
		
	}
	
	@Test
	public void deactiveUser() {
		Long id= 1004L;
		
		User mockUser = User.builder().id(id).email("admin@example.com").name("admin").level(100L).build();
		
		given(userRepository.findById(id)).willReturn(Optional.of(mockUser));
		
		
		User user = userService.deactiveUser(1004L);
		
		verify(userRepository).findById(1004L);
		
		assertThat(user.isAdmin(), is(false));
		assertThat(user.isActive(), is(false));
	}
	

}
