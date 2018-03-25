package org.UserInterfaceLayer;

import javafx.stage.*;
import pingpong.Tournament;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.BusinessLayer.TournamentBL;
import org.BusinessLayer.UserBL;

public class ViewT {

	@SuppressWarnings("unchecked")
	public static void display(int a) {
		Stage window = new Stage();

		TableView<Tournament> table;

		// id column
		TableColumn<Tournament, Double> idColumn = new TableColumn<>("Id");
		idColumn.setMinWidth(200);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("idtournament"));

		// Name column
		TableColumn<Tournament, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		// Price column
		TableColumn<Tournament, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setMinWidth(100);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

		// Quantity column
		TableColumn<Tournament, String> winnerColumn = new TableColumn<>("Winner");
		winnerColumn.setMinWidth(100);
		winnerColumn.setCellValueFactory(new PropertyValueFactory<>("idwinner"));
		
		
		
		table = new TableView<>();
		TournamentBL t = new TournamentBL();
		table.setItems(t.getTournaments());
		table.getColumns().addAll(idColumn, nameColumn, statusColumn, winnerColumn);

		VBox vBox = new VBox();
		

		if(a==1) {
			Button updateT = new Button("Update Torunament");
			Button deleteT = new Button("Delete Tournament by id");
			
			Label idl = new Label("Id:");
			GridPane.setConstraints(idl, 0, 0);
			
			final TextField id = new TextField();
			id.setPromptText("id");
			GridPane.setConstraints(id, 1, 0);
			
			
			Label namel = new Label("Name:");
			GridPane.setConstraints(namel, 0, 1);
			
			final TextField name = new TextField();
			name.setPromptText("name");
			GridPane.setConstraints(name, 2, 0);
			
			updateT.setOnAction(e -> {
				t.updateN(Integer.parseInt(id.getText()), name.getText());
				window.close();
			});
			
			deleteT.setOnAction(e -> {
				t.delete(Integer.parseInt(id.getText()));
				window.close();
			});
			vBox.getChildren().addAll(updateT, deleteT, idl, id, namel, name, table);
		} else {
			vBox.getChildren().addAll(table);
		}
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.showAndWait();
	}
}
