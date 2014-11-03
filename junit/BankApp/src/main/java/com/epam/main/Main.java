package com.epam.main;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

import com.epam.beans.Account;
import com.epam.beans.Bank;
import com.epam.beans.ExchangeRate;
import com.epam.concurrency.BankReader;
import com.epam.concurrency.BankWriter;
import com.epam.concurrency.FileGenerator;
import com.epam.exceptions.BankException;

public class Main {
	static final Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Bank> queue = new ArrayBlockingQueue<Bank>(1024);
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		File data = new File("data.ser");
		// initial thread - will put data to queue
		Thread initialDataThread = new Thread(new FileGenerator(queue));
		initialDataThread.start();
		if(initialDataThread.isAlive()) {
			try {
				initialDataThread.join();
			} catch(InterruptedException e) {
				logger.error("Can't wait the finishing of initia1lDataThread", e);
			}
		}
		
		BankWriter writer = new BankWriter(data, lock, queue);
		Thread writerThread = new Thread(writer);
		writerThread.setUncaughtExceptionHandler(new BankException());
		writerThread.start();
		
		// client
		int choice = 0;
		Scanner scanner = new Scanner(System.in);
		Bank bank = null;
		do {
			logger.info("[1] show accounts");
			logger.info("[2] exchange money");
			logger.info("[3] exit");
			choice = scanner.nextInt();
			
			switch (choice) {
				case 1:
					BankReader reader = new BankReader(data, lock);
					bank = reader.read();
					if (bank != null && bank.getAccount() != null) {
						logger.info("Bank Account : " + bank.getAccount());
					}
					break;
				case 2:
					if(bank != null) {
						Account account = bank.getAccount();
						logger.info("Enter FROM currency");
						String currencyFrom = scanner.next();
						if(account.getBalance().containsKey(currencyFrom)) {
							logger.info("Enter TO currency : ");
							String currencyTo = scanner.next();
							if(account.getBalance().containsKey(currencyFrom)) {
								logger.info("Enter transfer amount");
								double amount = scanner.nextDouble();
								double balanceFrom = account.getBalance().get(currencyFrom);
								if(amount > 0 && balanceFrom >= amount) {
									double balanceTo = account.getBalance().get(currencyTo);
									ExchangeRate rate = bank.getExchangeRate(currencyFrom, currencyTo);
									if(rate != null) {
										balanceFrom = bank.reduceBalance(balanceFrom, amount);
										balanceTo = bank.increaseBalance(balanceTo, amount, rate.getRate());
										
										//bank.getAccount().getBalance().remove(currencyFrom);
										bank.getAccount().getBalance().put(currencyFrom, balanceFrom);
										//bank.getAccount().getBalance().remove(currencyTo);
										bank.getAccount().getBalance().put(currencyTo, balanceTo);
										logger.info("put to queue...");
										queue.put(bank);
									}
								}
							} else {
								logger.info("Currency is not valid");
							}
						} else {
							logger.info("Currency is not valid");
						}
					} else {
						logger.info("Bank don't have account");
					}
					break;
				case 3:
					logger.info("----------------------");
					break;			
			}
		} while(choice != 3);
		
		writerThread.interrupt();
	}
	

}
