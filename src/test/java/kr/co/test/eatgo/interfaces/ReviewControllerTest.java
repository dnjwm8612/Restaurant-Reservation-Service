package kr.co.test.eatgo.interfaces;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.test.eatgo.application.ReviewService;
import kr.co.test.eatgo.domain.Review;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ReviewService reviewService;
	
	@Test
	public void createWithValidAttriutes() throws Exception {
		String token= "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A";
		given(reviewService.addReview(1L, "John", 3, "Mat-it-da")).willReturn(Review.builder().id(1004L).build());
		
		mvc.perform(post("/restaurants/1/reviews").header("Authorization", "Bearer "+token).contentType(MediaType.APPLICATION_JSON).content("{\"score\":3, \"description\":\"Mat-it-da\"}"))
		.andExpect(status().isCreated())
		.andExpect(header().string("location", "/restaurants/1/reviews/1004"));
		
		verify(reviewService).addReview(eq(1L), eq("John"), eq(3), eq("Mat-it-da"));
	}
	
	@Test
	public void createWithInvalidAttriutes() throws Exception {
		mvc.perform(post("/restaurants/1/reviews").contentType(MediaType.APPLICATION_JSON).content("{}"))
		.andExpect(status().isBadRequest());
		
		verify(reviewService, never()).addReview( any(), any(), any(), any());
	}
}
