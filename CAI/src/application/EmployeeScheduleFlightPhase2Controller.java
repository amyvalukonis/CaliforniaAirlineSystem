package application;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.event.ChangeListener;

import application.Classes.DatabaseReference;
import application.Classes.Location;
import application.Classes.PriceCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class EmployeeScheduleFlightPhase2Controller {
	@FXML Button backButton;
	@FXML TextField basePriceTextField;
	@FXML TextField flightNameTextField;
	@FXML TextField flightSeatsTextField;
	@FXML Label flightTicketPriceLabel;
	@FXML Label flightScheduledLabel;
	Boolean flightScheduled = false;
	Date departureDate;
	String destinationLocation;
	Integer destinationLocationId;
	String departureLocation;
	Integer departureLocationId;
	Integer flightDuration;
	Integer minuteInterval;
	Integer flightInfoId;
	String flightName;
	Double basePrice;
	Double averageEmptySeats;
	HashMap<String, HashMap<String, Double>> flightEmptySeatsHistory;
	
	
	
	public void initializeController() {
		this.departureLocationId = Location.convertLocationStringToLocationId(this.departureLocation);
		this.destinationLocationId = Location.convertLocationStringToLocationId(this.destinationLocation);
		HashMap<String, Object> flightInfo = DatabaseReference.getFlightInfo(this.departureLocationId, this.destinationLocationId);
		this.flightInfoId = (Integer)flightInfo.get("FlightInfo_Id");
		this.flightName = (String)flightInfo.get("Flight_Name");
		this.flightNameTextField.setText(this.flightName);
		this.flightEmptySeatsHistory = DatabaseReference.getFlightEmptySeatsHistory();
		this.averageEmptySeats = this.flightEmptySeatsHistory.get(this.departureLocation).get(this.destinationLocation);
	}
	
	
	
	@FXML void handleKeyReleased(KeyEvent event) {
		try {
			Double basePrice = Double.parseDouble(this.basePriceTextField.getText());
			//TODO need to calculate price using PriceCalculator using history!!
			this.basePrice = basePrice;
			this.flightTicketPriceLabel.setText("Flight Ticket Price is: " + PriceCalculator.getPrice(basePrice, this.averageEmptySeats));
		} catch (NumberFormatException exception) {
			
		}
	}
	
	
	@FXML void handleScheduleFlight(ActionEvent event) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(this.departureDate);
			calendar.add(Calendar.MINUTE, this.flightDuration);
			
			//These are the database variables to insert
			Date departureDate = this.departureDate;
			Date arrivalDate = calendar.getTime();
			Integer flightInfoId = this.flightInfoId;
			Integer seats = Integer.parseInt(this.flightSeatsTextField.getText());
			Integer seatsTaken = 0;
			Double basePrice = this.basePrice;
			String status = "On Schedule";
			
			if (flightScheduled == false) {
				DatabaseReference.addFlight(departureDate, arrivalDate, flightInfoId, seats, seatsTaken, PriceCalculator.getPrice(basePrice, this.averageEmptySeats), status);
				this.flightScheduledLabel.setText("Flight Scheduled!");
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	 
	
	@FXML void handleBack(ActionEvent event) {
		try {
			Stage stage = (Stage)backButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("EmployeeScheduleFlight.fxml"));
			Scene scene = new Scene(root,600,400);
			
			stage.setTitle("CAI");
			stage.setScene(scene);
			stage.show();
			
			
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	}

	
}
