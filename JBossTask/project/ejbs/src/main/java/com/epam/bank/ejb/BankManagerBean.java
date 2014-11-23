package com.epam.bank.ejb;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.epam.bank.bean.Account;
import com.epam.bank.bean.Balance;
import com.epam.bank.bean.Bank;
import com.epam.bank.bean.ExchangeRate;

@Stateless
@DeclareRoles("bean")
public class BankManagerBean implements BankManager, BankManagerLocal {
    @PersistenceContext
    private EntityManager em;

    @RolesAllowed("bean")
	public void createBank() {
    	Balance b = new Balance();
    	b.setAmount(100.0);
    	b.setName("eur");
    	Account a = new Account();
    	a.setName("account1");
    	Set <Balance> set =new HashSet<Balance>();
    	set.add(b);
    	a.setBalance(set);
    	Set<ExchangeRate> rates = new HashSet<ExchangeRate>();
 		rates.add(new ExchangeRate("eur", "usd", 1.23));
 		Set<Account> accounts = new HashSet<Account>();
 		accounts.add(a);
 		Bank bank = new Bank(accounts, rates);
        em.persist(bank);
	}

	public List<Bank> list() {
	    Query query = em.createQuery("from Bank");
	    return query.getResultList();
	}

	public Account getAccount(int id) {
		Query query = em.createQuery("from Account where id = :id");
		query.setParameter("id", id);	
	    return (Account) query.getResultList().get(0);
	}
	
	public List<ExchangeRate> getExchangeRates(int bankId) {
		Query query = em.createQuery("from ExchangeRate where bankId = :id");
		query.setParameter("id", bankId);	
	    return  query.getResultList();
	}

	public void saveAccount(Account a) {
		em.merge(a);
	}
	
	public void saveBalance(Balance b) {
		em.merge(b);
	}

}
