package kr.co.test.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor 
public class Reservation {
	
	@Id @GeneratedValue
	private Long id;
	
	private Long restaurantId;
	
	private Long userId;
	
	private String name;

	@NotEmpty
	private String date;
	
	@NotEmpty
	private String time;
	
	@NotNull
	@Min(1)
	private Integer partySize;
}
