package application.Classes;

import java.util.*;

public class Customer extends User {
	ArrayList<Ticket> reservedFlightTickets;
	
	
	public Customer(String username, String password, Integer id) {
		super(username, password, id);
		
		
	}

	private void viewFlights(Scanner scanner) {
		
		ArrayList<Flight> availableFlights = DatabaseReference.getAvailableFlights();
		
		int optionInput = -1;
		while(optionInput != 0) {
			int i = 1;
			for(Flight flight: availableFlights) {
				if (flight.getSeatsTaken() < flight.getSeats()) {
					System.out.println(i + " : " + flight.toString(false));
					i++;
				}
			}
			System.out.println("Enter an option to buy a ticket, enter 0 to exit.");
			optionInput = scanner.nextInt();
			
			if (optionInput < 0 || optionInput > availableFlights.size()) {
				System.out.println("Not a valid option please try again.");
			}
			else if (optionInput != 0 && (availableFlights.get(optionInput-1).getSeatsTaken() >= availableFlights.get(optionInput-1).getSeats())) {
				System.out.println("Not a valid option please try again.");
			}
			else if (optionInput != 0) {
				this.viewFlight(scanner, availableFlights.get(optionInput-1));
			}
		} 
	}
	
	private void viewFlight(Scanner scanner, Flight flight) {
		System.out.println("Ticket price is " + flight.getFlightPrice());
		int optionInput = -1;
		while (optionInput != 2) {
			System.out.println("1. Buy ticket");
			System.out.println("2. Exit");
			optionInput = scanner.nextInt();
			if (optionInput < 1 || optionInput > 2) {
				System.out.println("Not a valid option please try again.");
			}
			else if (optionInput == 1) {
				//Buying ticket
				int numberOfPassengers = 0;
				while (numberOfPassengers <= 0) {
					System.out.println("Please enter number of passengers: ");		
					numberOfPassengers = scanner.nextInt();
					
					if (numberOfPassengers <= 0) {
						System.out.println("Please enter a number greater than 0.");
					}
				}
					
				scanner.nextLine();
				for (int i = 1; i <= numberOfPassengers; i++) {
					String firstName, lastName, phoneNumber, dateOfBirth;
					System.out.println("Enter first name for passenger " + i + ": ");
					firstName = scanner.nextLine();
					System.out.println("Enter last name for passenger " + i + ": ");
					lastName = scanner.nextLine();
					System.out.println("Enter phone number for passenger " + i + ": ");
					phoneNumber = scanner.nextLine();
					System.out.println("Enter date of birth for passenger " + i + " formatted as MM/DD/YYYY: ");
					dateOfBirth = scanner.nextLine();
					String confirmationCode = this.generateCode();
					Ticket newTicket = new Ticket(this, flight, confirmationCode);
					
					System.out.println("Thanks for reserving your flight!");
					System.out.println("Your ticket confirmation code is " + confirmationCode + ".");
					
					return;
				}
			}
		}
	}
	
	private void viewFlightStatus() {
		this.reservedFlightTickets = Ticket.reservedFlightTicketsForUser(this);
		
		if (this.reservedFlightTickets.size() == 0) {
			System.out.println("You have no reserved flights.");
		}
		else {
			for (Ticket reservedFlightTicket: this.reservedFlightTickets) {
				System.out.println(reservedFlightTicket.getFlight().toString(true));
			}
		}
	}
	

	private void flightCheckIn(Scanner scanner) {
		this.reservedFlightTickets = Ticket.reservedFlightTicketsForUser(this);
		
		if (this.reservedFlightTickets.size() == 0) {
			System.out.println("You have no reserved flights.");
		}
		else {
			int optionInput = -1;
			while (optionInput < 0) {
				for (int i = 0; i < this.reservedFlightTickets.size(); i++) {
					if (this.reservedFlightTickets.get(i).isCheckedIn() == false) {
						System.out.println(i+1 + ". " + this.reservedFlightTickets.get(i).getFlight().toString(true));
					}
				}
				System.out.println("\nPlease select flight to check-in to. Enter 0 to exit.");
				optionInput = scanner.nextInt();
				
				if (optionInput < 0 || optionInput > this.reservedFlightTickets.size()) {
					optionInput = -1;
					System.out.println("Please enter a valid option.");
				}
				else if (optionInput != 0 && this.reservedFlightTickets.get(optionInput-1).isCheckedIn()) {
					optionInput = -1;
					System.out.println("Please enter a valid option.");
				}
				else if (optionInput != 0) {
					System.out.println("Enter confirmation code for your ticket: ");
					String confirmationCode = scanner.next();
					if (!confirmationCode.equals(this.reservedFlightTickets.get(optionInput-1).getConfirmationCode())) {
						System.out.println("Invalid confirmation code");
						optionInput = -1;
					}
					else {
						this.reservedFlightTickets.get(optionInput-1).setCheckedIn(true);
						int baggageOneWeight, baggageTwoWeight;
						System.out.println("Enter weight for baggage 1 in lbs (0 if no baggage): ");
						baggageOneWeight = scanner.nextInt();
						System.out.println("Baggage code for baggage 1 is : " + this.generateCode());
						System.out.println("Enter weight for baggage 2 in lbs (0 if no baggage): ");
						baggageTwoWeight = scanner.nextInt();
						System.out.println("Baggage code for baggage 2 is : " + this.generateCode());
						System.out.println("Thank you for checking in!");
					}
				}
			}
		}

	}
	
	public static String generateCode() {
		String characters ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    Random random =new Random();
	    StringBuilder stringBuilder = new StringBuilder();
	    for (int i = 0; i < 8; i++) {
	       int randomIndex = random.nextInt(characters.length()); 
	       stringBuilder.append(characters.charAt(randomIndex));            
	    }
	    return stringBuilder.toString();
	}
	

	
	@Override
	public void interfacePrompt(Scanner scanner) {
		
		int optionInput = 0;
		while(optionInput != 4) {
			System.out.println("What would you like to do?");
			System.out.println("1. View and reserve flights");
			System.out.println("2. Flight check-in");
			System.out.println("3. Flight status");
			System.out.println("4. Logout");
			optionInput = scanner.nextInt();
			
			if (optionInput == 1) {
				this.viewFlights(scanner);
			}
			else if (optionInput == 2) {
				this.flightCheckIn(scanner);
			}
			else if (optionInput == 3) {
				this.viewFlightStatus();
			}
			else if (optionInput == 4) {
				return;
			}
			else {
				System.out.println("Not a valid option. Please try again.");
			}
		}
	}
}
