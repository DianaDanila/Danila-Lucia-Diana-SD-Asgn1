package client;

import command.DeleteUserCommand;
import command.DeleteUserResponse;
import command.Response;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DeleteWindow {
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

		Button updateButton = new Button("Delete");
		GridPane.setConstraints(updateButton, 1, 2);
		
		updateButton.setOnAction(e -> {
			Client.addMessage(new DeleteUserCommand(name.getText()));
			Response r = Client.getResponse();

			if (r instanceof DeleteUserResponse) {
				window.close();
			}
		});
		
		grid.getChildren().addAll(nameLabel, name, updateButton);
		grid.setAlignment(Pos.CENTER);

		Scene scene = new Scene(grid, 300, 200);
		window.setScene(scene);
		window.show();
	}
}
