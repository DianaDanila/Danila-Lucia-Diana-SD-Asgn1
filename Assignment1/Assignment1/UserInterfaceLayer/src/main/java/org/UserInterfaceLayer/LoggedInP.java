package org.UserInterfaceLayer;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import org.BusinessLayer.GameBL;

import javafx.geometry.*;

public class LoggedInP {
	static boolean answer;

	public static void display(String title, int id) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Button viewT = new Button("View Tournaments");
		Button viewM = new Button("View Matches");
		Button updateP = new Button("Update Player");
		Button updateS = new Button("Update Score");
		Button viewG = new Button("View Games");

		viewT.setOnAction(e -> {
			ViewT.display(0);
		});
		viewM.setOnAction(e -> {
			ViewM.display();
		});
		updateP.setOnAction(e -> {
			UpdatePlayer.display();
		});
		viewG.setOnAction(e -> {
			ViewG.display(1);
		});

		updateS.setOnAction(e -> {
			GameBL g = new GameBL();
			g.updateScoreByPlayer(0, 0, id);
			answer = false;
			window.close();
		});

		VBox layout = new VBox(10);

		layout.getChildren().addAll(viewT, viewM, updateP, viewG);
		layout.setAlignment(Pos.BASELINE_LEFT);
		Scene scene = new Scene(layout, 600, 600);
		window.setScene(scene);
		window.showAndWait();
	}
}
