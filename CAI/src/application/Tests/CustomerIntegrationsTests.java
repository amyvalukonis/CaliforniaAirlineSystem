package application.Tests;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import application.Classes.*;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class CustomerIntegrationsTests {

	@Test
	public void testVerifyTicketCheckInProcess() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		
		Customer customer = new Customer("aa", "bb", 1);
		String confirmationCode = Customer.generateCode();
		Ticket ticket = new Ticket(customer, (new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0)), confirmationCode);
		ticket.setCheckedIn(true);
		assertEquals(ticket.getConfirmationCode(), confirmationCode);
		assertEquals(ticket.isCheckedIn(), true);
	}
	
	@Test
	public void testReserveTicketForCustomer() {
		int tableSize = DatabaseReference.getTickets().size();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		Date date = new Date(0);
		
		Customer customer = new Customer("yuri", "m", 6);
		String confirmationCode = Customer.generateCode();
		
		Flight flight = new Flight(date, calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On Schedule");
		DatabaseReference.addFlight(date, calendar.getTime(), 1, 20, 10, 100.0, "On Schedule");
		
		DatabaseReference.addTicket(flight.getId(), customer.getId(), 0, false, confirmationCode, "yuri", "m", true, "425-431-4523");
		
		assertEquals(DatabaseReference.getTickets().size(), tableSize + 1);
	}
	
	@Test
	public void testCustomerCheckFlightStatus() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		Date date = new Date(0);

		Flight flight = new Flight(date, calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On Schedule");
		DatabaseReference.addFlight(date, calendar.getTime(), 1, 20, 10, 100.0, "On Schedule");
		assertEquals(DatabaseReference.getFlightStatus("19691231 17:00"), "On Schedule");
	}
	
	@Ignore
	public void testCustomerLogin() {
		User customer = new Customer("Yuri", "Machida", 6);
		
		DatabaseReference.addUser("Yuri", "Machida", true, false, false);
		
		assertTrue(customer.isUserWithCredentials("Yuri", "Machida"));
		
		assertTrue(DatabaseReference.checkCustomerLogin("Yuri", "Machida"));
		assertFalse(DatabaseReference.checkEmployeeLogin("Yuri", "Machida"));
		
	}
}
