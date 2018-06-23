package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import javafx.stage.Stage;

public class EmployeePassengersForFlightsController {
	@FXML ListView listView;
	@FXML Button backButton;
	
	@FXML void handleBack(ActionEvent event) {
		try {
			Stage stage = (Stage)backButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("MainMenuEmployee.fxml"));
			Scene scene = new Scene(root,600,400);
			
			stage.setTitle("CAI");
			stage.setScene(scene);
			stage.show();
			
			
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	@FXML
    public void initialize() {
		this.refreshListView();
    }
	
	private void refreshListView() {
		ObservableList<String> weeklyFlightsData = FXCollections.observableArrayList();
        ArrayList<Flight> allFlights = DatabaseReference.getAvailableFlights();
        
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date currentDatePlusOneWeek = calendar.getTime();
        
        
        for (int i = 0; i < allFlights.size(); i++) {
        	//only queue flights within the next week
			Flight flight = allFlights.get(i);
        	weeklyFlightsData.add(flight.toString(true) + " || Seats Taken: " + flight.getSeatsTaken() + "/" + flight.getSeats());
		} 
		
		listView.setItems(weeklyFlightsData);
	}
}
