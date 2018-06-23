package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Classes.Customer;
import application.Classes.User;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;


public class BaggageDisplayController{ 

	@FXML TextField bag1;
	@FXML TextField bag2; 
	@FXML Label code1; 
	@FXML Label code2; 
	@FXML Button mainMenu; 
	String displayBags; 
	boolean customer; 
	
	public void init() {
		String codeFirst, codeSecond; 
		codeFirst = Customer.generateCode(); 
		codeSecond = Customer.generateCode();
		System.out.println(codeFirst);
		if(displayBags.equals("1")) {
			bag1.setVisible(true);
			code1.setVisible(true);
			code1.setText(codeFirst); 
		}
		else if(displayBags.equals("2")) {
			bag1.setVisible(true); 
			code1.setVisible(true);
			bag2.setVisible(true);
			code2.setVisible(true);
			code1.setText(codeFirst); 
			code2.setText(codeSecond);
		}
	}
	
	@FXML void handleToCheckIn(ActionEvent event) throws IOException { 
		Parent root; 
		try {
			Stage stage = (Stage)mainMenu.getScene().getWindow();
			if(customer==false) {
				root = FXMLLoader.load(getClass().getResource("MainMenuEmployee.fxml"));
			}
			else {
				root = FXMLLoader.load(getClass().getResource("MainMenuCustomer.fxml"));
			}
			//Parent root = FXMLLoader.load(getClass().getResource("CheckIn.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("Check In");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}		
		
	} 
	 
    
}