package kr.co.test.eatgo.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.test.eatgo.domain.MenuItem;
import kr.co.test.eatgo.domain.MenuItemRepository;
import kr.co.test.eatgo.domain.Restaurant;
import kr.co.test.eatgo.domain.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	MenuItemRepository menuItemRepository;
	
	public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
		this.restaurantRepository = restaurantRepository;
		this.menuItemRepository = menuItemRepository;
	}

	public List<Restaurant> getRestaurants(){
		List<Restaurant> restaurants = restaurantRepository.findAll();
		return restaurants;
	}
	
	public Restaurant getRestaurant(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id);
		
		List<MenuItem> menuItem = menuItemRepository.findAllByRestaurantId(id);
		restaurant.setMenuItem(menuItem);
				
		return restaurant;
	}
}
