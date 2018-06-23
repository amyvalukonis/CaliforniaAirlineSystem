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

public class BusinessRulesController {
	
	@FXML Button back; 
	
	@FXML public void handleBack(ActionEvent event) {	
		try {
            Stage stage = (Stage)back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("EmployeeScheduleFlight.fxml"));
            Scene scene = new Scene(root, 600, 400);

            stage.setTitle("Schedule Flight");
            stage.setScene(scene);
            stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}			
		
	}
	
	
}