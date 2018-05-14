package client;

import command.CreateUserCommand;
import command.CreateUserResponse;
import command.Response;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CreateWindow {
	public static void display(String title) {
		Stage window = new Stage();
		
		window.setTitle(title);
		
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

		Button updateButton = new Button("Create");
		GridPane.setConstraints(updateButton, 1, 2);
		
		updateButton.setOnAction(e -> {
			Client.addMessage(new CreateUserCommand(name.getText(), pass.getText()));
			Response r = Client.getResponse();

			if (r instanceof CreateUserResponse) {
				window.close();
			}
		});
		
		grid.getChildren().addAll(nameLabel, name, passLabel, pass, updateButton);
		grid.setAlignment(Pos.CENTER);

		Scene scene = new Scene(grid, 300, 200);
		window.setScene(scene);
		window.show();
	}
}
