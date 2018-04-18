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

public class CreateAWindow {
	Stage window;
	Button create;
	TextField pass;
	TextField name;

	public CreateAWindow() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);

		Label nameLabel = new Label("User:");
		GridPane.setConstraints(nameLabel, 0, 0);

		name = new TextField();
		name.setPromptText("email");
		GridPane.setConstraints(name, 1, 0);

		Label passLabel = new Label("Password:");
		GridPane.setConstraints(passLabel, 0, 2);

		pass = new TextField();
		pass.setPromptText("password");
		GridPane.setConstraints(pass, 3, 1);

		create = new Button("Create");

		VBox layout = new VBox(10);

		layout.getChildren().addAll(nameLabel, name, passLabel, pass, create);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 200, 300);
		window.setScene(scene);
		window.show();
	}

	public String getName() {
		return this.name.getText();
	}

	public String getPass() {
		return this.pass.getText();
	}

	public void close() {
		window.close();
	}

	public void addCreateButtonHandler(EventHandler<ActionEvent> e) {
		this.create.setOnAction(e);
	}
}
