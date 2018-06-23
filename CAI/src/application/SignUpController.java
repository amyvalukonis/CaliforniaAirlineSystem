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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
public class SignUpController {
	
	@FXML Button backToMainMenu;
	@FXML public TextField username;
	@FXML public PasswordField password;
	@FXML public CheckBox isCustomerCheckBox;
	@FXML public CheckBox isOfficeEmployeeCheckBox;
	@FXML public CheckBox isFlightCrewCheckBox;
	
	
	@FXML void handleBack(ActionEvent event) throws IOException {
        try {
            Stage stage = (Stage)backToMainMenu.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root, 600, 400);

            stage.setTitle("Login");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
        	e.printStackTrace();
        }
	}
	
	@FXML public void signUpUser(ActionEvent event) throws IOException {
            String uname = username.getText();
            String pword = password.getText();
            boolean isFlightCrew = this.isFlightCrewCheckBox.isSelected();
    		boolean isOfficeEmployee = this.isOfficeEmployeeCheckBox.isSelected();
    		boolean isCustomer = this.isCustomerCheckBox.isSelected();
            this.signUpUser(uname, pword, isCustomer, isFlightCrew, isOfficeEmployee);
            try {
                Stage stage = (Stage)backToMainMenu.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Scene scene = new Scene(root, 600, 400);

                stage.setTitle("Login");
                stage.setScene(scene);
                stage.show();
            }
            catch (IOException e) {
            	e.printStackTrace();
            }
	}
	
	@FXML public void setUserAsCustomer(ActionEvent event) {
		this.isOfficeEmployeeCheckBox.setSelected(false);
		this.isFlightCrewCheckBox.setSelected(false);
	}
	
	@FXML public void setUserAsOfficeEmployee(ActionEvent event) {
		this.isCustomerCheckBox.setSelected(false);
		this.isFlightCrewCheckBox.setSelected(false);
	}
	
	@FXML public void setUserAsFlightCrew(ActionEvent event) {
		this.isCustomerCheckBox.setSelected(false);
		this.isOfficeEmployeeCheckBox.setSelected(false);
	}
	
	public void signUpUser(String username, String password, boolean isCustomer, boolean isFlightCrew, boolean isOfficeEmployee) {
		DatabaseReference.addUser(username, password, isCustomer, isFlightCrew, isOfficeEmployee);
	}
	
	
	
	
}
