package com.epam.beans;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class Person implements Serializable{
	private static final long serialVersionUID = -4305801834806167465L;
	private static AtomicInteger counter = new AtomicInteger(0);
	
	private int id;
	private String firstName;
	private String lastName;
	
	public Person(String firstName, String lastName) {
		super();
		this.id = counter.addAndGet(1);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
