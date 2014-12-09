package com.epam.jmp.jpa.model;

import javax.persistence.Entity;

@Entity
public class PersonalInfo extends AbstractEntity {
	
	private String driverLicense;

	public String getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}
}
