package by.kino.jmp.services.impl;

import java.util.List;

import javax.ejb.Stateless;

import by.kino.jmp.beans.Cinema;
import by.kino.jmp.beans.Ticket;
import by.kino.jmp.beans.Visitor;
import by.kino.jmp.services.TicketService;
import by.kino.jmp.services.TicketServiceLocal;
import by.kino.jmp.transactions.logic.CompositeTransactionLogic;

@Stateless
public class TicketManager implements TicketService, TicketServiceLocal {

	public void buyTickets(Cinema film, Visitor viewer, List<Ticket> tickets) {
		CompositeTransactionLogic ctl = new CompositeTransactionLogic(film, viewer, tickets);
		ctl.processTransactions();
		
	}
	
}
