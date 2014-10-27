package com.epam.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

import com.epam.beans.Account;
import com.epam.beans.Bank;
import com.epam.beans.ExchangeRate;
import com.epam.beans.Person;

public class FileGenerator implements Runnable {
	static final Logger logger = Logger.getLogger(FileGenerator.class);
	BlockingQueue<Bank> queue;
	
	public FileGenerator(BlockingQueue<Bank> queue) {
		this.queue = queue;
	}
	
	public void run() {
		// add initial data
		Person person = new Person("Siarhei", "Ivashkou");
		Map<String, Double> balance = new HashMap<String, Double>();
		balance.put("usd", (double)1000);
		balance.put("eur", (double)1000);
		
		Account account = new Account("deposit", person, balance);
		List<ExchangeRate> rates = new ArrayList<ExchangeRate>();
		rates.add(new ExchangeRate("eur", "usd", 1.258));
		Bank bank = new Bank(1, "Prior", account, rates);
		try {
			queue.put(bank);
		} catch (InterruptedException e) {
			logger.info("Can't add to queue", e);
		}
		logger.info("Bank added to queue");
	}

}
