package org.UserInterfaceLayer;

import javafx.stage.*;
import pingpong.Game;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.BusinessLayer.GameBL;

public class ViewG {

	@SuppressWarnings("unchecked")
	public static void display(int a) {
		Stage window = new Stage();

		Button updateS = new Button("Update Score");

		Label idl = new Label("Idgame:");
		GridPane.setConstraints(idl, 0, 0);

		final TextField id = new TextField();
		id.setPromptText("id");
		GridPane.setConstraints(id, 1, 0);

		Label sideL = new Label("Side(1,2):");
		GridPane.setConstraints(sideL, 0, 1);

		final TextField side = new TextField();
		side.setPromptText("1/2");
		GridPane.setConstraints(side, 2, 0);

		Label idPL = new Label("idPlayer:");
		GridPane.setConstraints(idPL, 0, 2);

		final TextField idp = new TextField();
		idp.setPromptText("idPlayer");
		GridPane.setConstraints(idp, 3, 0);

		updateS.setOnAction(e -> {
			GameBL g = new GameBL();
			if (a == 0) {
				g.updateScore(Integer.parseInt(id.getText()), Integer.parseInt(side.getText()));
			} else {
				g.updateScoreByPlayer(Integer.parseInt(id.getText()), Integer.parseInt(side.getText()),
						Integer.parseInt(idp.getText()));
			}
			window.close();
		});

		TableView<Game> table;

		TableColumn<Game, Double> idColumn = new TableColumn<>("Id");
		idColumn.setMinWidth(200);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("idgames"));

		TableColumn<Game, Double> sp1 = new TableColumn<>("ScoreP1");
		sp1.setMinWidth(200);
		sp1.setCellValueFactory(new PropertyValueFactory<>("scoreP1"));

		TableColumn<Game, Double> sp2 = new TableColumn<>("ScoreP2");
		sp2.setMinWidth(100);
		sp2.setCellValueFactory(new PropertyValueFactory<>("scoreP2"));

		TableColumn<Game, Double> m = new TableColumn<>("IdMatch");
		m.setMinWidth(100);
		m.setCellValueFactory(new PropertyValueFactory<>("idm"));

		TableColumn<Game, Double> winnerColumn = new TableColumn<>("IdWinner");
		winnerColumn.setMinWidth(100);
		winnerColumn.setCellValueFactory(new PropertyValueFactory<>("idw"));

		table = new TableView<>();
		GameBL g = new GameBL();
		table.setItems(g.getGames());
		table.getColumns().addAll(idColumn, sp1, sp2, m, winnerColumn);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(idl, id, sideL, side, idPL, idp, updateS, table);

		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.showAndWait();
	}
}
