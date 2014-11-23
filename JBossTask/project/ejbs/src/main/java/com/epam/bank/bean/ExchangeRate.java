package com.epam.bank.bean;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExchangeRate implements Serializable{
	
	private static final long serialVersionUID = -2524843811354075501L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Basic
	private String firstCurrency;
	@Basic
	private String secondCurrency;
	@Basic
	private double rate;
	@Basic
	private int bankId;
	
	public ExchangeRate() {
		super();
	}
	public ExchangeRate(String firstCurrency, String secondCurrency,
			double rate) {
		super();
		this.firstCurrency = firstCurrency;
		this.secondCurrency = secondCurrency;
		this.rate = rate;
	}
	public String getFirstCurrency() {
		return firstCurrency;
	}
	public void setFirstCurrency(String firstCurrency) {
		this.firstCurrency = firstCurrency;
	}
	public String getSecondCurrency() {
		return secondCurrency;
	}
	public void setSecondCurrency(String secondCurrency) {
		this.secondCurrency = secondCurrency;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	
}
