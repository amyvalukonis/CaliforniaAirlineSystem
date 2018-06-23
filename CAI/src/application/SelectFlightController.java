package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SelectFlightController {
	
	@FXML Button next;
	@FXML Button back; 
	
	@FXML void handleBack(ActionEvent event) throws IOException {
    	try {
    		Stage stage = (Stage)back.getScene().getWindow();
    		Parent root = FXMLLoader.load(getClass().getResource("SearchFlight2.fxml"));
    		Scene scene = new Scene(root, 600, 400);
    		
    		stage.setScene(scene);
    		stage.show();
    	}
    	
    	catch (IOException e) {
    		e.printStackTrace(); 
    	}
		
	}
	
	@FXML void handleNext(ActionEvent event) throws IOException {
    	try {
    		Stage stage = (Stage)back.getScene().getWindow();
    		Parent root = FXMLLoader.load(getClass().getResource("TicketBought.fxml"));
    		Scene scene = new Scene(root, 600, 400);
    		
    		stage.setTitle("Ticket Confirmation");
    		stage.setScene(scene);
    		stage.show();
    	}
    	
    	catch (IOException e) {
    		e.printStackTrace(); 
    	}
		
	}
	
	
}