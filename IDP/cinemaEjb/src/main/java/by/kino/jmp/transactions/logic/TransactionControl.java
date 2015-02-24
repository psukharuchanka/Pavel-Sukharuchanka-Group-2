package by.kino.jmp.transactions.logic;

public class TransactionControl {
	
	private boolean running = false;
	private boolean status = false;
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
