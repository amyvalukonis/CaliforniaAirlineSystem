package application;

import javafx.scene.control.Button;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class MainMenuCustomerController {
	
	@FXML Button toFlightStatus;
	@FXML Button toCheckIn;
    @FXML Button logoutButton;
    @FXML Button searchForFlight; 
    @FXML Button toPurchaseTicket;
    
    @FXML void handleSearchForFlight(ActionEvent event) throws IOException {
    	try {
    		Stage stage = (Stage)searchForFlight.getScene().getWindow();
    		Parent root = FXMLLoader.load(getClass().getResource("SearchFlight.fxml"));
    		Scene scene = new Scene(root, 600, 400);
    		
    		stage.setTitle("Purchase a Ticket");
    		stage.setScene(scene);
    		stage.show();
    	}
    	
    	catch (IOException e) {
    		e.printStackTrace(); 
    	}
    }
  
	@FXML void handleLogout(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)logoutButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("CAI");
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightStatus.fxml"));
			Parent root = (Parent) loader.load();
			//Parent root = FXMLLoader.load(getClass().getResource("FlightStatus.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("Check Flight Status");
			stage.setScene(scene);
			stage.show();
			
			FlightStatusController controller = (FlightStatusController)loader.getController();
			controller.customer=true;
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
			controller.customer=true;
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML void handleToPurchaseATicket(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)toCheckIn.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchFlight.fxml"));
			Parent root = (Parent) loader.load();
			//Parent root = FXMLLoader.load(getClass().getResource("SearchFlight.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("Purchase a Ticket");
			stage.setScene(scene);
			stage.show();

			SearchFlightController controller = (SearchFlightController)loader.getController();
			controller.customer=true; 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
