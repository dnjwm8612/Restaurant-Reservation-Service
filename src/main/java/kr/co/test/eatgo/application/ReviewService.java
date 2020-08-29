package kr.co.test.eatgo.application;


import org.springframework.stereotype.Service;

import kr.co.test.eatgo.domain.Review;
import kr.co.test.eatgo.domain.ReviewRepository;

@Service
public class ReviewService {
	
	private ReviewRepository reviewRepository;
	
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}
	
	public Review addReview(Long restaurantId, String name, Integer score, String description) {
		Review review = Review.builder().restaurantId(restaurantId).name(name).score(score).description(description).build();
		
		return reviewRepository.save(review);
	}

}
