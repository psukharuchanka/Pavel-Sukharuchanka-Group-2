package by.kino.jmp.services;

import java.util.List;

import javax.ejb.Local;

import by.kino.jmp.beans.Cinema;
import by.kino.jmp.beans.Ticket;
import by.kino.jmp.beans.Visitor;

@Local
public interface TicketServiceLocal {

	void buyTickets(Cinema cinema, Visitor viewer, List<Ticket> tickets);

}
