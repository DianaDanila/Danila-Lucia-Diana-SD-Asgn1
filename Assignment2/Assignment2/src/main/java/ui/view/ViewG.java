package ui.view;

import datamodel.sd.bll.GameBLL;
import datamodel.sd.dao.GameDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.model.Game;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewG {
	Stage window;
	Button updateS;
	TextField id;
	TextField side;
	TextField idp;

	@SuppressWarnings("unchecked")
	public ViewG() {
		window = new Stage();

		updateS = new Button("Update Score");

		Label idl = new Label("Idgame:");
		GridPane.setConstraints(idl, 0, 0);

		id = new TextField();
		id.setPromptText("id");
		GridPane.setConstraints(id, 1, 0);

		Label sideL = new Label("Side(1,2):");
		GridPane.setConstraints(sideL, 0, 1);

		side = new TextField();
		side.setPromptText("1/2");
		GridPane.setConstraints(side, 2, 0);
		

		TableView<Game> table;

		TableColumn<Game, Double> idColumn = new TableColumn<>("Id");
		idColumn.setMinWidth(200);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Game, Double> sp1 = new TableColumn<>("ScoreP1");
		sp1.setMinWidth(200);
		sp1.setCellValueFactory(new PropertyValueFactory<>("scoreP1"));

		TableColumn<Game, Double> sp2 = new TableColumn<>("ScoreP2");
		sp2.setMinWidth(100);
		sp2.setCellValueFactory(new PropertyValueFactory<>("scoreP2"));

		TableColumn<Game, Double> winnerColumn = new TableColumn<>("IdWinner");
		winnerColumn.setMinWidth(100);
		winnerColumn.setCellValueFactory(new PropertyValueFactory<>("winner"));

		table = new TableView<>();
		
		GameDao gdao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getGameDao();
		GameBLL g = new GameBLL(gdao);
		table.setItems(FXCollections.observableArrayList(g.getGames())); 
		table.getColumns().addAll(idColumn, sp1, sp2, winnerColumn);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(idl, id, sideL, side, updateS, table);

		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	}
	
	public void updateButtonHandler(EventHandler<ActionEvent> e) {
		this.updateS.setOnAction(e);
	}
	
	public int getId() {
		return Integer.parseInt(this.id.getText());
	}
	
	public int getSide() {
		return Integer.parseInt(this.side.getText());
	}
	
	public int getPlayer() {
		return Integer.parseInt(this.idp.getText());
	}
	
	public void close() {
		window.close();
	}
}
