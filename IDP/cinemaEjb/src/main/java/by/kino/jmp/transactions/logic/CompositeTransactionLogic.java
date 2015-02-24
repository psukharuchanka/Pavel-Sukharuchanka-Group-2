package by.kino.jmp.transactions.logic;

import java.util.ArrayList;
import java.util.List;

import by.kino.jmp.beans.Cinema;
import by.kino.jmp.beans.Film;
import by.kino.jmp.beans.Ticket;
import by.kino.jmp.beans.Visitor;
import by.kino.jmp.transactions.BookTransaction;
import by.kino.jmp.transactions.CheckOutTransaction;
import by.kino.jmp.transactions.interfaces.CompositTransactionParticipant;

public class CompositeTransactionLogic {
	
	private Coordinator coordinator;

	public CompositeTransactionLogic(Cinema cinema, Visitor viewer, List<Ticket> tikcetsToBook) {
		BookTransaction bt = new BookTransaction(cinema, viewer, tikcetsToBook);
		CheckOutTransaction ct = new CheckOutTransaction(cinema, viewer, tikcetsToBook);
		List<CompositTransactionParticipant> participants = new ArrayList<CompositTransactionParticipant>();
		participants.add(bt);
		participants.add(ct);

		this.coordinator = new Coordinator(participants);
	}

	public void processTransactions(){
		coordinator.startTransactions();
		coordinator.synchronize();

		if (coordinator.getStatus()) {
			coordinator.commit();
			coordinator.synchronize();
			
			if (!coordinator.getStatus()) {
				coordinator.abbort();
			}
		} else {
			coordinator.abbort();
		}
	}
}
