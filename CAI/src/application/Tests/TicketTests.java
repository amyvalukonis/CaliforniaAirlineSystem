package application.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import application.Classes.Customer;
import application.Classes.DatabaseReference;
import application.Classes.Flight;
import application.Classes.Ticket;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import application.Classes.Customer;
import application.Classes.DatabaseReference;
import application.Classes.Flight;
import application.Classes.Ticket;

public class TicketTests {

	@Test 
	public void testTicketFlight() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Customer customer = new Customer("Yuri", "yuri", 6);
		
		String confirmationCode = Customer.generateCode();
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		Ticket ticket = new Ticket(customer, flight, confirmationCode);
		
		assertEquals(ticket.getFlight(), flight);
	}
	
	@Test
	public void testSetFlight() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Customer customer = new Customer("Yuri", "yuri", 6);
		
		String confirmationCode = Customer.generateCode();
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		Ticket ticket = new Ticket(customer, flight, confirmationCode);
		
		Flight newFlight = new Flight(new Date(0), calendar.getTime(), "CAI-0001", "San Luis Obispo", "Los Angeles", 20, 100.0);
		ticket.setFlight(newFlight);
		
		assertEquals(ticket.getFlight(), newFlight);
	}
	
	@Test
	public void testTicketOwner() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Customer customer = new Customer("Yuri", "yuri", 6);
		
		String confirmationCode = Customer.generateCode();
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		Ticket ticket = new Ticket(customer, flight, confirmationCode);
		
		assertEquals(ticket.getTicketOwner(), customer);
	}
	
	@Test
	public void testSetNewTicketOwner() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Customer customer = new Customer("Yuri", "yuri", 6);
		
		String confirmationCode = Customer.generateCode();
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		Ticket ticket = new Ticket(customer, flight, confirmationCode);
		
		Customer newCustomer = new Customer("Y", "M", 6);
		ticket.setTicketOwner(newCustomer);
		
		assertEquals(ticket.getTicketOwner(), newCustomer);
	}
	
	@Test
	public void testFlightId() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Customer customer = new Customer("Yuri", "yuri", 6);
		
		String confirmationCode = Customer.generateCode();
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 0, 100.0, 5, "On time");
		Ticket ticket = new Ticket(customer, flight, confirmationCode);
		
		assertEquals(ticket.getFlightId(), 5);
	}

	@Test
	public void testSetFlightId() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Customer customer = new Customer("Yuri", "yuri", 6);
		
		String confirmationCode = Customer.generateCode();
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 0, 100.0, 5, "On time");
		Ticket ticket = new Ticket(customer, flight, confirmationCode);
		
		ticket.setFlightId(4);
		
		assertEquals(ticket.getFlightId(), 4);
	}
	
	@Test
	public void testCheckedIn() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Customer customer = new Customer("Yuri", "yuri", 6);
		
		String confirmationCode = Customer.generateCode();
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 0, 100.0, 5, "On time");
		Ticket ticket = new Ticket(customer, flight, confirmationCode);
		
		assertEquals(ticket.isCheckedIn(), false);
		
	}
	
	@Test
	public void testConfirmationCode() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Customer customer = new Customer("Yuri", "yuri", 6);
		
		String confirmationCode = Customer.generateCode();
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 0, 100.0, 5, "On time");
		Ticket ticket = new Ticket(customer, flight, confirmationCode);
		
		assertEquals(ticket.getConfirmationCode(), confirmationCode);
	}
	
	@Test
	public void testSetConfirmationCode() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Customer customer = new Customer("Yuri", "yuri", 6);
		
		String confirmationCode = Customer.generateCode();
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 0, 100.0, 5, "On time");
		Ticket ticket = new Ticket(customer, flight, confirmationCode);
		
		String newConfirmationCode = Customer.generateCode();
		ticket.setConfirmationCode(newConfirmationCode);
		
		assertEquals(ticket.getConfirmationCode(), newConfirmationCode);
	}
}
