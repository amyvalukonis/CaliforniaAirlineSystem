package application.Classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Employee extends User {
	private String firstName;
	private String lastName;
	private Boolean isFlightCrew;
	
	public Employee(String username, String password, String firstName, String lastName, String isFlightCrew, Integer id) {
		super(username, password, id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.isFlightCrew = false;
		if (isFlightCrew.equals("True")) {
			this.isFlightCrew = true;
		}
		
	}
	
	private static String getLocationFromOption(int optionInput) {
		if (optionInput == 1) {
			return "Los Angeles";
		}
		else if (optionInput == 2) {
			return "San Francisco";
		}
		else if (optionInput == 3) {
			return "San Diego";
		}
		else if (optionInput == 4) {
			return "Arizona";
		}
		else if (optionInput == 5) {
			return "Seattle";
		}
		else if (optionInput == 6) {
			return "Dallas";
		}
		else if (optionInput == 7) {
			return "San Luis Obispo";
		}
		return "";
	}
	
	private int getDestination(Scanner scanner) {
		int optionInput = -1;
		while (optionInput != 0) {
			System.out.println("Choose flight destination, enter 0 to cancel scheduling: ");
			System.out.println("1. Los Angeles");
			System.out.println("2. San Francisco");
			System.out.println("3. San Diego");
			System.out.println("4. Arizona");
			System.out.println("5. Seattle");
			System.out.println("6. Dallas");
			System.out.println("7. San Luis Obispo");
			optionInput = scanner.nextInt();
			if (optionInput < 0 || optionInput > 7) {
				optionInput = -1;
				System.out.println("Please choose valid destination.");
			}
			else {
				return optionInput;
			}
		}
		return optionInput;
	}
	
	private int getDepartureLocation(Scanner scanner, int destinationOptionInput) {
		int optionInput = -1;
		if (destinationOptionInput == 7) {
			//If destination is SLO, departure location can be anywhere else except SLO
			while (optionInput != 0) {
				System.out.println("Choose flight departure location, enter 0 to cancel scheduling: ");
				System.out.println("1. Los Angeles");
				System.out.println("2. San Francisco");
				System.out.println("3. San Diego");
				System.out.println("4. Arizona");
				System.out.println("5. Seattle");
				System.out.println("6. Dallas");
				optionInput = scanner.nextInt();
				
				if (optionInput < 0 || optionInput > 6) {
					optionInput = -1;
					System.out.println("Please choose valid departure location.");
				}
				else {
					return optionInput;
				}
			}
		}
		else {
			return 7; //If destination is anywhere but SLO, departure location is SLO
		}
		return optionInput;
	}
	
	private Date getDepartureDate(Scanner scanner, ArrayList<Date> recommendedFlightSchedules) {
		int optionInput = -1;
		while (optionInput != 0) {
			for(int i = 0; i < recommendedFlightSchedules.size(); i++) {
				System.out.println(i+1 + ". " + recommendedFlightSchedules.get(i).toString());
			}
			
			System.out.println("Select a departure date or 0 to cancel: ");
			
			optionInput = scanner.nextInt();
			
			if (optionInput < 0 || optionInput > recommendedFlightSchedules.size()) {
				System.out.println("Please enter valid option.");
			}
			else if(optionInput == 0) {
				return null;
			}
			else {
				return recommendedFlightSchedules.get(optionInput-1);
			}
		}
		return null;
	}
	
	private HashMap<String, Object> addAdditionalFlightSchedulingRules(Scanner scanner, HashMap<String, Object> rules) {
		int optionInput = -1;
		
		while(optionInput != 0) {
			System.out.println("0. Cancel rules");
			System.out.println("1. Set recommended schedules minute intervals");
			System.out.println("2. Set start date and time for recommended schedules");
			System.out.println("3. Set end date and itme for recommended schedules"); 
			System.out.println("4. Apply rules and exit");
			optionInput = scanner.nextInt();
			if (optionInput < 0 || optionInput > 4) {
				System.out.println("Please enter a valid option");
			}
			else if (optionInput == 0) {
				return new HashMap<String, Object>();
			}
			else if (optionInput == 1) {
				System.out.println("Enter an integer for scheduling minute interval: ");
				int minuteInterval = scanner.nextInt();
				rules.put("Minute_Interval", minuteInterval);
				
			}
			else if (optionInput == 2) {
				System.out.println("Please enter a start date and time in the format MM/DD/YYYY HH:MM");
				scanner.nextLine();
				String dateInput = scanner.nextLine();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
				try {
					Date date = simpleDateFormat.parse(dateInput);
					rules.put("Start_Date", date);
				} catch (ParseException ex) {
					System.out.println("Invalid date and time format. Please try again.");
				}
			}
			else if (optionInput == 3) {
				System.out.println("Please enter an end date and time in the format MM/DD/YYYY HH:MM :");
				scanner.nextLine();
				String dateInput = scanner.nextLine();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
				try {
					System.out.println(dateInput);
					Date date = simpleDateFormat.parse(dateInput);
					rules.put("End_Date", date);
				} catch (ParseException ex) {
					System.out.println("Invalid date and time format. Please try again.");
				}
			}
			else if (optionInput == 4) {
				return rules;
			}
		}
		
		return rules;
	}

	private void scheduleFlight(Scanner scanner) {
		String destination, departureLocation;
		int durationInMinutes;
		int optionInput = this.getDestination(scanner);
		
		if (optionInput == 0) {
			return;
		}
		destination = this.getLocationFromOption(optionInput);
		
		optionInput = this.getDepartureLocation(scanner, optionInput);
		
		if (optionInput == 0) {
			return;
		}
		departureLocation = this.getLocationFromOption(optionInput);
		
		HashMap<String, Object> schedulingRules = new HashMap<>();
		int optionInput2 = -1;
		int flightDurationInMinutes = FlightsScheduler.getFlightDurationEstimateInMinutes(destination, departureLocation);
		while(optionInput2 != 0) {
			System.out.println("Flight duration estimate from historical data is: " + flightDurationInMinutes);
			System.out.println("Please select an option to add additional scheduling rules or to get recommended schedules:");
			System.out.println("0. Cancel");
			System.out.println("1. Get recommended schedules");
			System.out.println("2. Add additional rules");
			optionInput2 = scanner.nextInt();
			if (optionInput2 < 0 || optionInput2 > 2) {
				System.out.println("Please enter valid option.");
			}
			else if (optionInput2 == 1) {
				ArrayList<Date> recommendedFlightSchedules = FlightsScheduler.getRecommendedSchedules(schedulingRules, flightDurationInMinutes, destination, departureLocation);
				Date departureDate = this.getDepartureDate(scanner, recommendedFlightSchedules);
				
				if (departureDate == null) {
					optionInput = -1;
				}
				else {
					scanner.nextLine();
					//User chose a recommended schedule
					System.out.println("Enter flight number: ");
					String flightNumber = scanner.nextLine();
					System.out.println("Enter available seats for flight: ");
					int availableSeats = scanner.nextInt();
					System.out.println("Enter flight base price: ");
					double flightBasePrice = scanner.nextDouble();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(departureDate);
					calendar.add(Calendar.MINUTE, flightDurationInMinutes);
					Date arrivalDate = calendar.getTime();
					Flight newFlight = new Flight(departureDate, arrivalDate, flightNumber,
							departureLocation, destination, availableSeats, flightBasePrice);
					System.out.println("Flight scheduled!");
					DatabaseReference.addFlight(newFlight);
					return;
					
				}
			}
			else if (optionInput2 == 2) {
				schedulingRules = this.addAdditionalFlightSchedulingRules(scanner, schedulingRules);
			}
		}
		
		
		
	}
	
	private void changeFlightStatus(Scanner scanner, Flight flight) {
		System.out.println("Enter new flight status: ");
		String newFlightStatus = scanner.nextLine();
		flight.setStatus(newFlightStatus);
	}
	
	private void editFlightStatus(Scanner scanner) {
		ArrayList<Flight> scheduledFlights = AvailableFlights.getScheduledFlightsAsArray();
		int optionInput = -1;
		while (optionInput != 0) {
			int i = 0;
			for (Flight flight: scheduledFlights) {
				System.out.println(i+1 + ". " + flight.toString(true));
				i++;
			}
			System.out.println("Choose flight to edit status or enter 0 to exit.");
			
			optionInput = scanner.nextInt();
			scanner.nextLine();
			if (optionInput < 0 || optionInput > scheduledFlights.size()) {
				System.out.println("Please choose a valid option.");
			}
			else if (optionInput != 0) {
				this.changeFlightStatus(scanner, scheduledFlights.get(optionInput-1));
			}
		}
	}
	
	private void editEmployees(Scanner scanner) {
		int optionInput = -1;
		ArrayList<Employee> employees = User.getEmployees();
		while (optionInput != 0) {
			int i = 0;
			for (Employee employee: employees) {
				System.out.println(i+1 + ". " + employee.toString());
				i++;
			}
			System.out.println("Choose employee to edit or 0 to exit:");
			optionInput = scanner.nextInt();
			scanner.nextLine();
			if (optionInput < 0 || optionInput > employees.size()) {
				System.out.println("Please enter valid option.");
			}
			else if (optionInput != 0) {
				
			}
		}
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public String toString() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public void interfacePrompt(Scanner scanner) {
		int optionInput = 0;
		while(optionInput != 4) {
			System.out.println("What would you like to do?");
			System.out.println("1. Schedule flight");
			System.out.println("2. Edit employees");
			System.out.println("3. Edit flight status");
			System.out.println("4. Logout");
			optionInput = scanner.nextInt();
			
			if (optionInput == 1) {
				this.scheduleFlight(scanner);
			}
			else if (optionInput == 2) {
				this.editEmployees(scanner);
			}
			else if (optionInput == 3) {
				this.editFlightStatus(scanner);
			}
			else if (optionInput == 4) {
				return;
			}
			else {
				System.out.println("Not a valid option. Please try again.");
			}
		}
	}

	public Boolean getIsFlightCrew() {
		return isFlightCrew;
	}

	public void setIsFlightCrew(Boolean isFlightCrew) {
		this.isFlightCrew = isFlightCrew;
	}
}
