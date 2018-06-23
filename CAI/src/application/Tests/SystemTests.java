package application.Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import application.Classes.Customer;
import application.Classes.DatabaseReference;
import application.Classes.Employee;
import application.Classes.Flight;
import application.Classes.User;

class SystemTests {

	@Ignore
	void testBuyCustomerTicket() {
		User customer = new Customer("yyy", "m", 6);
		
		DatabaseReference.addUser("yyy", "m", true, false, false);
		
		assertTrue(customer.isUserWithCredentials("yyy", "m"));
		
		assertTrue(DatabaseReference.checkCustomerLogin("yyy", "m"));
		assertFalse(DatabaseReference.checkEmployeeLogin("yyy", "m"));
		
		int tableSize = DatabaseReference.getTickets().size();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		Date date = new Date(0);

		String confirmationCode = Customer.generateCode();
		
		Flight flight = new Flight(date, calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On Schedule");
		DatabaseReference.addFlight(date, calendar.getTime(), 1, 20, 10, 100.0, "On Schedule");
		
		DatabaseReference.addTicket(flight.getId(), customer.getId(), 0, false, confirmationCode, "yuri", "m", true, "425-431-4523");
		
		assertEquals(DatabaseReference.getTickets().size(), tableSize + 1);
	}

	@Ignore
	void testBuyEmployeeTicket() {
		Employee employee = new Employee("YY", "M", "yuri", "machida", "False", 6);
		
		DatabaseReference.addUser("YY", "M", false, false, true);
		
		assertTrue(employee.isUserWithCredentials("YY", "M"));
		
		assertFalse(DatabaseReference.checkCustomerLogin("YY", "M"));
		assertTrue(DatabaseReference.checkEmployeeLogin("YY", "M"));
		
		int tableSize = DatabaseReference.getTickets().size();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		Date date = new Date(0);

		String confirmationCode = "abcdefgh";
		
		Flight flight = new Flight(date, calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On Schedule");
		DatabaseReference.addFlight(date, calendar.getTime(), 1, 20, 10, 100.0, "On Schedule");
		
		DatabaseReference.addTicket(flight.getId(), 0, employee.getId(), false, confirmationCode, "yuri", "machida", false, "425-431-4523");
		
		assertEquals(DatabaseReference.getTickets().size(), tableSize + 1);
	}
}
