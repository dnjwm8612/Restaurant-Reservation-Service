package kr.co.test.eatgo.application;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.test.eatgo.domain.MenuItem;
import kr.co.test.eatgo.domain.MenuItemRepository;
import kr.co.test.eatgo.domain.Restaurant;
import kr.co.test.eatgo.domain.RestaurantRepository;


public class RestaurantServiceTest {
	
	private RestaurantService restaurantService;
	
	@Mock
	private RestaurantRepository restaurantRepository;
	@Mock
	private MenuItemRepository menuItemRepository;
	
	//@Before 인식 오류 인하여 메소드 직접 주입
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		mockRestaurnatRepository();
		mockMenuItemRepository();
		
		restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
	}

	private void mockRestaurnatRepository() {
		List<Restaurant> restaurants  = new ArrayList<Restaurant>();
		Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
		restaurants.add(restaurant);
		given(restaurantRepository.findAll()).willReturn(restaurants);
		
		given(restaurantRepository.findById(1004L)).willReturn(restaurant);
	}
	
	private void mockMenuItemRepository() {
		List<MenuItem> menuItems =  new ArrayList<MenuItem>();
		menuItems.add(new MenuItem("Kimchi"));
		given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
	}
	
	@Test
	public void getRestaurants() {
		setUp();
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		Restaurant restaurant = restaurants.get(0);
		
		assertThat(restaurant.getId(), is(1004L));
	}
	
	@Test
	public void getRestaurant(){
		setUp();
		Restaurant restaurant = restaurantService.getRestaurant(1004L);
		
		assertThat(restaurant.getId(), is(1004L));
	
		MenuItem menuItem = restaurant.getMenuItems().get(0);
		
		assertThat(menuItem.getName(), is("Kimchi"));
	}
	
	@Test 
	public void addRestaurant() {
		setUp();
		Restaurant restaurant = new Restaurant("BeRyong", "Busan");
		Restaurant saved = new Restaurant(1234L, "BeRyong", "Busan");
		
		given(restaurantRepository.save(any())).willReturn(saved);
		
		Restaurant created = restaurantService.addRestaurant(restaurant);
		
		assertThat(created.getId(), is(1234L));
	}
}
