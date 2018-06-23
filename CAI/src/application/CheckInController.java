package application;

import java.io.IOException;

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
public class CheckInController {
	
	@FXML TextField first_name;
	@FXML TextField last_name; 
	@FXML TextField confirmationCode; 
	@FXML TextField error; 
	@FXML Button submit; 
	@FXML Button toMain;
	boolean customer; 
	
	@FXML void handleSubmit(ActionEvent event) throws IOException {
		try {
			String firstName = first_name.getText(); 
			String lastName = last_name.getText(); 
			String code = confirmationCode.getText(); 
			if(!DatabaseReference.checkConfirmationCode(firstName,lastName,code)) {
				error.setVisible(true); 
			}
			else {
				Stage stage = (Stage)submit.getScene().getWindow();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("checkInSubmit.fxml"));
				Parent root = (Parent) loader.load();
				//Parent root = FXMLLoader.load(getClass().getResource("checkInSubmit.fxml"));
				Scene scene = new Scene(root, 600, 400);
				
				stage.setTitle("Add baggage(s)");
				stage.setScene(scene);
				stage.show();

				AddBaggageController controller = (AddBaggageController)loader.getController();
				controller.customer=customer;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML void handleToMain(ActionEvent event) throws IOException {
		Parent root; 
		try {
			System.out.println(getClass());
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
}