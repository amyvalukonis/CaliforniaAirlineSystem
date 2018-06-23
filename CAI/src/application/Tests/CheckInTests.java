package application.Tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date; 

import org.junit.jupiter.api.Test;

import application.Classes.Customer;
import application.Classes.DatabaseReference;
import application.Classes.Ticket;
import application.Classes.User;

class CheckInTests {

	@Test
	public void testTicketSize() {
		assertEquals(DatabaseReference.getTickets().size() > 0, true);
	}

	@Test
	public void testAddingTicketToDatabase() {
		DatabaseReference.addTicket(6,2,3,false,"4XY", "Amy", "Valukonis",true, "425-431-4523"); 
		assertEquals(DatabaseReference.checkConfirmationCode("Amy", "Valukonis","4XY" ), true); 
	}
	
	@Test
	public void testInvalidConfirmationCode() {
		assertEquals(DatabaseReference.checkConfirmationCode("Yuri", "M","ektSrlcf" ), false);
	}

}
