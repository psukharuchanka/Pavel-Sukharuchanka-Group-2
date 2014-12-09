package com.epam.jmp.jpa.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String country;
	private String sity;
	private String street;
	private String house;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSity() {
		return sity;
	}
	public void setSity(String sity) {
		this.sity = sity;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	
}
