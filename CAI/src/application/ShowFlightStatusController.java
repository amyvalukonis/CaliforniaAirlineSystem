package application;

import java.io.IOException;
import java.util.HashMap;

import application.Classes.DatabaseReference;
import application.Classes.Location;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class ShowFlightStatusController {
	
	@FXML Button toMain;
	@FXML Button toFlightStatus;
	@FXML TextField flightDate; 
	@FXML TextField flightTime;
	@FXML TextField flightStatus; 
	String flightDeparture;
	boolean customer; 
	
	public void initializeController() {
		flightDate.setEditable(false);
		flightTime.setEditable(false);
		//get Status from Database
		String status = DatabaseReference.getFlightStatus(flightDeparture);
		flightStatus.setText(status);
		flightStatus.setEditable(false);
	}

	@FXML void handleToMain(ActionEvent event) throws IOException {
		Parent root; 
		try {
			Stage stage = (Stage)toMain.getScene().getWindow();
			if(customer == false) {
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
	
	@FXML void handleToFlightStatus(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)toFlightStatus.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("FlightStatus.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("Show Flight Status");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}