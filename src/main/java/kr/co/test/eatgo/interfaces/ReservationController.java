package kr.co.test.eatgo.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import kr.co.test.eatgo.application.ReservationService;
import kr.co.test.eatgo.domain.Reservation;

@RestController
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@PostMapping("/restaurants/{restaurantId}/reservation")
	public ResponseEntity<?> create(Authentication authentication ,@PathVariable("restaurantId") Long restaurantId, @RequestBody Reservation resource) throws URISyntaxException {
		Claims claims = (Claims) authentication.getPrincipal();
		
		Long userId= claims.get("userId", Long.class);
		String name =claims.get("name", String.class);
		String date= resource.getDate();
		String time = resource.getTime();
		Integer partySize = resource.getPartySize(); 
		
		reservationService.addReservation(restaurantId, userId, name, date, time, partySize);
		
		String url = "/restaurants/"+restaurantId+"/reservation/1";
		return ResponseEntity.created(new URI(url)).body("{}");
	}
	
}
