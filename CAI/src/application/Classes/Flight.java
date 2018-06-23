package application.Classes;

import java.util.*;

public class Flight {
	
	private ArrayList<Ticket> flightTickets;
	private int id;
	private Date departureDate;
	private Date arrivalDate;
	private int seats;
	private int seatsTaken;
	private String destination;
	private String departureLocation;
	private String flightNumber;
	private String status;
	private double basePrice;
	
	public Flight(Date departureDate, Date arrivalDate, String flightNumber,
			String departureLocation, String destination, int initialAvailableSeats, double basePrice) {
		this.setArrivalDate(arrivalDate);
		this.setDepartureDate(departureDate);
		this.setDepartureLocation(departureLocation);
		this.setDestination(destination);
		this.setFlightNumber(flightNumber);
		this.setSeats(initialAvailableSeats);
		this.setSeatsTaken(0);
		this.setStatus("On Schedule");
		this.setBasePrice(basePrice);
	
	}
	
	public Flight(Date departureDate, Date arrivalDate, String flightNumber,
			String departureLocation, String destination, int initialAvailableSeats, int seatsTaken, double basePrice, int id, String status) {
		this.setArrivalDate(arrivalDate);
		this.setDepartureDate(departureDate);
		this.setDepartureLocation(departureLocation);
		this.setDestination(destination);
		this.setFlightNumber(flightNumber);
		this.setSeats(initialAvailableSeats);
		this.setSeatsTaken(seatsTaken);
		this.setStatus(status);
		this.setBasePrice(basePrice);
		this.setId(id);
	}
	
	public Flight() {
		
	}
	
	public int getFlightDurationInMinutes() {
	
		long diff = this.getArrivalDate().getTime() - this.getDepartureDate().getTime();
		return (int)(diff / (60 * 1000) % 60);
	}
	
	public double getFlightPrice() {
		return PriceCalculator.calculatePrice(this);
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public int getSeatsTaken() {
		return seatsTaken;
	}

	public void setSeatsTaken(int seatsTaken) {
		this.seatsTaken = seatsTaken;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString(boolean withStatus) {
		String returnString = this.getFlightNumber() + " : " + 
				this.getDepartureLocation() + " to " + this.getDestination() + " : " + 
				this.getDepartureDate().toString() + " to " + this.getArrivalDate().toString();
		if (withStatus) {
			returnString = returnString + " : " + this.getStatus(); 
		}
		
		return returnString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
}
