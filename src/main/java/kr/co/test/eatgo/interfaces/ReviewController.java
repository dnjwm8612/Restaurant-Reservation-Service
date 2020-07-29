package kr.co.test.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.test.eatgo.application.ReviewService;
import kr.co.test.eatgo.domain.Review;

@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/restaurants/{restaurantId}/reviews")
	public ResponseEntity<?> create() throws URISyntaxException {
		Review review = Review.builder().build();
		
		reviewService.addReview(review);
		
		return ResponseEntity.created(new URI("/restaurants/1/reviews")).body("{}");
	}
}
