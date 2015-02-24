package by.kino.jmp.transactions.interfaces;

public interface TransactionParticipant {
	
	void startTransaction();
	void abbort();
	boolean synchronize();
	void commit();
}