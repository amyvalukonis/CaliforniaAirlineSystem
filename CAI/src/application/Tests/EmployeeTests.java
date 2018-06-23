package application.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Classes.Customer;
import application.Classes.Employee;

class EmployeeTests {
	
	@Test
	public void testEmployeeId() {
		Employee employee = new Employee("Y", "M", "Yuri", "Machida", "False", 1);
		assertEquals(employee.getId(), 1);
	}
	
	@Test
	public void testGetFirstName() {
		Employee employee = new Employee("Y", "M", "Yuri", "Machida", "False", 1);
		assertEquals(employee.getFirstName(), "Yuri");
	}
	
	@Test
	public void testGetLastName() {
		Employee employee = new Employee("Y", "M", "Yuri", "Machida", "False", 1);
		assertEquals(employee.getLastName(), "Machida");
	}
	
	@Test
	public void testNotFlightCrew() {
		Employee employee = new Employee("Y", "M", "Yuri", "Machida", "False", 1);
		assertFalse(employee.getIsFlightCrew());
	}
	
	@Test
	public void testIsFlightCrew() {
		Employee employee = new Employee("Y", "M", "Yuri", "Machida", "True", 1);
		assertTrue(employee.getIsFlightCrew());
	}
	
	@Test
	public void testSetFlightCrew() {
		Employee employee = new Employee("Y", "M", "Yuri", "Machida", "False", 1);
		employee.setIsFlightCrew(true);
		
		assertTrue(employee.getIsFlightCrew());
	}
	
	@Test
	public void testIsUserWithCredentials() {
		Employee employee = new Employee("Y", "M", "Yuri", "Machida", "False", 1);
		assertTrue(employee.isUserWithCredentials("Y", "M"));
	}
}
