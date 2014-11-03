package com.epam.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.epam.beans.Bank;

public class BankArithmeticOperationsTest {
	
	private Bank bank;
	
	@Before
	public void initialize() {
		bank = new Bank(0, "test bank", null, null);
	}
	
	@Test
	public void testReduceBalance() {
		assertEquals(900, bank.reduceBalance(1000, 100), 0);
	}
	
	@Test
	public void testIncreaseBalance() {
		assertEquals(1080, bank.increaseBalance(1000, 100, 1.25), 0.0001);
	}
	
}
