package by.kino.jmp.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cinema {
	
	private Integer id;
	private List<Ticket> tickets = new ArrayList<Ticket>();
	private int amount;
	
	public Cinema(List<Ticket> tickets,int amount) {
		super();
		this.tickets = tickets;
		this.amount=amount;
		this.id= (new Random()).nextInt();
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
