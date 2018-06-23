package application;

import java.io.IOException;
import java.util.ArrayList;

import application.Classes.DatabaseReference;
import application.Classes.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class MainMenuEmployeeController {
	@FXML Button logoutButton;
	@FXML Button editEmployeeButton;
	@FXML Button scheduleFlightButton;
	@FXML Button checkIn; 
	@FXML Button searchFlight; 
	@FXML Button editFlightStatus; 
	@FXML Button checkFlightStatus; 
	@FXML Button weeklyFlightsReportButton;
	@FXML Button passengersForFlightsReportButton;
	
	@FXML void handlePassengersForFlights(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)searchFlight.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("EmployeePassengersForFlights.fxml"));
			Scene scene = new Scene(root, 610, 400);
			
			stage.setTitle("CAI");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	
	}
	
	
	@FXML void handleWeeklyFlightsReport(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)searchFlight.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("EmployeeWeekFlightsReport.fxml"));
			Scene scene = new Scene(root, 610, 400);
			
			stage.setTitle("CAI");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	
	}
	boolean customer; 
	
	@FXML void handleSearchFlight(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)searchFlight.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchFlight.fxml"));
			Parent root = (Parent) loader.load();
			//Parent root = FXMLLoader.load(getClass().getResource("SearchFlight2.fxml"));
			Scene scene = new Scene(root, 610, 400);
			
			stage.setTitle("CAI");
			stage.setScene(scene);
			stage.show();
			
			SearchFlightController controller = (SearchFlightController)loader.getController();
			controller.customer=false; 
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	
	}

	@FXML void handleLogout(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)logoutButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			Scene scene = new Scene(root, 610, 400);
			
			stage.setTitle("CAI");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	
	}
	
	/*
	@FXML void handleCheckFlightStatus(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)checkFlightStatus.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("FlightStatus.fxml"));
			Scene scene = new Scene(root, 610, 400);
			
			stage.setTitle("Check Flight Status");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	
	}*/
	
	@FXML void handleToFlightStatus(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)checkFlightStatus.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightStatus.fxml"));
			Parent root = (Parent) loader.load();
			//Parent root = FXMLLoader.load(getClass().getResource("FlightStatus.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("Check Flight Status");
			stage.setScene(scene);
			stage.show();
			
			FlightStatusController controller = (FlightStatusController)loader.getController();
			controller.customer=false; 
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*need to fix back button going back to customer main menu instead of employee main menu*/ 
	@FXML void handleCheckIn(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)checkIn.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CheckIn.fxml"));
			Parent root = (Parent) loader.load();
			//Parent root = FXMLLoader.load(getClass().getResource("CheckIn.fxml"));
			Scene scene = new Scene(root, 610, 400);
			
			stage.setTitle("Check-In");
			stage.setScene(scene);
			stage.show();
			
			CheckInController controller = (CheckInController)loader.getController();
			controller.customer=false;
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	
	}
	@FXML void handleEditEmployee(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)editEmployeeButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("EditEmployee.fxml"));
			Scene scene = new Scene(root,600,400);
			
			stage.setTitle("CAI");
			stage.setScene(scene);
			stage.show();
			
			
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	
	}
	
	@FXML void handleCheckFlightStatus(ActionEvent event) throws IOException {
//		ArrayList<Flight> availableFlights = DatabaseReference.getAvailableFlights();
//		ObservableList<String> availableFlightsData = FXCollections.observableArrayList();
//		try {
//			Stage stage = (Stage)editEmployeeButton.getScene().getWindow();
//			
//			for (int i = 0; i < availableFlights.size(); i++) {
//				availableFlightsData.add(availableFlights.get(i).toString(true));
//			}
//			
//			ListView<String> listView = new ListView<String>(availableFlightsData);
//			Pane root = FXMLLoader.load(getClass().getResource("EmployeeCheckFlightStatus.fxml"));
//			root.getChildren().add(listView);
//			Scene scene = new Scene(root,300,275);
//			
//			stage.setTitle("CAI");
//			stage.setScene(scene);
//			stage.show();
//		}
//		catch (IOException e) {
//            e.printStackTrace();
//        }
//	
	}
	
	@FXML void handleEditFlightStatus(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)editEmployeeButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("EmployeeEditFlightStatus.fxml"));
			Scene scene = new Scene(root,600,400);
			
			stage.setTitle("CAI");
			stage.setScene(scene);
			stage.show();
			
			
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML void handleScheduleFlight(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)scheduleFlightButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("EmployeeScheduleFlight.fxml"));
			Scene scene = new Scene(root,600,400);
			
			stage.setTitle("CAI");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	
	}
	
}
