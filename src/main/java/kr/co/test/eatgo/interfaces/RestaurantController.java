package kr.co.test.eatgo.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.test.eatgo.application.RestaurantService;
import kr.co.test.eatgo.domain.MenuItem;
import kr.co.test.eatgo.domain.MenuItemRepository;
import kr.co.test.eatgo.domain.Restaurant;
import kr.co.test.eatgo.domain.RestaurantRepository;

@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/restaurants")
	public List<Restaurant> list() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		 return restaurants;
	}
	
	@GetMapping("/restaurants/{id}")
	public Restaurant detail(@PathVariable("id") Long id) {
		Restaurant restaurant =  restaurantRepository.findById(id);
		
		List<MenuItem> menuItem = menuItemRepository.findAllByRestaurantId(id);
		restaurant.setMenuItem(menuItem);
		
		return restaurant;
	}
	
}
