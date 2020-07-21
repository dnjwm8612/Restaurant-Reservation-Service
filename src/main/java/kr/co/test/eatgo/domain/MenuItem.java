package kr.co.test.eatgo.domain;

import javax.persistence.Entity;

@Entity
public class MenuItem {
	private String name;
	
	public MenuItem(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
