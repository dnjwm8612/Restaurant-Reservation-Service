package kr.co.test.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
	
	@Id 
	@GeneratedValue
	@Setter
	private Long id;
	
	@Setter
	@NotEmpty
	private String name;
	
	@NotNull
	@Setter
	private Long categoryId;
	
	@NotEmpty
	private String address;

	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<MenuItem> menuItems;
	
	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Review> review;
	
	
	public String getInformation() {
		return name +" in "+ address ;
	}
	public void setMenuItems(List<MenuItem> menuItems) {
		
		this.menuItems = new ArrayList<>(menuItems);
	}

	public void updateInformation(String name, String address) {
		this.name = name;
		this.address = address;
		
	}
	public void setReviews(List<Review> review) {
		this.review = new ArrayList<Review>(review);
		
	}

	

}
