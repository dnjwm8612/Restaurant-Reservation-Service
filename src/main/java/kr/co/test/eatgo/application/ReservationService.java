package kr.co.test.eatgo.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.test.eatgo.domain.Reservation;
import kr.co.test.eatgo.domain.ReservationRepository;

@Service
@Transactional
public class ReservationService {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	public Reservation addReservation(Long restaurantId,Long userId, String name, String date, String time, Integer partySize) {
	  return reservationRepository.save(Reservation.builder().restaurantId(restaurantId).userId(userId).name(name).date(date).time(time).partySize(partySize).build()); 
		
	}

}
