package by.kino.jmp;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.log4j.Logger;

import by.kino.jmp.beans.Cinema;
import by.kino.jmp.beans.Film;
import by.kino.jmp.beans.Seat;
import by.kino.jmp.beans.Ticket;
import by.kino.jmp.beans.Visitor;
import by.kino.jmp.services.TicketServiceLocal;

@WebServlet(name="StartupServlet",loadOnStartup=1,urlPatterns={"/*"})
public class StartupServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(StartupServlet.class);

	@EJB
	private TicketServiceLocal ticketManager;

	@Override
	public void init() throws ServletException {
		LOG.info("START");

		Film film = new Film(20000, "Taxi");
		List<Ticket> tickets = new ArrayList<Ticket>();
		for (int i=1; i<=10; i++) {
			for (int j=1; i<=10; j++) {
				tickets.add(new Ticket(film, new Seat(i,j)));
			}
		}
		
	
		Visitor viewer = new Visitor("genstr", "password", new ArrayList<Ticket>(), 70000);
		Cinema cinema = new Cinema(tickets, 0);
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
