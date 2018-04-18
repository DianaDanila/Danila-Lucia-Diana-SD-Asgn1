package ui.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnrolView {
	Stage window;
	TextField iduser;
	TextField idt;
	Button enrol;
	
	public EnrolView() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);

		Label userLabel = new Label("User:");
		GridPane.setConstraints(userLabel, 0, 0);

		iduser = new TextField();
		iduser.setPromptText("user");
		GridPane.setConstraints(iduser, 1, 0);

		Label passLabel = new Label("Tournament:");
		GridPane.setConstraints(passLabel, 0, 1);

		idt = new TextField();
		idt.setPromptText("Torunament");
		GridPane.setConstraints(idt, 2, 0);

		enrol = new Button("Enrol");
		VBox layout = new VBox(10);

		layout.getChildren().addAll(userLabel, iduser, passLabel, idt, enrol);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 200, 300);
		window.setScene(scene);
		window.show();
	}
	
	public int getUser() {
		return Integer.parseInt(this.iduser.getText());
	}
	
	public int getT() {
		return  Integer.parseInt(this.idt.getText());
	}
	
	public void updateButtonHandler(EventHandler<ActionEvent> e) {
		this.enrol.setOnAction(e);
	}
	
	
	public void close() {
		window.close();
	}
}
