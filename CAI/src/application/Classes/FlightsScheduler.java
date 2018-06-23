package application.Classes;

import java.util.*;


public class FlightsScheduler {
	private static int losAngelesEstimate = 45;
	private static int sanFranciscoEstimate = 45;
	private static int sanDiegoEstimate = 60;
	private static int arizonaEstimate = 60;
	private static int seattleEstimate = 120;
	private static int dallasEstimate = 120;
	
	
	
	
	public static int getFlightDurationEstimateInMinutes(String destination, String departureLocation) {
		
		if (destination == "Los Angeles" || departureLocation == "Los Angeles") {
			return FlightsScheduler.losAngelesEstimate;
		}
		else if (destination == "San Francisco" || departureLocation == "San Francisco") {
			return FlightsScheduler.sanFranciscoEstimate;
		}
		else if (destination == "San Diego" || departureLocation == "San Diego") {
			return FlightsScheduler.sanDiegoEstimate;
		}
		else if (destination == "Arizona" || departureLocation == "Arizona") {
			return FlightsScheduler.arizonaEstimate;
		}
		else if (destination == "Seattle" || departureLocation == "Seattle") {
			return FlightsScheduler.seattleEstimate;
		}
		else if (destination == "Dallas" || departureLocation == "Dallas") {
			return FlightsScheduler.dallasEstimate;
		}
		return 0;
	}
	
	
	public static ArrayList<Date> getRecommendedSchedules(HashMap<String, Object> rules, int flightDurationInMinutes, String destination, String departureLocation) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(calendar.HOUR, calendar.get(Calendar.HOUR)+1);
		Date startDate = calendar.getTime();
		
		int minuteInterval = 30;
		
		calendar.add(Calendar.WEEK_OF_YEAR, 2); //default end date
		Date endDate = calendar.getTime();
		
		if (rules != null) {
			if (rules.containsKey("End_Date")) {
				endDate = (Date) rules.get("End_Date");
			}
			
			if (rules.containsKey("Start_Date")) {
				startDate = (Date) rules.get("Start_Date");
			}
			
			if (rules.containsKey("Minute_Interval")) {
				minuteInterval = (int) rules.get("Minute_Interval");
			}
		}
		
		ArrayList<Date> recommendedFlightSchedules = FlightsScheduler.getDateBetweenDates(startDate, endDate, minuteInterval);
		
		
		
		return FlightsScheduler.filterInvalidRecommendedFlightSchedules(recommendedFlightSchedules, flightDurationInMinutes, destination, departureLocation);
	}
	
	private static ArrayList<Date> filterInvalidRecommendedFlightSchedules(ArrayList<Date> recommendedFlightSchedules, 
			int flightDurationInMinutes, String destination, String departureLocation) {
		//TODO if flight leaves have to make sure current airport + 40 minutes does not have any arriving or departing flights
		ArrayList<Date> filteredRecommendedFlightSchedules = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		ArrayList<Flight> scheduledFlightsAsArray = AvailableFlights.getScheduledFlightsAsArray();
		for (Date departureDate : recommendedFlightSchedules) {
			boolean recommendedFlightScheduleIsValid = true;
			
			calendar.setTime(departureDate);
			calendar.add(Calendar.MINUTE, flightDurationInMinutes);
			Date arrivalDate = calendar.getTime();
			
			//First check flights with the same destination
			for (Flight flight: scheduledFlightsAsArray) {
				/*
				if (flight.getDestination().equals(destination) || flight.getDepartureLocation().equals(destination)) {
					//Destination is busy starting from departure date of flight if departure location is same as destination
					Date destinationDateBusyFrom, destinationDateBusyUntil;
					calendar.setTime(flight.getDepartureDate());
					calendar.add(Calendar.MINUTE, -40);
					destinationDateBusyFrom = calendar.getTime();
					//Other wise if the flight's destination is the same as the current scheduled's destination then 
					//destination is busy starting from flight's arrival date
					if (flight.getDestination().equals(destination)) {
						calendar.setTime(flight.getArrivalDate());
						calendar.add(Calendar.MINUTE, -40);
						destinationDateBusyFrom = calendar.getTime();
					}
					
					calendar.setTime(destinationDateBusyFrom);
					calendar.add(Calendar.MINUTE, 80);
					destinationDateBusyUntil = calendar.getTime();
					if (flight.getDestination().equals(destination)) {
						if ((arrivalDate.before(destinationDateBusyUntil) && arrivalDate.after(destinationDateBusyFrom))) {
							recommendedFlightScheduleIsValid = false;
						}
					}
					else {
						if (departureDate.before(destinationDateBusyUntil) && departureDate.after(destinationDateBusyFrom)) {
			
							recommendedFlightScheduleIsValid = false;
						}
					}
				}
				else if (recommendedFlightScheduleIsValid && (flight.getDestination().equals(departureLocation) || flight.getDepartureLocation().equals(departureLocation))) {
					
					Date departureLocationDateBusyFrom , departureLocationDateBusyUntil;
					calendar.setTime(flight.getDepartureDate());
					calendar.add(Calendar.MINUTE, -40);
					departureLocationDateBusyFrom = calendar.getTime();
					
					if (flight.getDestination().equals(departureLocation)) {
					 	calendar.setTime(flight.getArrivalDate());
						calendar.add(Calendar.MINUTE, -40);
						departureLocationDateBusyFrom = calendar.getTime();
					}
					
					calendar.setTime(departureLocationDateBusyFrom);
					calendar.add(Calendar.MINUTE, 80);
					departureLocationDateBusyUntil = calendar.getTime();
					if (flight.getDestination().equals(departureLocation)) {
						if (departureDate.before(departureLocationDateBusyUntil) && departureDate.after(departureLocationDateBusyFrom)) {
							recommendedFlightScheduleIsValid = false;
						}
					}
					else {
						if (arrivalDate.before(departureLocationDateBusyUntil) && arrivalDate.after(departureLocationDateBusyFrom)) {
							recommendedFlightScheduleIsValid = false;
						}
						
					}
				}
				*/
				if (flight.getDestination().equals(destination)) {
					//if this flight's destination is equal to the scheduled flight's destination
					Date dateDestinationBusyFrom, dateDestinationBusyUntil;
					calendar.setTime(flight.getArrivalDate());
					calendar.add(Calendar.MINUTE, -40);
					dateDestinationBusyFrom = calendar.getTime();
					calendar.add(Calendar.MINUTE, 80);
					dateDestinationBusyUntil = calendar.getTime();
					if (arrivalDate.after(dateDestinationBusyFrom) && arrivalDate.before(dateDestinationBusyUntil)) {
						recommendedFlightScheduleIsValid = false;
					}
				}
				else if (recommendedFlightScheduleIsValid && flight.getDepartureLocation().equals(destination)) {
					//if this flight's departure location is equal to the scheduled flight's destination
					Date dateDestinationBusyFrom, dateDestinationBusyUntil;
					calendar.setTime(flight.getDepartureDate());
					calendar.add(Calendar.MINUTE, -40);
					dateDestinationBusyFrom = calendar.getTime();
					calendar.add(Calendar.MINUTE, 80);
					dateDestinationBusyUntil = calendar.getTime();
					if (arrivalDate.after(dateDestinationBusyFrom) && arrivalDate.before(dateDestinationBusyUntil)) {
						recommendedFlightScheduleIsValid = false;
					}
				}
				else if (recommendedFlightScheduleIsValid && flight.getDestination().equals(departureLocation)) {
					Date dateDestinationBusyFrom, dateDestinationBusyUntil;
					calendar.setTime(flight.getArrivalDate());
					calendar.add(Calendar.MINUTE, -40);
					dateDestinationBusyFrom = calendar.getTime();
					calendar.add(Calendar.MINUTE, 80);
					dateDestinationBusyUntil = calendar.getTime();
					if (departureDate.after(dateDestinationBusyFrom) && departureDate.before(dateDestinationBusyUntil)) {
						recommendedFlightScheduleIsValid = false;
					}
				}
				else if (recommendedFlightScheduleIsValid && flight.getDepartureLocation().equals(departureLocation)) {
					//if this flight's departure location is equal to the scheduled flight's destination
					Date dateDestinationBusyFrom, dateDestinationBusyUntil;
					calendar.setTime(flight.getDepartureDate());
					calendar.add(Calendar.MINUTE, -40);
					dateDestinationBusyFrom = calendar.getTime();
					calendar.add(Calendar.MINUTE, 80);
					dateDestinationBusyUntil = calendar.getTime();
					if (departureDate.after(dateDestinationBusyFrom) && departureDate.before(dateDestinationBusyUntil)) {
						recommendedFlightScheduleIsValid = false;
					}
				}
				
				if (recommendedFlightScheduleIsValid == false) {
					break;
				}
				
			}
			if (recommendedFlightScheduleIsValid) {
				filteredRecommendedFlightSchedules.add(departureDate);
			}

		}
		return filteredRecommendedFlightSchedules;
	}
	
	private static ArrayList<Date> getDateBetweenDates(Date startDate, Date endDate, int minuteInterval) {
		ArrayList<Date> dates = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		while (calendar.getTime().before(endDate)) {
			Date date = calendar.getTime();
			dates.add(date);
			calendar.add(Calendar.MINUTE, minuteInterval);
		}
		return dates;
	}
}
