package com.epam.beans;

import java.io.Serializable;
import java.util.List;

public class Bank implements Serializable {
	
	private static final long serialVersionUID = 7112527815451627980L;
	
	private int id;
	private String name;
	private Account account;
	private List<ExchangeRate> rates;
	
	public Bank(int id, String name, Account account, List<ExchangeRate> rates) {
		super();
		this.id = id;
		this.name = name;
		this.account = account;
		this.setRates(rates);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public List<ExchangeRate> getRates() {
		return rates;
	}
	public void setRates(List<ExchangeRate> rates) {
		this.rates = rates;
	}
	
	public ExchangeRate getExchangeRate(String curr1, String curr2) {
		for(ExchangeRate rate : rates) {
			if(rate.getFirstCurrency().equalsIgnoreCase(curr1) && rate.getSecondCurrency().equalsIgnoreCase(curr2)) {
				return rate;
			}
		}
		return null;
	}
	
	public double reduceBalance(double summ, double amount) {
		double result = 0;
		result = summ - amount;
		return result;
	}
	
	public double increaseBalance(double summ, double amount, double rate) {
		double result = 0;
		if (rate != 0) {
			result = summ + amount/rate;
		}
		return result;
	}
}
