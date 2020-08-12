package kr.co.test.eatgo.application;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.test.eatgo.domain.Region;
import kr.co.test.eatgo.domain.RegionRepository;

class RegionServiceTest {

	private RegionService regionService;

	@Mock
	private RegionRepository regionRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		regionService = new RegionService(regionRepository);
	}
	
	@Test
	public void getRegions() {
		List<Region> mockRegions = new ArrayList<Region>();
		mockRegions.add(Region.builder().name("Seoul").build());
		
		given(regionRepository.findAll()).willReturn(mockRegions);
		
		List<Region> regions = regionService.getRegions();
		
		Region region = regions.get(0);
		assertThat(region.getName(), is("Seoul"));
	}
	
	@Test
	public void addRegion() {
		Region region = regionService.addRegion("Seoul");
		
		verify(regionRepository).save(any());
		
		assertThat(region.getName(), is("Seoul"));
		
	}

}
