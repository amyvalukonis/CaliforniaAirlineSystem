package application.Classes;

public class Location {
	public static String convertLocationIdToString(Integer locationId) {
		if (locationId == 1) {
			return "San Luis Obispo";
		}
		else if (locationId == 2) {
			return "Los Angeles";
		}
		else if (locationId == 3) {
			return "San Francisco";
		}
		else if (locationId == 4) {
			return "San Diego";
		}
		else if (locationId == 5) {
			return "Arizona";
		}
		else if (locationId == 6) {
			return "Seattle";
		}
		else if (locationId == 7) {
			return "Dallas";
		}
		return "";
	}
	
	public static Integer convertLocationStringToLocationId(String location) {
		if (location.equals("San Luis Obispo")) {
			return 1;
		}
		else if (location.equals("Los Angeles")) {
			return 2;
		}
		else if (location.equals("San Francisco")) {
			return 3;
		}
		else if (location.equals("San Diego")) {
			return 4;
		}
		else if (location.equals("Arizona")) {
			return 5;
		}
		else if (location.equals("Seattle")) {
			return 6;
		}
		else if (location.equals("Dallas")) {
			return 7;
		}
		return 0;
	}
}
