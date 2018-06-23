package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import application.Classes.DatabaseReference;
import application.Classes.FlightsScheduler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FlightSchedulerController {
	@FXML DatePicker dayPicker;
	@FXML ChoiceBox<String> departureLocationChoiceBox;
	@FXML ChoiceBox<String> destinationLocationChoiceBox;
	@FXML TextField flightDurationTextField;
	@FXML Button backButton;
	@FXML Button businessRules; 
	@FXML Button scheduleFlightButton;
	@FXML TextField minuteIntervalTextField;
	@FXML ListView<String> listView;
	
	ArrayList<Date> recommendedFlightSchedules;
	ArrayList<String> locations;
	
	HashMap<String, HashMap<String, Integer>> flightHistory = DatabaseReference.getFlightHistory();
	
	
	@FXML
    public void initialize() {
		ObservableList<String> locationsData = FXCollections.observableArrayList();
        this.locations = new ArrayList<>();
		this.locations.add("San Luis Obispo");
        this.locations.add("Los Angeles");
        this.locations.add("San Francisco");
        this.locations.add("San Diego");
        this.locations.add("Arizona");
        this.locations.add("Seattle");
        this.locations.add("Arizona");
        this.locations.add("Dallas");
        
        
        for (int i = 0; i < this.locations.size(); i++) {
			locationsData.add(this.locations.get(i));
		} 
        this.departureLocationChoiceBox.setItems(locationsData);
        this.destinationLocationChoiceBox.setItems(locationsData);
        
        this.departureLocationChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        		@Override
    			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    				String departureLocation = departureLocationChoiceBox.getValue();
    				String destinationLocation = destinationLocationChoiceBox.getValue();
    				
    				if (departureLocation == null || destinationLocation == null || destinationLocation.equals(departureLocation)) {
    					return;
    				}
    				
    				flightDurationTextField.setText(Integer.toString(getFlightDurationEstimate(departureLocation, destinationLocation)));
    				return;
    			}
        });
        
        

        this.destinationLocationChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
        		@Override
    			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    				String departureLocation = departureLocationChoiceBox.getValue();
    				String destinationLocation = destinationLocationChoiceBox.getValue();
    				
    				if (destinationLocation.equals(departureLocation)) {
    					return;
    				}
    				
    				flightDurationTextField.setText(Integer.toString(getFlightDurationEstimate(departureLocation, destinationLocation)));
    				return;
    			}
        });
    }
	
	private Integer getFlightDurationEstimate(String departureLocation, String destinationLocation) {
		Integer flightDurationInMinutes = 30;
		if (departureLocation.equals("San Luis Obispo")) {
 	    	if (destinationLocation.equals("Los Angeles")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("Los Angeles");
 	    	}
 	    	else if (destinationLocation.equals("San Francisco")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("San Francisco");
 	    	}
 	    	else if (destinationLocation.equals("San Diego")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("San Diego");
 	    	}
 	    	else if (destinationLocation.equals("Arizona")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("Arizona");
 	    	}
 	    	else if (destinationLocation.equals("Seattle")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("Seattle");
 	    	}
 	    	else if (destinationLocation.equals("Dallas")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("Dallas");
 	    	}
 	    	
 	    }
 	    else {
 	    	if (departureLocation.equals("Los Angeles")) {
 	    		flightDurationInMinutes = flightHistory.get("Los Angeles").get("San Luis Obispo");
 	    	}
 	    	else if (departureLocation.equals("San Francisco")) {
 	    		flightDurationInMinutes = flightHistory.get("San Francisco").get("San Luis Obispo");
 	    	}
 	    	else if (departureLocation.equals("San Diego")) {
 	    		flightDurationInMinutes = flightHistory.get("San Diego").get("San Luis Obispo");
 	    	}
 	    	else if (departureLocation.equals("Arizona")) {
 	    		flightDurationInMinutes = flightHistory.get("Arizona").get("San Luis Obispo");
 	    	}
 	    	else if (departureLocation.equals("Seattle")) {
 	    		flightDurationInMinutes = flightHistory.get("Seattle").get("San Luis Obispo");
 	    	}
 	    	else if (departureLocation.equals("Dallas")) {
 	    		flightDurationInMinutes = flightHistory.get("Dallas").get("San Luis Obispo");
 	    	}
 	    }
		return flightDurationInMinutes;
	}
	
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
	
	@FXML public void handleBusinessRules(ActionEvent event) {	
		try {
            Stage stage = (Stage)businessRules.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("BusinessRules.fxml"));
            Scene scene = new Scene(root, 600, 400);

            stage.setTitle("Business Rules for Flight Scheduling");
            stage.setScene(scene);
            stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}			
		
	}

	
	@FXML public void handleGiveRecommendations(ActionEvent event) {
		LocalDate datePicked = this.dayPicker.getValue();
		String destinationLocation = this.destinationLocationChoiceBox.getValue();
		String departureLocation = this.departureLocationChoiceBox.getValue();
		Integer flightDuration = Integer.parseInt(this.flightDurationTextField.getText());
		Integer minuteInterval = Integer.parseInt(this.minuteIntervalTextField.getText());
		
		//Cannot have same destination and departure location
		if (destinationLocation.equals(departureLocation)) {
			return;
		}
		
		Calendar calendar = Calendar.getInstance();
		Date startDate = java.sql.Date.valueOf(datePicked);
		calendar.setTime(startDate);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date endDate = calendar.getTime();
		
		HashMap<String, Object> rules = new HashMap<>();
		rules.put("Start_Date", startDate);
		rules.put("End_Date", endDate);
		rules.put("Minute_Interval", minuteInterval);
		
		Integer flightDurationInMinutes = 30;
		
		
		
		if (departureLocation.equals("San Luis Obispo")) {
 	    	if (destinationLocation.equals("Los Angeles")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("Los Angeles");
 	    	}
 	    	else if (destinationLocation.equals("San Francisco")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("San Francisco");
 	    	}
 	    	else if (destinationLocation.equals("San Diego")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("San Diego");
 	    	}
 	    	else if (destinationLocation.equals("Arizona")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("Arizona");
 	    	}
 	    	else if (destinationLocation.equals("Seattle")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("Seattle");
 	    	}
 	    	else if (destinationLocation.equals("Dallas")) {
 	    		flightDurationInMinutes = flightHistory.get("San Luis Obispo").get("Dallas");
 	    	}
 	    	
 	    }
 	    else {
 	    	if (departureLocation.equals("Los Angeles")) {
 	    		flightDurationInMinutes = flightHistory.get("Los Angeles").get("San Luis Obispo");
 	    	}
 	    	else if (departureLocation.equals("San Francisco")) {
 	    		flightDurationInMinutes = flightHistory.get("San Francisco").get("San Luis Obispo");
 	    	}
 	    	else if (departureLocation.equals("San Diego")) {
 	    		flightDurationInMinutes = flightHistory.get("San Diego").get("San Luis Obispo");
 	    	}
 	    	else if (departureLocation.equals("Arizona")) {
 	    		flightDurationInMinutes = flightHistory.get("Arizona").get("San Luis Obispo");
 	    	}
 	    	else if (departureLocation.equals("Seattle")) {
 	    		flightDurationInMinutes = flightHistory.get("Seattle").get("San Luis Obispo");
 	    	}
 	    	else if (departureLocation.equals("Dallas")) {
 	    		flightDurationInMinutes = flightHistory.get("Dallas").get("San Luis Obispo");
 	    	}
 	    	
 	    }
		
		this.recommendedFlightSchedules = FlightsScheduler.getRecommendedSchedules(rules, flightDuration, destinationLocation, departureLocation);
		
		ObservableList<String> listData = FXCollections.observableArrayList();
        
        for (int i = 0; i < this.recommendedFlightSchedules.size(); i++) {
			listData.add(this.recommendedFlightSchedules.get(i).toString());
		} 
		
		listView.setItems(listData);
		
	}	
	
	@FXML public void handleScheduleFlight(ActionEvent event) {
		if (this.listView.getSelectionModel().getSelectedIndex() < 0) {
			return;
		}
		
		
		
		try {
			Stage stage = (Stage)backButton.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeScheduleFlightPhase2Controller.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root,600,400);
			
			stage.setTitle("CAI");
			stage.setScene(scene);
			stage.show();
			
			EmployeeScheduleFlightPhase2Controller controller = (EmployeeScheduleFlightPhase2Controller)loader.getController();
			Date departureDate = this.recommendedFlightSchedules.get(this.listView.getSelectionModel().getSelectedIndex());
			String destinationLocation = this.destinationLocationChoiceBox.getValue();
			String departureLocation = this.departureLocationChoiceBox.getValue();
			Integer flightDuration = Integer.parseInt(this.flightDurationTextField.getText());
			Integer minuteInterval = Integer.parseInt(this.minuteIntervalTextField.getText());
			controller.departureDate = departureDate;
			controller.destinationLocation = destinationLocation;
			controller.departureLocation = departureLocation;
			controller.flightDuration = flightDuration;
			controller.minuteInterval = minuteInterval;
			controller.initializeController();
		}
		catch (IOException e) {
            e.printStackTrace();
        }
	}
}
