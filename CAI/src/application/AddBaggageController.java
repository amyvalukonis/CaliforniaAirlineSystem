package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddBaggageController { 

	@FXML Button submit;
	@FXML Button baggagePolicy;
	@FXML Button toCheckIn; 
	@FXML ChoiceBox<String> comboBox; 
	BaggageDisplayController baggageDisplayController; 
	String bags; 
	boolean customer; 
	
	/*@FXML void handleSearch(ActionEvent event) throws IOException {
    	try {
    		Stage stage = (Stage)search.getScene().getWindow();
    		Parent root = FXMLLoader.load(getClass().getResource("DisplayFlightList.fxml"));
    		Scene scene = new Scene(root, 600, 400);
    		
    		stage.setScene(scene);
    		stage.show();
    	}
    	
    	catch (IOException e) {
    		e.printStackTrace(); 
    	}
		
	}*/ 

	@FXML void handleToBaggagePolicy(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)baggagePolicy.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("BaggagePolicy.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("Baggage Policy");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML void handleSubmit(ActionEvent event) throws IOException {
		try {
			String bags = comboBox.getValue();   
			Stage stage = (Stage)submit.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("BaggageDisplay.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("Check-In Confirmed");
			stage.setScene(scene);
			stage.show();
			
			BaggageDisplayController controller = (BaggageDisplayController)loader.getController();
			controller.displayBags=bags; 
			controller.customer=customer; 
			controller.init(); 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML void handleToCheckIn(ActionEvent event) throws IOException { 
		try {
			Stage stage = (Stage)toCheckIn.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckIn.fxml"));
			Parent root = (Parent) loader.load();
			//Parent root = FXMLLoader.load(getClass().getResource("CheckIn.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("Check In");
			stage.setScene(scene);
			stage.show();
			
			CheckInController controller = (CheckInController)loader.getController();
			controller.customer=customer; 
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
		
	} 
}