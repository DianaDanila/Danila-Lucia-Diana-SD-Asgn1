package client;

import command.Response;
import command.ViewUsersCommand;
import command.ViewUsersResponse;
import dto.UserDTO;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LogInAdmin {
	public static void display(String title) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Button delete = new Button("Delete User");
		Button create = new Button("Create User");
		

		Client.addMessage(new ViewUsersCommand());

		Response r = Client.getResponse();
		VBox layout = new VBox(10);
		layout.getChildren().addAll(create, delete);

		if (r instanceof ViewUsersResponse) {
			ListView<UserDTO> l = new ListView<UserDTO>();
			l.setItems(FXCollections.observableArrayList(((ViewUsersResponse) r).getUsers()));
			l.setMaxWidth(800);
			layout.getChildren().addAll(l);
		}

		delete.setOnAction(e -> {
			DeleteWindow.display("Delete User");
		});
		
		create.setOnAction(e -> {
			CreateWindow.display("Create User");
		});

		
		layout.setAlignment(Pos.TOP_CENTER);
		Scene scene = new Scene(layout, 600, 600);
		window.setScene(scene);
		window.showAndWait();
	}
}
