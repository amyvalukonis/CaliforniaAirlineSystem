package application;




import java.io.IOException;
import java.util.ArrayList;

import application.Classes.DatabaseReference;
import application.Classes.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditEmployeeController {
	@FXML ListView<String> listView;
	@FXML TextField firstNameTextField;
	@FXML TextField lastNameTextField;
	@FXML CheckBox isFlightCrewCheckBox;
	@FXML CheckBox isOfficeEmployeeCheckBox;
	@FXML Button backButton;
	ArrayList<Employee> employeesList;
	
	@FXML
    public void initialize() {
		this.refreshListView();
    }
	
	@FXML public void setUserAsOfficeEmployee(ActionEvent event) {
		this.isFlightCrewCheckBox.setSelected(false);
	}
	
	@FXML public void setUserAsFlightCrew(ActionEvent event) {
		this.isOfficeEmployeeCheckBox.setSelected(false);
	}
	
	private void refreshListView() {
		ObservableList<String> employeesListData = FXCollections.observableArrayList();
        this.employeesList = DatabaseReference.getEmployees();
        
        for (int i = 0; i < this.employeesList.size(); i++) {
			employeesListData.add(this.employeesList.get(i).toString());
		} 
		
		listView.setItems(employeesListData);
	}
	
	@FXML void handleListViewMouseClick(MouseEvent event) {
		Integer selectedIndex = listView.getSelectionModel().getSelectedIndex();
		
		Employee selectedEmployee = this.employeesList.get(selectedIndex);
		
		this.firstNameTextField.setText(selectedEmployee.getFirstName());
		this.lastNameTextField.setText(selectedEmployee.getLastName());
		if (selectedEmployee.getIsFlightCrew()) {
			this.isFlightCrewCheckBox.setSelected(true);
			this.isOfficeEmployeeCheckBox.setSelected(false);
		}
		else {
			this.isOfficeEmployeeCheckBox.setSelected(true);
			this.isFlightCrewCheckBox.setSelected(false);
		}
		
	}
	
	@FXML void handleDeleteEmployee(ActionEvent event) {
		Integer selectedIndex = this.listView.getSelectionModel().getSelectedIndex();
		DatabaseReference.deleteEmployee(this.employeesList.get(selectedIndex).getId());
		this.refreshListView();
	}
	
	@FXML void handleEditEmployee(ActionEvent event) {
		Integer selectedIndex = this.listView.getSelectionModel().getSelectedIndex();
		if (selectedIndex < 0) {
			return;
		}
		
		DatabaseReference.updateEmployee(this.firstNameTextField.getText(), this.lastNameTextField.getText(), 
				this.isFlightCrewCheckBox.isSelected(), this.employeesList.get(selectedIndex).getId());
		
		
		this.refreshListView();
		
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
	
	
}
