package kr.co.test.eatgo.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class CategoryTest {

	@Test
	public void creation() {
		Category category = Category.builder().name("Korean Food").build();
		
		assertThat(category.getName(), is("Korean Food"));
	}

}
