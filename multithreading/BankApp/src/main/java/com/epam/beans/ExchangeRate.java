package com.epam.beans;

import java.io.Serializable;

public class ExchangeRate implements Serializable {
	private static final long serialVersionUID = 8623722920540504427L;
	
	private String firstCurrency;
	private String secondCurrency;
	private double rate;
	
	public ExchangeRate(String firstCurrency, String secondCurrency, double rate) {
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
}
