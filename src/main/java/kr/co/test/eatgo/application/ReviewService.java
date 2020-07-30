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
	
	public Review addReview(Review review) {
		reviewRepository.save(review);
		return review;
	}

}
