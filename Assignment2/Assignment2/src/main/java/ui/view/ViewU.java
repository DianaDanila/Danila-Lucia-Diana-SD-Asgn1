package ui.view;

import datamodel.sd.bll.UserBLL;
import datamodel.sd.dao.UserDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.model.User;
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

public class ViewU {
	Stage window;
	Button deleteU;
	Button add;
	Button with;
	TextField id;
	TextField money;
	
	@SuppressWarnings("unchecked")
	public ViewU() {
		window = new Stage();

		deleteU = new Button("Delete User by id");
		add = new Button("Add");
		with = new Button("Withdraw");

		Label idl = new Label("IdUser:");
		GridPane.setConstraints(idl, 0, 0);

		id = new TextField();
		id.setPromptText("id");
		GridPane.setConstraints(id, 1, 0);
		
		Label moneyL = new Label("Sum:");
		GridPane.setConstraints(idl, 0, 1);

		money = new TextField();
		id.setPromptText("Money");
		GridPane.setConstraints(money, 2, 0);

		

		TableView<User> table;

		TableColumn<User, Double> idColumn = new TableColumn<>("Id");
		idColumn.setMinWidth(200);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<User, String> user = new TableColumn<>("user");
		user.setMinWidth(200);
		user.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<User, String> password = new TableColumn<>("password");
		password.setMinWidth(100);
		password.setCellValueFactory(new PropertyValueFactory<>("password"));

		TableColumn<User, Boolean> admin = new TableColumn<>("admin");
		admin.setMinWidth(100);
		admin.setCellValueFactory(new PropertyValueFactory<>("admin"));
		
		TableColumn<User, Double> balance = new TableColumn<>("balance");
		balance.setMinWidth(100);
		balance.setCellValueFactory(new PropertyValueFactory<>("accountbalance"));

		table = new TableView<>();
		UserDao udao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
		UserBLL u = new UserBLL(udao);
		
		table.setItems(FXCollections.observableArrayList(u.getUsers()));
		table.getColumns().addAll(idColumn, user, password, admin, balance);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(idl, id, moneyL, money, deleteU,add,with, table);

		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	}
	
	public void deleteButtonHandler(EventHandler<ActionEvent> e) {
		this.deleteU.setOnAction(e);
	}
	
	public void addButtonHandler(EventHandler<ActionEvent> e) {
		this.add.setOnAction(e);
	}
	
	public void withButtonHandler(EventHandler<ActionEvent> e) {
		this.with.setOnAction(e);
	}
	
	public int getId() {
		return Integer.parseInt(this.id.getText());
	}
	
	public int getMoney() {
		return Integer.parseInt(this.money.getText());
	}
	
	public void close() {
		window.close();
	}
}
