package kr.co.test.eatgo.application;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aopalliance.intercept.Invocation;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.test.eatgo.domain.MenuItem;
import kr.co.test.eatgo.domain.MenuItemRepository;
import kr.co.test.eatgo.domain.Restaurant;
import kr.co.test.eatgo.domain.RestaurantNotFoundException;
import kr.co.test.eatgo.domain.RestaurantRepository;
import net.bytebuddy.agent.builder.AgentBuilder.RawMatcher.Inversion;


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
		Restaurant restaurant = Restaurant.builder().id(1004L).name("Bob zip").address("Seoul").build();
		restaurants.add(restaurant);
		given(restaurantRepository.findAll()).willReturn(restaurants);
		
		given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
	}
	
	private void mockMenuItemRepository() {
		List<MenuItem> menuItems =  new ArrayList<MenuItem>();
		menuItems.add(MenuItem.builder().name("Kimchi").build());
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
	public void getRestaurantWithExisted(){
		setUp();
		Restaurant restaurant = restaurantService.getRestaurant(1004L);
		
		assertThat(restaurant.getId(), is(1004L));
	
		MenuItem menuItem = restaurant.getMenuItems().get(0);
		
		assertThat(menuItem.getName(), is("Kimchi"));
	}
	
	@org.junit.Test(expected =  RestaurantNotFoundException.class)
	public void getRestaurantWithNotExisted(){
		setUp();
		restaurantService.getRestaurant(404L);
		}
	
	
	@Test 
	public void addRestaurant() {
		setUp();
		given(restaurantRepository.save(any())).will(invocation -> { 
			Restaurant restaurant = invocation.getArgument(0);
			restaurant.setId(1234L);
			return restaurant;
		});
		Restaurant restaurant = Restaurant.builder().name("BeRyong").address("Busan").build();
		Restaurant saved = Restaurant.builder().id(1234L).name("BeRyong").address("Busan").build();
		
		Restaurant created = restaurantService.addRestaurant(restaurant);
		
		assertThat(created.getId(), is(1234L));
	}
	
	@Test
	public void updateRestaurant() {
		setUp();
		Restaurant restaurant = Restaurant.builder().id(1004L).name("Bob zip").address("Seoul").build();
		
		given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

		Restaurant updated = restaurantService.updateRestaurant(1004L, "Sool zip", "Busan");
		
		assertThat(updated.getName(), is("Sool zip"));
		assertThat(updated.getAddress(), is("Busan"));
	}
}
