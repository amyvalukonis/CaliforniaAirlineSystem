package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import application.Classes.DatabaseReference;
import application.Classes.Flight;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class SearchFlightController{     
	
	@FXML Button search;
	@FXML Button toMain; 
	@FXML ChoiceBox destination;
	@FXML DatePicker date;
	@FXML ChoiceBox<String> choiceFlights;
	@FXML Button toPurchaseTicket;
	@FXML ChoiceBox departure;
	private ArrayList<Flight> flights;
	boolean customer; 
	
	
	@FXML void handleSearch(ActionEvent event) throws IOException {
//    	try {
//    		Stage stage = (Stage)search.getScene().getWindow();
//    		Parent root = FXMLLoader.load(getClass().getResource("DisplayFlightList.fxml"));
//    		Scene scene = new Scene(root, 600, 400);
//    		
//    		stage.setTitle("Select a Flight");
//    		stage.setScene(scene);
//    		stage.show();
//    	}
//    	
//    	catch (IOException e) {
//    		e.printStackTrace(); 
//    	}
		ObservableList<String> oL = FXCollections.observableArrayList();
		
		flights = DatabaseReference.getAvailableFlights();
		for (int i = 0; i < flights.size(); i++) {
			Flight flight = flights.get(i);
			String dest = destination.getValue().toString();
			String dep = departure.getValue().toString();
			
			LocalDate localDate = date.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date otherDate = Date.from(instant);
		
			SimpleDateFormat x = new SimpleDateFormat("ddMMyyyy");
			String temp = x.format(otherDate);
			String temp2 = x.format(flight.getDepartureDate());
			

			if (temp.equals(temp2) && flight.getDestination().equals(dest) && flight.getDepartureLocation().equals(dep)) {
				String line = flight.getDepartureLocation() + " ";
				SimpleDateFormat localDateFormat = new SimpleDateFormat("hh:mm a");
		        line += "(" + localDateFormat.format(flight.getDepartureDate()) + ")  -->  ";
		        line += flight.getDestination() + " ";
		        line += "(" + localDateFormat.format(flight.getArrivalDate()) + ")   ";
		        line += "\n";
		        // For some reason this function isn't working and only returns 0
 	            System.out.println(flight.getArrivalDate().getTime() - flight.getDepartureDate().getTime());
		        
		        
		        line += flight.getFlightDurationInMinutes() + " min   ";
		        line += "$" + flight.getBasePrice() + "   ";
		        int numAvailableSeats = flight.getSeats() - flight.getSeatsTaken();
		        if (numAvailableSeats < 1) {
		        	break;
		        }
		        line += numAvailableSeats + " seats available \n\n";
				oL.add(line);
			}
			
		}
		choiceFlights.setItems(oL);
		
	}
	
	@FXML void handleToMain(ActionEvent event) throws IOException { 
		try {
			Parent root; 
			Stage stage = (Stage)toMain.getScene().getWindow();
			if(customer==false) {
				root = FXMLLoader.load(getClass().getResource("MainMenuEmployee.fxml"));
			}
			else {
				root = FXMLLoader.load(getClass().getResource("MainMenuCustomer.fxml"));
			}
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("Main Menu");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
		
	}

	
	@FXML void handleToPurchaseTicket(ActionEvent event) throws IOException { 
//		try {
//			Stage stage = (Stage)toPurchaseTicket.getScene().getWindow();
//			Parent root = FXMLLoader.load(getClass().getResource("PurchaseTicket.fxml"));
//			Scene scene = new Scene(root, 600, 400);
//			
//			stage.setTitle("Purchase a Ticket");
//			stage.setScene(scene);
//			stage.show();
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}	
		String flightSelected = choiceFlights.getValue();
		
		
		try {
			Stage stage = (Stage)toPurchaseTicket.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PurchaseTicket.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root,600,400);
			
			stage.setTitle("Purchase a Ticket");
			stage.setScene(scene);
			stage.show();
			
			PurchaseTicketController controller = (PurchaseTicketController)loader.getController();
			
//			controller.flightToPurchase = findFlightIndex(flightSelected);
			
			controller.flightDetails.setText(flightSelected);
			controller.customer=customer; 
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private Flight findFlightIndex(String flightSelected) {
		String[] splitStr = flightSelected.split("\\s+");
		for (int j = 0; j < splitStr.length; j++) {
			System.out.println(splitStr[j]);
		}
		
		int i;
		for (i = 0; i < flights.size(); i++) {
			//if destination, departure, dates, and flight
		}
		return flights.get(i);
	}
	

}