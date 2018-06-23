package application;

import java.io.IOException;

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
public class BaggagePolicyController {
	
	@FXML Button back;

	@FXML void handleBack(ActionEvent event) throws IOException {
		try {
			Stage stage = (Stage)back.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("CheckInSubmit.fxml"));
			Scene scene = new Scene(root, 600, 400);
			
			stage.setTitle("Baggage Policy");
			stage.setScene(scene);
			stage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
