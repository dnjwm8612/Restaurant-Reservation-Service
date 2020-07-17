package kr.co.test.eatgo.application;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import kr.co.test.eatgo.domain.MenuItem;
import kr.co.test.eatgo.domain.MenuItemRepository;
import kr.co.test.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.test.eatgo.domain.Restaurant;
import kr.co.test.eatgo.domain.RestaurantRepository;
import kr.co.test.eatgo.domain.RestaurantRepositoryImpl;

class RestaurantServiceTest {
	
	private RestaurantService restaurantService;
	private RestaurantRepository restaurantRepository;
	private MenuItemRepository menuItemRepository;
	
	@Before
	public void setUp() {
		restaurantRepository = new RestaurantRepositoryImpl();
		menuItemRepository = new MenuItemRepositoryImpl();
		restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
	}
	
	@Test
	public void getRestaurants() {
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		Restaurant restaurant = restaurants.get(0);
		
		assertThat(restaurant.getId(), is(1004L));
	}
	
	@Test
	public void getRestaurant(){
		Restaurant restaurant = restaurantService.getRestaurant(1004L);
		
		assertThat(restaurant.getId(), is(1004L));
	
		MenuItem menuItem = restaurant.getMenuItems().get(0);
		
		assertThat(menuItem.getName(), is("Kimchi"));
	}
}
