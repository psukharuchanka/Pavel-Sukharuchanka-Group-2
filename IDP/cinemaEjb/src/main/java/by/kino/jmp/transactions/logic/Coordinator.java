package by.kino.jmp.transactions.logic;

import java.util.ArrayList;
import java.util.List;
import by.kino.jmp.transactions.interfaces.CompositTransactionParticipant;

public class Coordinator {
	
	private List<CompositTransactionParticipant> participants = new ArrayList<CompositTransactionParticipant>();
	public Coordinator(List<CompositTransactionParticipant> participants) {
		this.participants = participants;
	}

	public void startTransactions() {
		for(CompositTransactionParticipant tr : participants) {
			tr.startTransaction();
		}
	}

	public void synchronize() {
		for(CompositTransactionParticipant tr : participants) {
			tr.synchronize();
		}
	}

	public boolean getStatus() {
		boolean result = true;

		for(CompositTransactionParticipant tr : participants) {
			if (!tr.getStatus()) {
				result = false;
			}
		}

		return result;
	}

	public void abbort() {
		for(CompositTransactionParticipant tr : participants) {
			tr.abbort();
		}
	}

	public void commit() {
		for(CompositTransactionParticipant tr : participants) {
			tr.commit();;
		}
	}
}
