package kr.co.test.eatgo.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
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

import kr.co.test.eatgo.application.RestaurantService;
import kr.co.test.eatgo.domain.MenuItem;
import kr.co.test.eatgo.domain.Restaurant;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private RestaurantService restaurantService;
		
	@Test
	public void list() throws Exception {
		List<Restaurant> restaurants = new ArrayList<Restaurant>();
		restaurants.add(new Restaurant(1004L, "JOKER House", "Seoul"));
		
		given(restaurantService.getRestaurants()).willReturn(restaurants);
		
		mvc.perform(get("/restaurants"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("\"id\":1004")))
		.andExpect(content().string(containsString("\"name\":\"JOKER House\"")));
	}
	
	@Test 
	public void detail() throws Exception {
		Restaurant restaurant = new Restaurant(1004L, "JOKER House", "Seoul");
		Restaurant restaurant2 = new Restaurant(2020L, "Cyber Food", "Seoul");
		
		restaurant.addMenuItem(new MenuItem("Kimchi"));
		given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
		given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);
		
		mvc.perform(get("/restaurants/1004"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("\"id\":1004")))
		.andExpect(content().string(containsString("\"name\":\"JOKER House\"")))
		.andExpect(content().string(containsString("Kimchi")));
		
		mvc.perform(get("/restaurants/2020"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("\"id\":2020")))
		.andExpect(content().string(containsString("\"name\":\"Cyber Food\"")));
	}

	@Test
	public void create() throws Exception {
		mvc.perform(post("/restaurants").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"BeRyong\", \"address\":\"Busan\"}"))
		.andExpect(status().isCreated())
		.andExpect(header().string("location", "/restaurants/1234"))
		.andExpect(content().string("{}"));
		
		verify(restaurantService).addRestaurant(any());
		}
}
