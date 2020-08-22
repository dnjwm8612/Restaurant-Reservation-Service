package kr.co.test.eatgo.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	public void creation() {
		User user = User.builder().name("tester").email("tester@example.com").level(100L).build();
		
		assertThat(user.getName(), is("tester"));
		assertThat(user.isAdmin(), is(true));
		assertThat(user.isActive(), is(true));
		
		user.deactivate();
		
		assertThat(user.isActive(), is(false));
	}

}
