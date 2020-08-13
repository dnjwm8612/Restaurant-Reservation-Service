package kr.co.test.eatgo.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
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
import kr.co.test.eatgo.domain.RestaurantNotFoundException;
import kr.co.test.eatgo.domain.Review;

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
		restaurants.add(Restaurant.builder().id(1004L).name("JOKER House").address("Seoul").build());
		
		given(restaurantService.getRestaurants("Seoul")).willReturn(restaurants);
		
		mvc.perform(get("/restaurants?region=Seoul"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("\"id\":1004")))
		.andExpect(content().string(containsString("\"name\":\"JOKER House\"")));
	}
	
	@Test 
	public void detailWithExisted() throws Exception {
		Restaurant restaurant = Restaurant.builder().id(1004L).name("JOKER House").address("Seoul").build();
		
		MenuItem menuItem = MenuItem.builder().name("Kimchi").build();
		
		restaurant.setMenuItems(Arrays.asList(menuItem));
		Review review = Review.builder().name("JOKER").score(5).description("Great!").build();
		
		restaurant.setReviews(Arrays.asList(review));
		
		
		given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
		
		mvc.perform(get("/restaurants/1004"))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("\"id\":1004")))
		.andExpect(content().string(containsString("\"name\":\"JOKER House\"")))
		.andExpect(content().string(containsString("Kimchi")))
		.andExpect(content().string(containsString("Great!")));
		

	}
	
	@Test
	public void deteleWithNotExisted() throws Exception {
		given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));
		mvc.perform(get("/restaurants/404"))
		.andExpect(status().isNotFound())
		.andExpect(content().string("{}"));
	}

	@Test
	public void createWithValidData() throws Exception {
		given(restaurantService.addRestaurant(any())).will(invacation->{
			Restaurant restaurant = invacation.getArgument(0);
			return Restaurant.builder().id(1234L).name(restaurant.getName()).address(restaurant.getAddress()).build();
		});
		
		mvc.perform(post("/restaurants").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"BeRyong\", \"address\":\"Busan\"}"))
		.andExpect(status().isCreated())
		.andExpect(header().string("location", "/restaurants/1234"))
		.andExpect(content().string("{}"));
		
		verify(restaurantService).addRestaurant(any());
		}
	
	@Test
	public void createWithInvalidData() throws Exception {
		mvc.perform(post("/restaurants").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"\", \"address\":\"\"}"))
				.andExpect(status().isBadRequest());
		}
	
	@Test
	public void updateWithValidData() throws Exception {
		mvc.perform(patch("/restaurants/1004").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"JOKER Bar\", \"address\":\"Busan\"}"))
		.andExpect(status().isOk());
		
		verify(restaurantService).updateRestaurant(1004L,"JOKER Bar", "Busan");
		
	}
	public void updateWithInvalidData() throws Exception {
		mvc.perform(patch("/restaurants/1004").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"\", \"address\":\"\"}"))
		.andExpect(status().isBadRequest());
		
	}
}
