package kr.co.test.eatgo.application;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.test.eatgo.domain.Reservation;
import kr.co.test.eatgo.domain.ReservationRepository;

class ReservationServiceTest {
	
	ReservationService reservationService;
	
	@Mock
	private ReservationRepository reservationRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		reservationService = new ReservationService(reservationRepository);
	}
	
	@Test
	public void addReservation() {
		Long restaurantId = 369L;
		Long userId= 1004L;
		String name ="John";
		String date="2019-12-24";
		String time = "20:00";
		Integer partySize = 20; 
		
		given(reservationRepository.save(any())).will(invocation -> {Reservation reservation = invocation.getArgument(0);
				return reservation;});
		
		Reservation reservation = reservationService.addReservation(restaurantId, userId, name, date, time, partySize);
		
		assertThat(reservation.getName(), is(name));
	
		verify(reservationRepository).save(any(Reservation.class));
	}

}
