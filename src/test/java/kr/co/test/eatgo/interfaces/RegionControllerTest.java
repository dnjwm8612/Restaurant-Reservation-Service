package kr.co.test.eatgo.interfaces;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.test.eatgo.application.RegionService;
import kr.co.test.eatgo.domain.Region;

@RunWith(SpringRunner.class)
@WebMvcTest(RegionController.class)
class RegionControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private RegionService regionService;
	
	@Test
	public void list() throws Exception{
		List<Region> regions = new ArrayList<Region>();
		regions.add(Region.builder().name("Seoul").build());
		
		given(regionService.getRegions()).willReturn(regions);
		
		
		mvc.perform(get("/regions"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Seoul")));
	}

	@Test
	public void create() throws Exception {
		Region region = Region.builder().name("Seoul").build();
		given(regionService.addRegion("Seoul")).willReturn(region);
		
		mvc.perform(post("/regions").contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Seoul\"}"))
		.andExpect(status().isCreated())
		.andExpect(content().string("{}"));
		
		verify(regionService).addRegion("Seoul");
		
	}
	
}
