package kr.co.test.eatgo.interfaces;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.test.eatgo.application.ReservationService;
import kr.co.test.eatgo.domain.Reservation;

@RunWith(SpringRunner.class)
@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	ReservationService reservationService;
	
	@Test
	public void create() throws Exception {
		String token= "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKb2huIn0.8hm6ZOJykSINHxL-rf0yV882fApL3hyQ9-WGlJUyo2A";
		
		Reservation mockReservation = Reservation.builder().id(12L).build();
		
		given(reservationService.addReservation(any(), any(), any(), any(), any(), any())).willReturn(mockReservation);
		
		mvc.perform(post("/restaurants/369/reservation").header("Authorization", "Bearer "+ token).contentType(MediaType.APPLICATION_JSON)
				.content("{\"date\":\"2019-12-24\", \"time\":\"20:00\", \"partySize\":20}"))
				.andExpect(status().isCreated());
		
		Long restaurantId = 369L;
		Long userId= 1004L;
		String name ="John";
		String date="2019-12-24";
		String time = "20:00";
		Integer partySize = 20; 
		
		verify(reservationService).addReservation(eq(restaurantId),eq(userId), eq(name), eq(date), eq(time),eq(partySize));
	}

}
