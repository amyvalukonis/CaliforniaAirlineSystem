package application.Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.Classes.Location;

class LocationTests {

	@Test
	void testConvertLocationIdToString() {
		assertEquals(Location.convertLocationIdToString(1), "San Luis Obispo");
	}
	
	@Test
	void testBadConvertLocationIdToString() {
		assertEquals(Location.convertLocationIdToString(10), "");
	}

	@Test
	void testConvertLocationStringToId() {
		Integer losAngelesLocation = 2;
		assertEquals(Location.convertLocationStringToLocationId("Los Angeles"), losAngelesLocation);
	}
	
	@Test
	void testBadConvertLocationStringToId() {
		Integer losAngelesLocation = 0;
		assertEquals(Location.convertLocationStringToLocationId("New York"), losAngelesLocation);
	}
}
