package application.Tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import application.Classes.*;


public class CustomerTests {

	@Test
	public void verifyCodeGeneration() {
		 String confirmationCode = Customer.generateCode();
		 assertEquals(confirmationCode.length(), 8);
	}
	
	@Test
	public void testCustomerId() {
		Customer customer = new Customer("Y", "M", 6);
		assertEquals(customer.getId(), 6);
	}
	
	@Test
	public void verifyCustomerCredentials() {
		Customer customer = new Customer("Y", "M", 6);
		assertEquals(customer.isUserWithCredentials("Y", "M"), true);
	}
	
	@Test
	public void setCustomerId() {
		Customer customer = new Customer("Y", "M", 6);
		customer.setId(5);
		assertEquals(customer.getId(), 5);
	}
}
