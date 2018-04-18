package ui.view;

import datamodel.sd.bll.TournamentBLL;
import datamodel.sd.dao.TournamentDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.model.Tournament;
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

public class ViewT {
	Stage window;
	Button updateT;
	Button viewBy;
	Button deleteT;
	
	TextField name;
	TextField id;
	
	@SuppressWarnings("unchecked")
	public ViewT(int a) {
		window = new Stage();

		TableView<Tournament> table;

		TableColumn<Tournament, Double> idColumn = new TableColumn<>("Id");
		idColumn.setMinWidth(200);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Tournament, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Tournament, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setMinWidth(100);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		TableColumn<Tournament, String> typeColumn = new TableColumn<>("Type");
		typeColumn.setMinWidth(200);
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

		TableColumn<Tournament, String> winnerColumn = new TableColumn<>("Winner");
		winnerColumn.setMinWidth(100);
		winnerColumn.setCellValueFactory(new PropertyValueFactory<>("idwinner"));
		
		TableColumn<Tournament, Double> feeColumn = new TableColumn<>("Fee");
		feeColumn.setMinWidth(200);
		feeColumn.setCellValueFactory(new PropertyValueFactory<>("fee"));
		
		TableColumn<Tournament, Double> moneyColumn = new TableColumn<>("Winmoney");
		moneyColumn.setMinWidth(200);
		moneyColumn.setCellValueFactory(new PropertyValueFactory<>("winmoney"));

		table = new TableView<>();
		TournamentDao tdao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
		TournamentBLL t = new TournamentBLL(tdao);
		
		table.setItems(FXCollections.observableArrayList(t.getTournaments()));
		table.getColumns().addAll(idColumn, nameColumn, typeColumn, feeColumn, moneyColumn, statusColumn, winnerColumn);

		VBox vBox = new VBox();

		//if (a == 1) {
			updateT = new Button("Update Torunament");
			viewBy = new Button("ViewBy Torunament");
			deleteT = new Button("Delete Tournament by id");

			Label idl = new Label("Id:");
			GridPane.setConstraints(idl, 0, 0);

			id = new TextField();
			id.setPromptText("id");
			GridPane.setConstraints(id, 1, 0);

			Label namel = new Label("Name:");
			GridPane.setConstraints(namel, 0, 1);

			name = new TextField();
			name.setPromptText("name");
			GridPane.setConstraints(name, 2, 0);

			vBox.getChildren().addAll(updateT, viewBy, deleteT, idl, id, namel, name, table);
		//} else {
			//vBox.getChildren().addAll(table);
		//}

		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	}
	
	public int getId() {
		return Integer.parseInt(this.id.getText());
	}
	
	public String getName() {
		return this.name.getText();
	}
	
	public void updateButtonHandler(EventHandler<ActionEvent> e) {
		this.updateT.setOnAction(e);
	}
	
	public void viewByButtonHandler(EventHandler<ActionEvent> e) {
		this.viewBy.setOnAction(e);
	}
	
	public void deleteButtonHandler(EventHandler<ActionEvent> e) {
		this.deleteT.setOnAction(e);
	}
	
	public void close() {
		window.close();
	}
}
