package com.epam.bank.ejb;

import java.util.List;

import javax.ejb.Local;

import com.epam.bank.bean.Account;
import com.epam.bank.bean.Balance;
import com.epam.bank.bean.Bank;
import com.epam.bank.bean.ExchangeRate;

@Local
public interface BankManagerLocal {
    void createBank();

    public List<Bank> list();
    
    public Account getAccount(int id);
    
    public void saveAccount(Account a);
    
    public List<ExchangeRate> getExchangeRates(int bankId);
    
    public void saveBalance(Balance b);
}
