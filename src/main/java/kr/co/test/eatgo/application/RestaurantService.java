package kr.co.test.eatgo.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.test.eatgo.domain.*;

@Service
public class RestaurantService {
	
	private RestaurantRepository restaurantRepository;
	private MenuItemRepository menuItemRepository;
	private ReviewRepository reviewRepository;
	
	@Autowired
	public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, ReviewRepository reviewRepository) {
		this.restaurantRepository = restaurantRepository;
		this.menuItemRepository = menuItemRepository;
		this.reviewRepository= reviewRepository;
	}

	public List<Restaurant> getRestaurants(String region, Long categoryId){
		
		
		List<Restaurant> restaurants = restaurantRepository.findAllByAddressContainingAndCategoryId(region, categoryId);
		
		return restaurants;
	}
	
	public Restaurant getRestaurant(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(()->new RestaurantNotFoundException(id));
		
		List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
		restaurant.setMenuItems(menuItems);
		
		List<Review> review = reviewRepository.findAllByRestaurantId(id);
		restaurant.setReviews(review);
		
		return restaurant;
	}

	public Restaurant addRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Transactional
	public Restaurant updateRestaurant(Long id, String name, String address) {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
		
		restaurant.updateInformation(name, address);
		
		return restaurant;
		
	}
}
