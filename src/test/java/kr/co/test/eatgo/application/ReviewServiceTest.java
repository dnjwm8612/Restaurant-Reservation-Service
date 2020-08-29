package kr.co.test.eatgo.application;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.test.eatgo.domain.Review;
import kr.co.test.eatgo.domain.ReviewRepository;

class ReviewServiceTest {
	
	private ReviewService reviewService;
	
	@Mock
	private ReviewRepository reviewRepository;
	
	//@Before 오류로 직접 사용
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		reviewService = new ReviewService(reviewRepository);
		
	}
	
	@Test
	public void addReview() {
		setUp();
			
		reviewService.addReview(1004L, "JOKER", 3, "Mat-it-da");
		
		verify(reviewRepository).save(any());
	}

}
