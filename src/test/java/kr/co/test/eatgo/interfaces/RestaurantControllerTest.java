package kr.co.test.eatgo.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.test.eatgo.application.RestaurantService;
import kr.co.test.eatgo.domain.MenuItemRepository;
import kr.co.test.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.test.eatgo.domain.RestaurantRepository;
import kr.co.test.eatgo.domain.RestaurantRepositoryImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@SpyBean(RestaurantRepositoryImpl.class)
	private RestaurantRepository restaurantrepository;
	
	@SpyBean(MenuItemRepositoryImpl.class)
	private MenuItemRepository menuItemRepository;
	
	@SpyBean(RestaurantService.class)
	private RestaurantService restaurantService;
	
	@Test
	public void list() throws Exception {
		mvc.perform(get("/restaurants"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("\"id\":1004")))
		.andExpect(content().string(containsString("\"name\":\"Bob zip\"")));
	}
	
	@Test 
	public void detail() throws Exception {
		mvc.perform(get("/restaurants/1004"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("\"id\":1004")))
		.andExpect(content().string(containsString("\"name\":\"Bob zip\"")))
		.andExpect(content().string(containsString("Kimchi")));
		
		mvc.perform(get("/restaurants/2020"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("\"id\":2020")))
		.andExpect(content().string(containsString("\"name\":\"Cyber Food\"")));
	}

}
