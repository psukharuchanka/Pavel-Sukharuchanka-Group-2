package com.epam.bank.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Bank implements Serializable{ 
	private static final long serialVersionUID = 7863262235394607247L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="bankId")
	private Set<Account> accounts;
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name="bankId")
	private Set<ExchangeRate> rates;
	
	public Bank() {
		super();		
	}
	public Bank(Set<Account> accounts, Set<ExchangeRate> rates) {
		super();
		this.accounts = accounts;
		this.rates = rates;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	public Set<ExchangeRate> getRates() {
		return rates;
	}
	public void setRates(Set<ExchangeRate> rates) {
		this.rates = rates;
	}
}
