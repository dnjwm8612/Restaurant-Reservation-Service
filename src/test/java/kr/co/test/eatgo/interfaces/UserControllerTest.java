package kr.co.test.eatgo.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

}
