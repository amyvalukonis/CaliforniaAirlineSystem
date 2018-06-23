package application.Classes;


public class PriceCalculator {

	public static double calculatePrice(Flight flight) {
		
		return flight.getBasePrice();
	}
	
	public static Double getPrice(Double basePrice, Double averageEmptySeats) {
		return basePrice - ((averageEmptySeats/2)*basePrice);
	}
}
