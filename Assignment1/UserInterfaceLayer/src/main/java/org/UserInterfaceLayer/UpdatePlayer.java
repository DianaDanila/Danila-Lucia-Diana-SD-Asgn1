package org.UserInterfaceLayer;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import org.BusinessLayer.UserBL;

import javafx.geometry.*;

public class UpdatePlayer {

	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);

		Label userLabel = new Label("User:");
		GridPane.setConstraints(userLabel, 0, 0);

		final TextField user = new TextField();
		user.setPromptText("email");
		GridPane.setConstraints(user, 1, 0);

		Label passLabel = new Label("Password:");
		GridPane.setConstraints(passLabel, 0, 1);

		final TextField pass = new TextField();
		pass.setPromptText("password");
		GridPane.setConstraints(pass, 2, 0);

		Button create = new Button("Update");

		create.setOnAction(e -> {
			UserBL g = new UserBL();
			g.update(user.getText(), pass.getText());
			window.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(userLabel, user, passLabel, pass, create);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 200, 300);
		window.setScene(scene);
		window.showAndWait();
	}
}
