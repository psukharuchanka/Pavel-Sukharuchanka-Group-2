package by.kino.jmp.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import by.kino.jmp.beans.Cinema;
import by.kino.jmp.beans.Film;
import by.kino.jmp.beans.Ticket;
import by.kino.jmp.beans.Visitor;
import by.kino.jmp.repository.CinemaRepository;
import by.kino.jmp.repository.CinemaRepositoryInMem;
import by.kino.jmp.repository.VisitorRepository;
import by.kino.jmp.repository.VisitorRepositoryInMem;

public class CheckOutTransaction extends AbstractTransaction {
	
	private static final Logger LOG = Logger.getLogger(CheckOutTransaction.class);
	private Cinema cinema;
	private Visitor viewer;
	private List<Ticket> tikcetsToBook = new ArrayList<Ticket>();
	private int cinemaInitBalance;
	private int cinemaBalance;
	private int viewerInitBalance;
	private int viewerBalance;
	private VisitorRepository vr = VisitorRepositoryInMem.getInstance();
	private CinemaRepository cr= CinemaRepositoryInMem.getInstance();
	private Lock l = new ReentrantLock();

	public CheckOutTransaction(Cinema cinema, Visitor viewer, List<Ticket> tikcetsToBook) {
		super();
		this.tikcetsToBook = tikcetsToBook;
		this.cinema = cinema;
		this.viewer = viewer;
		
		LOG.info(cinema);
		LOG.info(viewer);
		
		LOG.info("Init transaction" + toString());
	}

	private class StartTransaction implements Runnable {

		public void run() {
			try {
				LOG.info("Start transaction");

				viewerInitBalance = viewer.getAmount();
				
				boolean isValidCondition = false;
				int sum=0;
				for (Ticket t:tikcetsToBook){
					sum+=t.getFilm().getTicketPrice();
				}
				viewerBalance = viewerInitBalance - sum;
				cinemaBalance = cinemaInitBalance + sum;
				if (viewerBalance >= 0) {
					isValidCondition = true;
				}
				
				LOG.debug("CO: " + isValidCondition);

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

				viewer.setAmount(viewerBalance);
				cinema.setAmount(cinemaBalance);
				vr.store(viewer);
				cr.store(cinema);
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

				viewer.setAmount(viewerInitBalance);
				cinema.setAmount(cinemaInitBalance);
				vr.store(viewer);
				cr.store(cinema);
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
		return "CheckOutTransaction [cinema=" + cinema + ", viewer=" + viewer
				+ ", tikcetsToBook=" + tikcetsToBook + ", cinemaInitBalance="
				+ cinemaInitBalance + ", cinemaBalance=" + cinemaBalance
				+ ", viewerInitBalance=" + viewerInitBalance
				+ ", viewerBalance=" + viewerBalance + ", l=" + l + "]";
	}
	
}
