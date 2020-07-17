package kr.co.test.eatgo.interfaces;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void list() throws Exception {
		mvc.perform(get("/restaurant")).andExpect(status().isOk());
	}

}
