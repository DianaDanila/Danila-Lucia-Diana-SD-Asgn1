package ui.view;

import datamodel.sd.bll.TournamentBLL;
import datamodel.sd.dao.TournamentDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.model.Tournament;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewByTWindow {
	Stage window;

	@SuppressWarnings("unchecked")
	public ViewByTWindow(String s) {
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
		
		if(s.equals("paid")||s.equals("free"))
			table.setItems(FXCollections.observableArrayList(t.findListByType(s)));
		if(s.equals("Finished") || s.equals("Ongoing") || s.equals("Upcoming"))
			table.setItems(FXCollections.observableArrayList(t.findListByStatus(s)));
		
		table.getColumns().addAll(idColumn, nameColumn, typeColumn, feeColumn, moneyColumn, statusColumn, winnerColumn);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);
		
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	}
}
