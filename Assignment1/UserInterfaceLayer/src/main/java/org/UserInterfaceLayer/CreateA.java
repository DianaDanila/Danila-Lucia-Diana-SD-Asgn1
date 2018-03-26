package org.UserInterfaceLayer;

import javafx.stage.*;
import pingpong.User;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import org.BusinessLayer.UserBL;

import javafx.geometry.*;

public class CreateA {

	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);

		Label nameLabel = new Label("User:");
		GridPane.setConstraints(nameLabel, 0, 0);

		final TextField name = new TextField();
		name.setPromptText("email");
		GridPane.setConstraints(name, 1, 0);

		Label emailLabel = new Label("Email:");
		GridPane.setConstraints(emailLabel, 0, 1);

		final TextField mail = new TextField();
		mail.setPromptText("email");
		GridPane.setConstraints(mail, 2, 0);

		Label passLabel = new Label("Password:");
		GridPane.setConstraints(passLabel, 0, 2);

		final TextField pass = new TextField();
		pass.setPromptText("password");
		GridPane.setConstraints(pass, 3, 1);

		Button create = new Button("Create");

		create.setOnAction(e -> {
			UserBL g = new UserBL();
			User u = new User(name.getText(), mail.getText(), pass.getText());
			g.insert(u);
			window.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(nameLabel, name, emailLabel, mail, passLabel, pass, create);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 200, 300);
		window.setScene(scene);
		window.showAndWait();
	}
}
