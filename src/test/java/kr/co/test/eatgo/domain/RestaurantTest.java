package kr.co.test.eatgo.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class RestaurantTest {

	@Test
	public void creation() {
		Restaurant restaurant = Restaurant.builder()
				.id(1004L)
				.name("Bob zip")
				.address("Seoul")
				.build();
		
		assertThat(restaurant.getId(), is(1004L));
		assertThat(restaurant.getName(), is("Bob zip"));
		assertThat(restaurant.getAddress(), is("Seoul"));
	}
	
	@Test
	public void  information() {
		Restaurant resturant =  Restaurant.builder().
				id(1004L)
				.name("Bob zip")
				.address("Seoul")
				.build();
		
		assertThat(resturant.getInformation(), is("Bob zip in Seoul"));
	}

}
