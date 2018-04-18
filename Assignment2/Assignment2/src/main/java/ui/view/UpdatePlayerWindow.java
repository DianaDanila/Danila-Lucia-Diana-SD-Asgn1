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

public class UpdatePlayerWindow {
	
	Stage window;
	TextField user;
	TextField pass;
	Button update;

	public UpdatePlayerWindow() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);

		Label userLabel = new Label("User:");
		GridPane.setConstraints(userLabel, 0, 0);

		user = new TextField();
		user.setPromptText("name");
		GridPane.setConstraints(user, 1, 0);

		Label passLabel = new Label("Password:");
		GridPane.setConstraints(passLabel, 0, 1);

		pass = new TextField();
		pass.setPromptText("password");
		GridPane.setConstraints(pass, 2, 0);

		update = new Button("Update");
		VBox layout = new VBox(10);

		layout.getChildren().addAll(userLabel, user, passLabel, pass, update);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 200, 300);
		window.setScene(scene);
		window.show();
	}
	
	public String getName() {
		return this.user.getText();
	}
	
	public String getPass() {
		return this.pass.getText();
	}
	
	public void updateButtonHandler(EventHandler<ActionEvent> e) {
		this.update.setOnAction(e);
	}
	
	
	public void close() {
		window.close();
	}
	
}
