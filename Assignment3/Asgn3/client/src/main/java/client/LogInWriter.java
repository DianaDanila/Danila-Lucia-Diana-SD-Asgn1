package client;

import javafx.stage.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class LogInWriter {
	public static void display(String title) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Button write = new Button("Write Article");
		Button update = new Button("Update Password");
		GridPane.setConstraints(write, 0, 0);
		GridPane.setConstraints(update, 1, 1);
		
		
		write.setOnAction(e -> {
			WriteWindow.display("Write Article");
		});
		
		update.setOnAction(e -> {
			UpdateWindow.display("Update Password");
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(write, update);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 150, 100);
		window.setScene(scene);
		window.showAndWait();
	}
}
