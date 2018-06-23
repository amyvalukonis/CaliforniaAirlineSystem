package application.Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


import application.Classes.*;

public class DatabaseReferenceTests {

	@Test
	public void testGetAvailableFlights() {
		assertEquals(DatabaseReference.getAvailableFlights().size() > 0, true);
		assertEquals(DatabaseReference.getAvailableFlights().size(), 3);
		
	}
	
	@Test
	public void testAddCustomer() {
		int tableSize = DatabaseReference.getUsersFromDatabase().size();
		DatabaseReference.addUser("Bill", "Smith");
		assertEquals(DatabaseReference.getUsersFromDatabase().size(), tableSize+1);
	}
	
	
	
}
