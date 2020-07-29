package kr.co.test.eatgo.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.test.eatgo.domain.MenuItem;
import kr.co.test.eatgo.domain.MenuItemRepository;

@Service
public class MenuItemService {

	@Autowired
	private MenuItemRepository menuItemRepository;

	public MenuItemService(MenuItemRepository menuItemRepository) {
		this.menuItemRepository = menuItemRepository;
	}
	
	public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
		for(MenuItem menuItem : menuItems) {
			update(restaurantId, menuItem);
			 
		}
	}

	public void update(Long restaurantId, MenuItem menuItem) {
		if(menuItem.isDestroy()) {
			menuItemRepository.deleteById(menuItem.getId());
			return;
		}
		menuItem.setRestaurantId(restaurantId);
		menuItemRepository.save(menuItem);
	}

}
