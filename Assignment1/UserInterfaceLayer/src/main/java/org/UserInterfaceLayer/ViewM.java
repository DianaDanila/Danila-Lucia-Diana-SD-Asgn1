package org.UserInterfaceLayer;

import javafx.stage.*;
import pingpong.Match;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.BusinessLayer.MatchBL;

public class ViewM {

	@SuppressWarnings("unchecked")
	public static void display() {
		Stage window = new Stage();

		TableView<Match> table;

		TableColumn<Match, Double> idColumn = new TableColumn<>("Id");
		idColumn.setMinWidth(200);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("idmatch"));

		TableColumn<Match, Double> p1 = new TableColumn<>("P1");
		p1.setMinWidth(200);
		p1.setCellValueFactory(new PropertyValueFactory<>("p1"));

		TableColumn<Match, Double> p2 = new TableColumn<>("P2");
		p2.setMinWidth(100);
		p2.setCellValueFactory(new PropertyValueFactory<>("p2"));

		TableColumn<Match, Double> t = new TableColumn<>("Tournament");
		t.setMinWidth(100);
		t.setCellValueFactory(new PropertyValueFactory<>("t"));

		TableColumn<Match, Double> winnerColumn = new TableColumn<>("Winner");
		winnerColumn.setMinWidth(100);
		winnerColumn.setCellValueFactory(new PropertyValueFactory<>("idw"));

		table = new TableView<>();
		MatchBL m = new MatchBL();
		table.setItems(m.getMatches());
		table.getColumns().addAll(idColumn, p1, p2, t, winnerColumn);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);

		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.showAndWait();
	}
}
