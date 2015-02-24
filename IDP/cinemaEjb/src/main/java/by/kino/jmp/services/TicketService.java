package by.kino.jmp.services;

import java.util.List;

import javax.ejb.Remote;

import by.kino.jmp.beans.Cinema;
import by.kino.jmp.beans.Ticket;
import by.kino.jmp.beans.Visitor;

@Remote
public interface TicketService {

	void buyTickets(Cinema cinema, Visitor viewer, List<Ticket> tickets);

}
