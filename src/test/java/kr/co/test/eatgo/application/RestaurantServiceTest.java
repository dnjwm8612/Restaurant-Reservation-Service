package kr.co.test.eatgo.application;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import kr.co.test.eatgo.domain.Restaurant;
import kr.co.test.eatgo.domain.RestaurantRepository;
import kr.co.test.eatgo.domain.RestaurantRepositoryImpl;

class RestaurantServiceTest {

	private RestaurantService restaurantService;
	private RestaurantRepository restaurantRepository;
	@Before
	public void setUp() {
		restaurantRepository = new RestaurantRepositoryImpl();
		restaurantService = new RestaurantService(restaurantRepository);
	}
	
	@Test
	public void getRestaurant() {
		Restaurant restaurant = restaurantService.getRestaurant(1004L);
		
		assertThat(restaurant.getId(), is(1004L));
	}
	
}
