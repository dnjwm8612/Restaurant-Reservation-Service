package kr.co.test.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import kr.co.test.eatgo.application.ReviewService;
import kr.co.test.eatgo.domain.Review;

@RestController
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/restaurants/{restaurantId}/reviews")
	public ResponseEntity<?> create(Authentication authentication,
			@PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody Review resource) throws URISyntaxException {
		Claims claims = (Claims) authentication.getPrincipal();
		
		String name = claims.get("name", String.class);
		Integer score = resource.getScore();
		String description = resource.getDescription();
		Review review = reviewService.addReview(restaurantId, name, score, description);
		
		String url = "/restaurants/"+ restaurantId +"/reviews/"+review.getId();
		return ResponseEntity.created(new URI(url)).body("{}");
	}
}
