package org.UserInterfaceLayer;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class LoggedInA {
	static boolean answer;

	public static void display(String title) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Button createT = new Button("Create Tournament");
		Button viewT = new Button("View Tournament");
		Button viewG = new Button("View Games");
		Button viewU = new Button("View Users");
		Button viewM = new Button("View Matches");

		viewT.setOnAction(e -> {
			ViewT.display(1);
		});
		createT.setOnAction(e -> {
			CreateT.display();
		});

		viewG.setOnAction(e -> {
			ViewG.display(0);
		});
		viewU.setOnAction(e -> {
			ViewU.display();
		});
		viewM.setOnAction(e -> {
			ViewM.display();
		});
		VBox layout = new VBox(10);

		layout.getChildren().addAll(viewT, createT, viewG, viewM, viewU);
		Scene scene = new Scene(layout, 600, 600);
		window.setScene(scene);
		window.showAndWait();
	}
}
