package kr.co.test.eatgo.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import kr.co.test.eatgo.application.UserService;
import kr.co.test.eatgo.domain.User;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userSerivce;

	@Test
	public void list() throws Exception {
		List<User> users = new ArrayList<User>();
		users.add(User.builder().name("tester").email("tester@example.com").level(1L).build());
		
		given(userSerivce.getUsers()).willReturn(users);
		
		mvc.perform(get("/users"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("tester")));
	}
	
	@Test
	public void create() throws Exception {
		String name ="admin";
		String email = "admin@example.com";
		
		User user = User.builder().name(name).email(email).build();
		given(userSerivce.addUser(name, email)).willReturn(user);
		
		mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"admin\", \"email\":\"admin@example.com\"}"))
		.andExpect(status().isCreated())
		.andExpect(content().string(containsString("")));
		
		verify(userSerivce).addUser(user.getName(), user.getEmail());
	} 

	@Test
	public void update() throws Exception {
		mvc.perform(patch("/users/1004").contentType(MediaType.APPLICATION_JSON).content("{\"id\": 1004,\"name\":\"admin\", \"email\":\"admin@example.com\", \"level\": 100}"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("")));
		
		Long id = 1004L;
		String name ="admin";
		String email = "admin@example.com";
		Long level = 100L;
		
		verify(userSerivce).updateUser(eq(id), eq(name), eq(email), eq(level));
		
	}
	
	@Test
	public void deactivete() throws Exception {
		mvc.perform(delete("/users/1004"))
		.andExpect(status().isOk());

		verify(userSerivce).deactiveUser(1004L);
	}
	
}
