package by.kino.jmp.transactions;

import java.util.UUID;
import org.apache.log4j.Logger;
import by.kino.jmp.transactions.interfaces.CompositTransactionParticipant;
import by.kino.jmp.transactions.logic.TransactionControl;

public abstract class AbstractTransaction implements CompositTransactionParticipant{
	
	private static final Logger LOG = Logger.getLogger(BookTransaction.class);
	private UUID id;
	private TransactionControl control;
	
	public AbstractTransaction() {
		this.id = UUID.randomUUID();
		this.control = new TransactionControl();
	}

	public UUID getId() {
		return id;
	}

	public TransactionControl getControl() {
		return control;
	}

	public void setControl(TransactionControl control) {
		this.control = control;
	}

	public boolean synchronize() {
		while (control.isRunning()) {
			try {
				Thread.sleep(SLEEP_TIMEOUT);
			} catch (InterruptedException e) {
				LOG.error("Error while waiting for response from StartTransaction Thread: " + 
						e.fillInStackTrace());
			}
		}
		return false;
	}

	public boolean getStatus() {
		return control.isStatus();
	}
}
