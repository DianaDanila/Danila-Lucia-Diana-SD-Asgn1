package ui.view;

import datamodel.sd.bll.MatchBLL;
import datamodel.sd.dao.MatchDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.model.Match;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewM {
	Stage window;
	
	@SuppressWarnings("unchecked")
	public ViewM() {
		window = new Stage();

		TableView<Match> table;

		TableColumn<Match, Double> idColumn = new TableColumn<>("Id");
		idColumn.setMinWidth(200);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Match, Double> p1 = new TableColumn<>("P1");
		p1.setMinWidth(200);
		p1.setCellValueFactory(new PropertyValueFactory<>("player1"));

		TableColumn<Match, Double> p2 = new TableColumn<>("P2");
		p2.setMinWidth(100);
		p2.setCellValueFactory(new PropertyValueFactory<>("player2"));

		TableColumn<Match, Double> t = new TableColumn<>("Tournament");
		t.setMinWidth(100);
		t.setCellValueFactory(new PropertyValueFactory<>("tournament"));

		TableColumn<Match, Double> winnerColumn = new TableColumn<>("Winner");
		winnerColumn.setMinWidth(100);
		winnerColumn.setCellValueFactory(new PropertyValueFactory<>("idw"));

		table = new TableView<>();
		MatchDao mdao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getMatchDao();
		MatchBLL m = new MatchBLL(mdao);
		table.setItems(FXCollections.observableArrayList(m.getMatchs()));
		table.getColumns().addAll(idColumn, p1, p2, t, winnerColumn);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);

		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	}
	
	public void close() {
		window.close();
	}
}
