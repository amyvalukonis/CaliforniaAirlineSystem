package application.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import application.Classes.Flight;

class FlightTests {
	
	@Test
	public void testGetFlightNumber() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		assertEquals(flight.getFlightNumber(), "CAI-0000");
	}
	
	@Test
	public void testSetFlightNumber() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		flight.setFlightNumber("CAI-0001");
		
		assertEquals(flight.getFlightNumber(), "CAI-0001");
	}
	
	@Test
	public void testGetDepartureLocation() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		assertEquals(flight.getDepartureLocation(), "San Luis Obispo");
	}
	
	@Test
	public void testSetDepartureLocation() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Francisco", "San Luis Obispo", 20, 100.0);
		flight.setDepartureLocation("Los Angeles");
		
		assertEquals(flight.getDepartureLocation(), "Los Angeles");
	}
	
	@Test
	public void testGetDestination() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		assertEquals(flight.getDestination(), "San Francisco");
	}
	
	@Test
	public void testSetDestination() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		flight.setDestination("Chicago");
		assertEquals(flight.getDestination(), "Chicago");
	}
	
	@Test
	public void testGetSeats() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		assertEquals(flight.getSeats(), 20);
	}
	
	@Test
	public void testSetSeats() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		flight.setSeats(30);
		assertEquals(flight.getSeats(), 30);
	}
	
	@Test
	public void testGetSeatsTaken() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On time");
		assertEquals(flight.getSeatsTaken(), 10);
	}
	
	@Test
	public void testSetSeatsTaken() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On time");
		flight.setSeatsTaken(5);
		assertEquals(flight.getSeatsTaken(), 5);
	}
	
	@Test
	public void testGetStatus() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On time");
		assertEquals(flight.getStatus(), "On time");
	}

	@Test
	public void testSetStatus() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On time");
		flight.setStatus("Delayed");
		assertEquals(flight.getStatus(), "Delayed");
	}
	
	@Test
	public void testGetId() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On time");
		assertEquals(flight.getId(), 5);
	}
	
	@Test
	public void testSetId() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On time");
		flight.setId(10);
		assertEquals(flight.getId(), 10);
	}
	
	@Test
	public void testGetBasePrice() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On time");
		assertEquals(flight.getBasePrice(), 100.0);
	}
	
	@Test
	public void testSetBasePrice() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 10, 100.0, 5, "On time");
		flight.setBasePrice(150.00);
		assertEquals(flight.getBasePrice(), 150.00);
	}
}
