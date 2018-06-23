package application.Classes;

import java.util.*;

public class AvailableFlights {
	private static HashMap<String, ArrayList<Flight>> scheduledFlights;
	private static ArrayList<Flight> scheduledFlightsAsArray;
	
	private static void reloadFromDatabase() {
		ArrayList<Flight> availableFlights = DatabaseReference.getAvailableFlights();
		AvailableFlights.scheduledFlightsAsArray = availableFlights;
		AvailableFlights.scheduledFlights = new HashMap<>();
		for (Flight flight: availableFlights) {
			if (AvailableFlights.scheduledFlights.containsKey(flight.getDestination())) {
				AvailableFlights.scheduledFlights.get(flight.getDestination()).add(flight);
			}
			else {
				ArrayList<Flight> newFlightsArrayList = new ArrayList<Flight>();
				newFlightsArrayList.add(flight);
				AvailableFlights.scheduledFlights.put(flight.getDestination(), newFlightsArrayList);
			}
		}
		
	}
	
	public static HashMap<String, ArrayList<Flight>> getScheduledFlights() {
		AvailableFlights.reloadFromDatabase();
		
		return AvailableFlights.scheduledFlights;
	}
	
	public static ArrayList<Flight> getScheduledFlightsAsArray() {
		AvailableFlights.reloadFromDatabase();
		
		return AvailableFlights.scheduledFlightsAsArray;
	}
	
	
	
}
