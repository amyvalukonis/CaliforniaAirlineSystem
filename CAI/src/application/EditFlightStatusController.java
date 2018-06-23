package application;

import java.io.IOException;
import java.util.ArrayList;

import application.Classes.*;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditFlightStatusController {
	@FXML ListView<String> listView;
	@FXML TextField flightStatusTextField;
	@FXML Button backButton;
	@FXML Button editFlightStatusButton;
	ArrayList<Flight> flightsList;
	
	@FXML
    public void initialize() {
		
		this.refreshListView();
    }
	
	private void refreshListView() {
		ObservableList<String> flightsListData = FXCollections.observableArrayList();
        this.flightsList = DatabaseReference.getAvailableFlights();
        
        for (int i = 0; i < this.flightsList.size(); i++) {
			flightsListData.add(this.flightsList.get(i).toString(true));
		} 
		
		this.listView.setItems(flightsListData);
	}
	
	@FXML void handleListViewMouseClick(MouseEvent event) {
		Integer selectedIndex = listView.getSelectionModel().getSelectedIndex();
		Flight selectedFlight = this.flightsList.get(selectedIndex);
	
		this.flightStatusTextField.setText(selectedFlight.getStatus());
		
	}
	
	
	@FXML void handleEditFlightStatus(ActionEvent event) {
		Integer selectedIndex = this.listView.getSelectionModel().getSelectedIndex();
		if (selectedIndex < 0) {
			return;
		}
		DatabaseReference.updateFlightStatus(this.flightStatusTextField.getText(), this.flightsList.get(selectedIndex).getId());
		
		
		this.refreshListView();
	}
	
	@FXML void handleBack(ActionEvent event) {
		//go back to main menu
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
}
