package application.Classes;


import java.util.*;

public class Ticket {
	private int ticketOwnerUserId; //id of User object
	private int flightId;
	private User ticketOwner;
	private Flight flight;
	private boolean checkedIn;
	private String confirmationCode;
	
	
	public Ticket(User ticketOwner, Flight flight, String confirmationCode) {
		this.ticketOwnerUserId = ticketOwner.getId();
		this.setTicketOwner(ticketOwner);
		this.setFlight(flight);
		this.setFlightId(flight.getId());
		this.setCheckedIn(false);
		this.setConfirmationCode(confirmationCode);
		flight.setSeatsTaken(flight.getSeatsTaken()+1);
		//DatabaseReference.addTicket(this);
	}
	
	
	
	public static ArrayList<Ticket> reservedFlightTicketsForUser(User user) {
		ArrayList<Ticket> ticketsForUser = new ArrayList<Ticket>();
		for (Ticket ticket: DatabaseReference.getTickets()) {
			if (ticket.ticketOwnerUserId == user.getId()) {
				System.out.println("id is "+ user.getId());
				ticketsForUser.add(ticket);
			}
		}
		
		return ticketsForUser;
	}
	
	


	public Flight getFlight() {
		return flight;
	}



	public void setFlight(Flight flight) {
		this.flight = flight;
	}



	public User getTicketOwner() {
		return ticketOwner;
	}



	public void setTicketOwner(User ticketOwner) {
		this.ticketOwner = ticketOwner;
	}



	public int getFlightId() {
		return flightId;
	}



	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}



	public boolean isCheckedIn() {
		return checkedIn;
	}



	public void setCheckedIn(boolean checkedIn) {
		this.checkedIn = checkedIn;
	}



	public String getConfirmationCode() {
		return confirmationCode;
	}



	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}
	
	
	
}
