package kr.co.test.eatgo.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class RegionTest {

	@Test
	public void creation() {
		Region region = Region.builder().name("����").build();
		assertThat(region.getName(), is("����"));
	}

}
