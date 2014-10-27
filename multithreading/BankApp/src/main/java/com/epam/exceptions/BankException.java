package com.epam.exceptions;

import java.lang.Thread.UncaughtExceptionHandler;

import org.apache.log4j.Logger;

public class BankException implements UncaughtExceptionHandler {
	static final Logger logger = Logger.getLogger(BankException.class);
	
	public void uncaughtException(Thread t, Throwable e) {
		logger.error("Uncaught exception by " + t + " : " + e);
	}

}
