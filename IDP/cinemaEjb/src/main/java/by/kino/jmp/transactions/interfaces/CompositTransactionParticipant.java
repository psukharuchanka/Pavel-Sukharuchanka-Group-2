package by.kino.jmp.transactions.interfaces;

public interface CompositTransactionParticipant extends TransactionParticipant {
	
	int SLEEP_TIMEOUT = 50;

	boolean getStatus();
}