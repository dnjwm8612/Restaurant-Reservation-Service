package kr.co.test.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MenuItemRepositoryImpl implements MenuItemRepository{

	private List<MenuItem> menuItems = new ArrayList<MenuItem>();
	
	public MenuItemRepositoryImpl() {
		menuItems.add(new MenuItem("Kimchi"));
	}
	
	@Override
	public List<MenuItem> findAllByRestaurantId(Long restaurantId) {
		return menuItems;
	}


}
