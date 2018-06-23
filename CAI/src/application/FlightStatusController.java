package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import application.Classes.DatabaseReference;
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


import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class FlightStatusController {
	
	@FXML Button toMain;
	@FXML Button toShowFlightStatus;
	@FXML ChoiceBox<Integer> hourBox; 
	@FXML ChoiceBox<Integer> minuteBox; 
	@FXML ChoiceBox<String> amPM; 
	@FXML TextField error; 
	@FXML DatePicker dp; 
	boolean customer; 

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
	
	@FXML void handleToShowFlightStatus(ActionEvent event) throws IOException {
		LocalDate ld = dp.getValue();
		String flightDate = ld.toString();
		flightDate = flightDate.replaceAll("-","");
		int hour = 0;
		
		if((amPM.getValue().toString()).equals("PM")){hour = hourBox.getValue() + 12;}
		else if( (amPM.getValue().toString()).equals("AM") && hourBox.getValue() == 12 ){hour = 0;	}
		else {hour = hourBox.getValue();}
		
		String flightTime = String.format("%s %02d:%02d",flightDate,hour,minuteBox.getValue());
		System.out.println(flightTime);
		
		if(!DatabaseReference.checkFlightExists(flightTime)) {
			error.setVisible(true);
		}
		try {
			Stage stage = (Stage)toShowFlightStatus.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowFlightStatus.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root,600,400);
			
			stage.setTitle("CAI");
			stage.setScene(scene);
			stage.show();
			
			ShowFlightStatusController controller = (ShowFlightStatusController)loader.getController();
			controller.flightDate.setText(flightDate);
			controller.flightTime.setText( String.format( "%02d%02d",hour, minuteBox.getValue() ) );
			controller.flightDeparture = flightTime;
			controller.initializeController();
			controller.customer=customer; 
		}
		catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	@FXML void initialize() {
			for(int i = 0;i <= 12; i++) {
				hourBox.getItems().add(i);
			}
			for(int i = 0;i < 60; i++) {
				minuteBox.getItems().add(i);
			}
			amPM.getItems().addAll("AM","PM");
	}
}
