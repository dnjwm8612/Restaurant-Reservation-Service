package kr.co.test.eatgo.application;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.test.eatgo.domain.MenuItem;
import kr.co.test.eatgo.domain.MenuItemRepository;
import kr.co.test.eatgo.domain.Restaurant;
import kr.co.test.eatgo.domain.RestaurantNotFoundException;
import kr.co.test.eatgo.domain.RestaurantRepository;
import kr.co.test.eatgo.domain.Review;
import kr.co.test.eatgo.domain.ReviewRepository;


public class RestaurantServiceTest {
	
	private RestaurantService restaurantService;
	
	@Mock
	private RestaurantRepository restaurantRepository;
	@Mock
	private MenuItemRepository menuItemRepository;
	
	@Mock
	private ReviewRepository reviewRepository;
	
	//@Before 인식 오류 인하여 메소드 직접 주입
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		mockRestaurnatRepository();
		mockMenuItemRepository();
		mockReviewRepository();
		
		restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);
	}

	private void mockRestaurnatRepository() {
		List<Restaurant> restaurants  = new ArrayList<Restaurant>();
		Restaurant restaurant = Restaurant.builder().id(1004L).categoryId(1L).name("Bob zip").address("Seoul").build();
		restaurants.add(restaurant);
		given(restaurantRepository.findAllByAddressContainingAndCategoryId("Seoul", 1L)).willReturn(restaurants);
		
		given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
	}
	
	private void mockMenuItemRepository() {
		List<MenuItem> menuItems =  new ArrayList<MenuItem>();
		menuItems.add(MenuItem.builder().name("Kimchi").build());
		given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
	}
	
	private void mockReviewRepository() {
		List<Review> reviews = new ArrayList<Review>();  
		reviews.add(Review.builder().name("BeRyong").score(1).description("Bad").build());
		
		given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);
	}
	
	@Test
	public void getRestaurants() {
		setUp();
		String region = "Seoul";
		Long categoryId= 1L;
		List<Restaurant> restaurants = restaurantService.getRestaurants(region, categoryId);
		
		Restaurant restaurant = restaurants.get(0);
		
		assertThat(restaurant.getId(), is(1004L));
	}
	
	@Test
	public void getRestaurantWithExisted(){
		setUp();
		Restaurant restaurant = restaurantService.getRestaurant(1004L);
		
		verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
		verify(reviewRepository).findAllByRestaurantId(eq(1004L));
		
		assertThat(restaurant.getId(), is(1004L));
	
		MenuItem menuItem = restaurant.getMenuItems().get(0);
		
		Review review = restaurant.getReview().get(0);
		
		assertThat(menuItem.getName(), is("Kimchi"));
		assertThat(review.getDescription(), is("Bad"));
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
