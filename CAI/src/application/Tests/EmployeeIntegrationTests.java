package application.Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmployeeIntegrationTests {
	@Ignore
	public void testEmployeeLogin() {
		Employee employee = new Employee("Y", "M", "yuri", "machida", "False", 6);
		
		DatabaseReference.addUser("Y", "M", false, false, true);
		
		assertTrue(employee.isUserWithCredentials("Y", "M"));
		
		assertFalse(DatabaseReference.checkCustomerLogin("Y", "M"));
		assertTrue(DatabaseReference.checkEmployeeLogin("Y", "M"));
		
	}
	
	@Test
	public void testUpdateEmployee() {
		Employee employee = new Employee("y", "m", "yuri", "machida", "False", 6);
		
		DatabaseReference.addUser("y", "m", false, false, true);
		
		DatabaseReference.updateEmployee("Yuri", "Machida", false, 6);
		
		assertEquals(employee.getFirstName(), "Yuri");
		assertEquals(employee.getLastName(), "Machida");
	}
}
