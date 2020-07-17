package kr.co.test.eatgo.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class RestaurantTest {

	@Test
	public void creation() {
		Restaurant restaurant = new Restaurant("Bob zip", "Seoul");
		assertThat(restaurant.getName(), is("Bob zip"));
		assertThat(restaurant.getAddress(), is("Seoul"));
	}
	
	@Test
	public void  information() {
		Restaurant resturant =  new  Restaurant("Bob zip", "Seoul");
		assertThat(resturant.getInformation(), is("Bob zip in Seoul"));
	}

}
