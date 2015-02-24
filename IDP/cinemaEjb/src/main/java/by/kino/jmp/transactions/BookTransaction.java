package by.kino.jmp.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

import by.kino.jmp.beans.Cinema;
import by.kino.jmp.beans.Film;
import by.kino.jmp.beans.Seat;
import by.kino.jmp.beans.Ticket;
import by.kino.jmp.beans.Visitor;
import by.kino.jmp.repository.CinemaRepository;
import by.kino.jmp.repository.CinemaRepositoryInMem;
import by.kino.jmp.repository.VisitorRepository;
import by.kino.jmp.repository.VisitorRepositoryInMem;

public class BookTransaction extends AbstractTransaction {
	
	private static final Logger LOG = Logger.getLogger(BookTransaction.class);
	private Cinema cinema;
	private Visitor viewer;
	private List<Ticket> initCineamTickets = new ArrayList<Ticket>();
	private List<Ticket> initViewerTickets = new ArrayList<Ticket>();
	private List<Ticket> bookedTickets;
	private List<Ticket> cinemaTickets;
	private List<Ticket> viewerTickets;
	private Lock l = new ReentrantLock();
	private VisitorRepository vr = VisitorRepositoryInMem.getInstance();
	private CinemaRepository cr= CinemaRepositoryInMem.getInstance();
	
	public BookTransaction(Cinema cinema, Visitor viewer, List<Ticket> bookedTickets) {
		super();
		this.cinema = cinema;
		this.viewer = viewer;
		this.bookedTickets = bookedTickets;
		initCineamTickets.addAll(cinema.getTickets());
		initViewerTickets.addAll(viewer.getTickets());

		LOG.info(cinema);
		LOG.info(viewer);
		
		LOG.info("Init transaction" + toString());
	}

	private class StartTransaction implements Runnable {

		public void run() {
			try {
				LOG.info("Start transaction");

				boolean isValidCondition = true;
				for (Ticket t: bookedTickets) {
					if (!cinema.getTickets().contains(t)) {
						isValidCondition = false;
					}
				}

				if (isValidCondition) {

					cinemaTickets = new ArrayList<Ticket>();
					for (Ticket ticket : cinema.getTickets()) {
						if (!bookedTickets.contains(ticket)) {
							cinemaTickets.add(ticket);
						}
					}

				}
				
				LOG.debug("BO: " + isValidCondition);
				getControl().setStatus(isValidCondition);
				getControl().setRunning(false);
			} catch (Exception e) {
				LOG.error("Error on Start: " + e.fillInStackTrace());
			}
		}
	}

	private class CommitTransaction implements Runnable {

		public void run() {
			try {
				LOG.info("Commit transaction");

				cinema.setTickets(cinemaTickets);
				viewer.setTickets(bookedTickets);
				vr.store(viewer);
				cr.store(cinema);
				
				LOG.info(cinema);
				LOG.info(viewer);
				
				getControl().setStatus(true);
				getControl().setRunning(false);
			} catch (Exception e) {
				LOG.error("Error on Commit: " + e.fillInStackTrace());
			}
		}
	}

	private class AbbortTransaction implements Runnable {

		public void run() {
			try {
				LOG.info("Abort transaction");

				cinema.setTickets(initCineamTickets);
				viewer.setTickets(initViewerTickets);
				vr.store(viewer);
				cr.store(cinema);
				LOG.info(cinema);
				LOG.info(viewer);

				getControl().setRunning(false);
			} catch (Exception e) {
				LOG.error("Error on Abort: " + e.fillInStackTrace());
			}
		}
	}

	public void startTransaction() {
		l.lock();
		getControl().setRunning(true);
		new Thread(new StartTransaction()).start();
	}

	public void abbort() {
		new Thread(new AbbortTransaction()).start();
		l.unlock();
	}

	public void commit() {
		getControl().setRunning(true);
		new Thread(new CommitTransaction()).start();
		l.unlock();
	}

	@Override
	public String toString() {
		return "BookTransaction [cinema=" + cinema + ", viewer=" + viewer
				+ ", initCineamTickets=" + initCineamTickets
				+ ", initViewerTickets=" + initViewerTickets
				+ ", bookedTickets=" + bookedTickets + ", cinemaTickets="
				+ cinemaTickets + ", viewerTickets=" + viewerTickets + ", l="
				+ l + "]";
	}
	
}
