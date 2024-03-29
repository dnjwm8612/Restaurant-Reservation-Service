package kr.co.test.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class User {
	
	@Id @GeneratedValue
	private Long id;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String email;
	
	@NotNull
	private Long level;
	
	@NotEmpty
	private String password;
	
	public boolean isAdmin() {
		return level >=100;
	}

	public boolean isActive() {
		return level > 0;
	}

	public void deactivate() {
		level = 0L;
	}
	
}
