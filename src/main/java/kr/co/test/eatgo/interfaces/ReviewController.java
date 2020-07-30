package kr.co.test.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.test.eatgo.application.ReviewService;
import kr.co.test.eatgo.domain.Review;

@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/restaurants/{restaurantId}/reviews")
	public ResponseEntity<?> create(@PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody Review resource ) throws URISyntaxException {		
		Review review = reviewService.addReview(resource);
		
		URI uri = new URI("/restaurants/"+ restaurantId +"/reviews/"+review.getId());
		return ResponseEntity.created(uri).body("{}");
	}
}
