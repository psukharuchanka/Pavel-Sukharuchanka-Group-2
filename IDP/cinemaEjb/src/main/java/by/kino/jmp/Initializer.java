package by.kino.jmp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;

import by.kino.jmp.beans.Cinema;
import by.kino.jmp.beans.Film;
import by.kino.jmp.beans.Seat;
import by.kino.jmp.beans.Ticket;
import by.kino.jmp.beans.Visitor;
import by.kino.jmp.services.impl.TicketManager;

@Singleton
@Startup
public class Initializer {
	
	private static final Logger LOG = Logger.getLogger(Initializer.class);

	private static Cinema cinema;
	private static Visitor viewer;
	private static Film film;

	@PostConstruct
    private static void postConstruct() {
		System.out.println("INITIALIZATION");
		film = new Film(20000, "Taxi");
		List<Ticket> tickets = new ArrayList<Ticket>();
		for (int i=1; i<=10; i++) {
			for (int j=1; i<=10; j++) {
				tickets.add(new Ticket(film, new Seat(i,j)));
			}
		}
		
		viewer = new Visitor("genstr", "password", new ArrayList<Ticket>(), 70000);
		cinema = new Cinema(tickets, 0);
	}
	
	public static void main(String[] args){
		LOG.info("START");
		TicketManager ticketManager = new TicketManager();
		postConstruct();

		List<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(new Ticket(film,new Seat(1,2)));
		tickets.add(new Ticket(film,new Seat(1,3)));
		
		ticketManager.buyTickets(cinema, viewer, tickets);
		LOG.debug("Cinema: " + cinema);
		LOG.debug("Viewer" + viewer);

		tickets = new ArrayList<Ticket>();
		tickets.add(new Ticket(film,new Seat(12,2)));


		ticketManager.buyTickets(cinema, viewer, tickets);
		LOG.debug(cinema);
		LOG.debug(viewer);

		tickets = new ArrayList<Ticket>();
		tickets.add(new Ticket(film,new Seat(3,2)));
		tickets.add(new Ticket(film,new Seat(3,3)));
		tickets.add(new Ticket(film,new Seat(4,2)));
		tickets.add(new Ticket(film,new Seat(4,3)));

		ticketManager.buyTickets(cinema, viewer, tickets);
		LOG.debug(cinema);
		LOG.debug(viewer);
	}
}
