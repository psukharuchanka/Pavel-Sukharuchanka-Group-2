package com.bank;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.bank.bean.Account;
import com.epam.bank.bean.Balance;
import com.epam.bank.bean.ExchangeRate;
import com.epam.bank.ejb.BankManagerLocal;

@WebServlet( urlPatterns = "/getAccount")
public class AccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private BankManagerLocal bankManager;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Integer id = Integer.valueOf( (String) request.getParameter("id"));
		request.setAttribute("account", bankManager.getAccount(id));
		request.getRequestDispatcher("WEB-INF/account.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf( (String) request.getParameter("id"));
		Account acc = bankManager.getAccount(id);
		String currency = (String) request.getParameter("currency");
		Balance balanceEUR = getBalance("EUR", acc);
		Balance balanceUSD = getBalance("USD", acc);
		ExchangeRate rate = getRate("EUR","USD",bankManager.getExchangeRates(acc.getBankId()));
		if (balanceEUR!=null && balanceUSD!=null && rate!=null){
			if (currency.equals("EURtoUSD") && balanceEUR.getAmount() !=null && balanceEUR.getAmount()>0){	
				balanceUSD.setAmount(rate.getRate()*balanceEUR.getAmount());
				balanceEUR.setAmount(0.0);			
			} else if (currency.equals("USDtoEUR") && balanceUSD.getAmount() !=null && balanceUSD.getAmount()>0){
				balanceEUR.setAmount(balanceUSD.getAmount()/rate.getRate());
				balanceUSD.setAmount(0.0);	
			}
			//bankManager.saveBalance(balanceUSD);
			//bankManager.saveBalance(balanceEUR);
			bankManager.saveAccount(acc);
		}
		request.setAttribute("account", bankManager.getAccount(id));
		request.getRequestDispatcher("WEB-INF/account.jsp").forward(request, response);
	}
	
	private Balance getBalance(String cur,Account acc){		
		for (Balance balance:acc.getBalance()){
			if (balance.getName().equalsIgnoreCase(cur)){
				return balance;
			}
		}
		return null;
	}
	
	private ExchangeRate getRate(String firstCur,String secondCur, List<ExchangeRate> rates){		
		for (ExchangeRate rate:rates){
			if (rate.getFirstCurrency().equalsIgnoreCase(firstCur)
					&& rate.getSecondCurrency().equalsIgnoreCase(secondCur)){
				return rate;
			}
		}
		return null;
	}
	
}
