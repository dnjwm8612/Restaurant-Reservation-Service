package kr.co.test.eatgo.domain;

import java.util.List;

public interface MenuItemRepository {

	List<MenuItem>findAllByRestaurantId(Long restaurantId);

}
