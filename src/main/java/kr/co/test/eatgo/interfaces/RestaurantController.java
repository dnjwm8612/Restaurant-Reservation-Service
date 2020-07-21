package kr.co.test.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.test.eatgo.application.RestaurantService;
import kr.co.test.eatgo.domain.Restaurant;

@RestController
public class RestaurantController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/restaurants")
	public List<Restaurant> list() {
		List<Restaurant> restaurants = restaurantService.getRestaurants();
		 return restaurants;
	}
	
	@GetMapping("/restaurants/{id}")
	public Restaurant detail(@PathVariable("id") Long id) {
		Restaurant restaurant = restaurantService.getRestaurant(id);
		return restaurant;
	}
	
	@PostMapping("/restaurants")
	public ResponseEntity<?> create(@RequestBody Restaurant resource) throws URISyntaxException{
		String name = resource.getName();
		String address = resource.getAddress();
		
		Restaurant restaurant = new Restaurant(1234L,  name, address);
		restaurantService.addRestaurant(restaurant);
		
		URI location = new URI("/restaurants/1234");
		return ResponseEntity.created(location).body("{}");
	}
	
}
