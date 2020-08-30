package kr.co.test.eatgo.application;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReservationService {

	public void addReservation( Long restaurantId,Long userId, String name, String date, String time, Integer partySize) {
		
		
	}

}
