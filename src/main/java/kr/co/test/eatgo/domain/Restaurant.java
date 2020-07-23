package kr.co.test.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

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
	private String name;
	
	private String address;
	
	@Transient
	private List<MenuItem> menuItems;
	
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

	

}
