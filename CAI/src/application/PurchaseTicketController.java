package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import application.Classes.Customer;
import application.Classes.DatabaseReference;
import application.Classes.Flight;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;

public class PurchaseTicketController{     
	
	@FXML Button toSearchFlight;
	@FXML CheckBox individual;
	@FXML CheckBox group;
	@FXML Label flightDetails;
	@FXML TextArea customerInfo;
	@FXML Button purchaseTicket;
	
	boolean customer; 
	Flight flightToPurchase;
	
	
	@FXML void handleToSearchFlight(ActionEvent event) throws IOException { 
		try {
			Stage stage = (Stage)toSearchFlight.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchFlight.fxml"));
			Parent root = (Parent) loader.load();
			//Parent root = FXMLLoader.load(getClass().getResource("SearchFlight.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("Purchase a Ticket");
			stage.setScene(scene);
			stage.show();
			
			SearchFlightController controller = (SearchFlightController)loader.getController();
			controller.customer = customer; 
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	// Firstname, lastname, DOB, (mm/dd/YYYY), phone #
	@FXML void handlePurchaseTicket(ActionEvent event) throws IOException, ParseException {
		String confirmCode = Customer.generateCode();
		String info = customerInfo.getText();
		String[] split = info.split("[\n,]");
		insertIntoDB(split, confirmCode);
		
		try {
			Stage stage = (Stage)purchaseTicket.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketBought.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root, 600, 400);
			
			TicketBoughtController controller = (TicketBoughtController)loader.getController();
			
			controller.displayCC.setText(confirmCode);
			controller.customer = customer; 
			
			stage.setTitle("Ticket Purchased");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
		
		//insert tickets into SQLlite db (function written)
		// update flight with seats remaining? (make sure num tickets purchased < available seats)
		// generate confirmation code and pass to ticketBoughtcontroller
		// go to ticketBoughtController
		
	}
	
	private void insertIntoDB(String[] split, String confirmCode) throws ParseException {
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String firstname;
		String lastname;
		Date date;
		String phoneNum;
		int numSeats = 0;
		System.out.println(Arrays.toString(split));
		
		if (individual.isSelected()) {
			numSeats++;
			firstname = split[0];
			lastname = split[1];
			date = format.parse(split[2]);
			phoneNum = split[3];
			//get actual flight_id
			DatabaseReference.addTicket(50, 50, 50, false, confirmCode, firstname, lastname, true, phoneNum);
			
		}
		else if (group.isSelected()) {
			for (int i = 0; i < split.length; i += 4) {
				numSeats++;
				firstname = split[i];
				lastname = split[i+1];
				date = format.parse(split[i+2]);
				phoneNum = split[i+3];
				//get actual flight_id
				DatabaseReference.addTicket(50, 50, 50, false, confirmCode, firstname, lastname, true, phoneNum);
			}
		
		}
	}
}