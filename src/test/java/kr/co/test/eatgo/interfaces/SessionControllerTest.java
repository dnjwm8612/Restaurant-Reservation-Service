package kr.co.test.eatgo.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.test.eatgo.application.EmailNotExistedException;
import kr.co.test.eatgo.application.PasswordWrongException;
import kr.co.test.eatgo.application.UserCustomerService;
import kr.co.test.eatgo.domain.User;
import kr.co.test.eatgo.utils.JwtUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
class SessionControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	JwtUtil jwtUtil;
	
	@MockBean
	UserCustomerService userCustomerService;
	
	@Test
	public void createWithValidAttributes() throws Exception {		
		Long id = 1004L;
		String email = "tester@example.com";
		String name = "Tester";
		String password = "test";
		
		
		User mockUser  = User.builder().id(id).name(name).build();
		given(userCustomerService.authenticate(email, password)).willReturn(mockUser);
		
		given(jwtUtil.createToken(id, name)).willReturn("header.payload.signature");
		
		mvc.perform(post("/session").contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"tester@example.com\", \"password\":\"test\"}"))
		.andExpect(status().isCreated()).andExpect(header().string("location", "/session"))
		.andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"}")))
		.andExpect(content().string(containsString(".")));
		
		verify(userCustomerService).authenticate(eq(email), eq(password));
	
	}
	
	@Test
	public void createWithNotExistedEmail() throws Exception {		
		given(userCustomerService.authenticate("x@example.com", "test")).willThrow(EmailNotExistedException.class);
		
		mvc.perform(post("/session").contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"x@example.com\", \"password\":\"test\"}"))
		.andExpect(status().isBadRequest());
		
		verify(userCustomerService).authenticate(eq("x@example.com"), eq("test"));
	
	}
	
	@Test
	public void createWithWrongPassword() throws Exception {		
		given(userCustomerService.authenticate("tester@example.com", "x")).willThrow(PasswordWrongException.class);
		
		mvc.perform(post("/session").contentType(MediaType.APPLICATION_JSON).content("{\"email\":\"tester@example.com\", \"password\":\"x\"}"))
		.andExpect(status().isBadRequest());
		
		verify(userCustomerService).authenticate(eq("tester@example.com"), eq("x"));
	
	}
	
}
