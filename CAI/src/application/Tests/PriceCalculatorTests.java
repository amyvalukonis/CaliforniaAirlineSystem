package application.Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import application.Classes.Flight;
import application.Classes.PriceCalculator;

class PriceCalculatorTests {

	@Test
	public void testCalculatePrice() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 60);
		
		Flight flight = new Flight(new Date(0), calendar.getTime(), "CAI-0000", "San Luis Obispo", "San Francisco", 20, 100.0);
		assertEquals(PriceCalculator.calculatePrice(flight), 100.0);
	}
	
	@Test
	public void testGetPrice() {
		Double basePrice = 100.0;
		Double averageEmptySeats = 1.0;
		Double price = 50.0;
		
		assertEquals(PriceCalculator.getPrice(basePrice, averageEmptySeats), price);
	}

}
