package application;

import java.io.IOException;

import application.Classes.DatabaseReference;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class LoginController {

	@FXML Button toEmployeeMenu;
	@FXML Button toCustomerMenu;
	@FXML Button signUp; 
	@FXML TextField username;
	@FXML PasswordField password;
	@FXML Label validLogin;
	
	@FXML void handleSignUp(ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage)signUp.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Scene scene = new Scene(root, 600, 400);

            stage.setTitle("Sign UP");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	@FXML void handleToCustomerMenu(ActionEvent event) throws IOException {
		String uname = username.getText();
		String pword = password.getText();
		boolean isValidLogin = DatabaseReference.checkCustomerLogin(uname, pword);
		if (isValidLogin) {
	        try {
	            Stage stage = (Stage)toCustomerMenu.getScene().getWindow();
	            Parent root = FXMLLoader.load(getClass().getResource("MainMenuCustomer.fxml"));
	            Scene scene = new Scene(root, 600, 400);
	
	            stage.setTitle("Customer Main Menu");
	            stage.setScene(scene);
	            stage.show();
	        }
	        catch (IOException e) {
	        	e.printStackTrace();
	        }
		}
		validLogin.setVisible(true);
        
	}
	
	@FXML void handleToEmployeeMenu(ActionEvent event) throws IOException {
		String uname = username.getText();
		String pword = password.getText();
		boolean isValidLogin = DatabaseReference.checkEmployeeLogin(uname, pword);
		if (isValidLogin) {
	        try {
	            Stage stage = (Stage)toEmployeeMenu.getScene().getWindow();
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenuEmployee.fxml"));
	            Parent root = (Parent) loader.load();
	            //Parent root = FXMLLoader.load(getClass().getResource("MainMenuEmployee.fxml"));
	            Scene scene = new Scene(root, 600, 400);
	
	            stage.setTitle("Employee Main Menu");
	            stage.setScene(scene);
	            stage.show();
	            
	            MainMenuEmployeeController controller = (MainMenuEmployeeController)loader.getController();
	            controller.customer=false; 
	        }
	        catch (IOException e) {
	        	e.printStackTrace();
	        }
		}
		validLogin.setVisible(true);
	}
	
}

