package application.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Classes.FlightsScheduler;

class FlightsSchedulerTests {

	@Test
	void testFlightDuration() {
		int flightTime = FlightsScheduler.getFlightDurationEstimateInMinutes("Los Angeles", "San Luis Obispo");
		assertEquals(flightTime, 45);
	}
	
	@Test
	void testInvalidFlightDuration() {
		int flightTime = FlightsScheduler.getFlightDurationEstimateInMinutes("Chicago", "San Luis Obispo");
		assertEquals(flightTime, 0);
	}

}
