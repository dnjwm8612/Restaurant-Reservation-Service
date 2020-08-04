package kr.co.test.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
	
	@Id 
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotNull
	private Integer score;
	
	@NotEmpty
	private String description;

	private Long restaurantId; 
}
