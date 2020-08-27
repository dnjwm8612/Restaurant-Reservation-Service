package kr.co.test.eatgo.interfaces;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.test.eatgo.application.UserCustomerService;
import kr.co.test.eatgo.domain.User;

@RunWith(SpringRunner.class)
@WebMvcTest(UserCustomerController.class)
class UserCustomerControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	UserCustomerService userCustomerService;
	
	@Test
	public void create() throws Exception {
		User mockUser = User.builder().id(1004L).email("tester@example.com").name("Tester").password("test").build();
		
		given(userCustomerService.registerUser("tester@example.com", "Tester", "test")).willReturn(mockUser);
		
		mvc.perform(post("/userss").contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"tester@example.com\", \"name\":\"Tester\", \"password\":\"test\"}"))
		.andExpect(status().isCreated()).andExpect(header().string("location", "/userss/1004"));
	
		verify(userCustomerService).registerUser(eq("tester@example.com"), eq("Tester"), eq("test"));
	}

}
