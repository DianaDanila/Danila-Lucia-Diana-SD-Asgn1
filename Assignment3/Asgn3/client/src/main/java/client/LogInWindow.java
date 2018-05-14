package client;

import command.LogInCommand;
import command.LogInResponse;
import command.Response;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LogInWindow {
	
	public static void display(String title) {
		Stage window = new Stage();
		
		window.setTitle("NEWS");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label nameLabel = new Label("Username:");
		GridPane.setConstraints(nameLabel, 0, 0);

		final TextField name = new TextField();
		name.setPromptText("username");
		GridPane.setConstraints(name, 1, 0);

		Label passLabel = new Label("Password:");
		GridPane.setConstraints(passLabel, 0, 1);

		final PasswordField pass = new PasswordField();
		pass.setPromptText("password");
		GridPane.setConstraints(pass, 1, 1);

		Button loginButton = new Button("Log In");
		
		GridPane.setConstraints(loginButton, 1, 2);
		loginButton.setOnAction(e -> {

			Client.addMessage(new LogInCommand(name.getText(), pass.getText()));

			Response r = Client.getResponse();

			if (r instanceof LogInResponse) {
				if (((LogInResponse) r).isCheckLogIn()) {
					if (((LogInResponse) r).isAdmin()) {
						window.close();
						LogInAdmin.display("Admin Window");
					} else {
						window.close();
						LogInWriter.display("Writer Window");
					}
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ERROR");
					alert.setHeaderText(null);
					alert.setContentText("Not valid name or pass!");
					alert.showAndWait();
				}
			}

		});
	
		grid.getChildren().addAll(nameLabel, name, passLabel, pass,loginButton);
		grid.setAlignment(Pos.CENTER);

		Scene scene = new Scene(grid, 300, 200);
		window.setScene(scene);
		window.show();
	}

}
