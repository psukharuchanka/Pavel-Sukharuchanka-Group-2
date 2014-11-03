package com.epam.beans;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Account implements Serializable {
	private static final long serialVersionUID = -2427357576398849680L;
	private static AtomicInteger counter = new AtomicInteger(0);
	
	private int id;
	private String name;
	private Person person;
	private Map<String, Double> balance;
	
	
	public Account(String name, Person person, Map<String, Double> balance) {
		super();
		this.id = counter.addAndGet(1);
		this.name = name;
		this.person = person;
		this.setBalance(balance);
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Map<String, Double> getBalance() {
		return balance;
	}
	public void setBalance(Map<String, Double> balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", person=" + person + ", balance=" + balance + "]";
	}
	
}
