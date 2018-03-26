package org.UserInterfaceLayer;

import javafx.stage.*;
import pingpong.Tournament;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import org.BusinessLayer.TournamentBL;

import javafx.geometry.*;

public class CreateT {

	public static void display() {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);

		Label nameLabel = new Label("Name:");
		GridPane.setConstraints(nameLabel, 0, 0);

		final TextField name = new TextField();
		name.setPromptText("name");
		GridPane.setConstraints(name, 1, 0);

		Label statusLabel = new Label("Status:");
		GridPane.setConstraints(statusLabel, 0, 1);

		final TextField stat = new TextField();
		stat.setPromptText("Status");
		GridPane.setConstraints(stat, 2, 0);

		Label idwL = new Label("IdWinner:");
		GridPane.setConstraints(idwL, 0, 2);

		final TextField id = new TextField();
		id.setPromptText("IdWinner");
		GridPane.setConstraints(id, 3, 1);

		Button create = new Button("Create");

		create.setOnAction(e -> {
			TournamentBL tb = new TournamentBL();
			Tournament t = new Tournament(name.getText(), stat.getText(), Integer.parseInt(id.getText()));
			tb.insert(t);
			window.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(nameLabel, name, statusLabel, stat, idwL, id, create);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 200, 300);
		window.setScene(scene);
		window.showAndWait();
	}
}
