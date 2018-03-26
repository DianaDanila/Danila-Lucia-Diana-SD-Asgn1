package org.UserInterfaceLayer;

import javafx.stage.*;
import pingpong.User;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import org.BusinessLayer.UserBL;

public class ViewU {

	@SuppressWarnings("unchecked")
	public static void display() {
		Stage window = new Stage();

		Button deleteU = new Button("Delete User by id");

		Label idl = new Label("IdUser:");
		GridPane.setConstraints(idl, 0, 0);

		final TextField id = new TextField();
		id.setPromptText("id");
		GridPane.setConstraints(id, 1, 0);

		deleteU.setOnAction(e -> {
			UserBL u = new UserBL();
			u.delete(Integer.parseInt(id.getText()));
			window.close();
		});

		TableView<User> table;

		TableColumn<User, Double> idColumn = new TableColumn<>("Id");
		idColumn.setMinWidth(200);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("iduser"));

		TableColumn<User, String> user = new TableColumn<>("user");
		user.setMinWidth(200);
		user.setCellValueFactory(new PropertyValueFactory<>("user"));

		TableColumn<User, String> mail = new TableColumn<>("mail");
		mail.setMinWidth(100);
		mail.setCellValueFactory(new PropertyValueFactory<>("mail"));

		TableColumn<User, String> password = new TableColumn<>("password");
		password.setMinWidth(100);
		password.setCellValueFactory(new PropertyValueFactory<>("password"));

		TableColumn<User, Boolean> admin = new TableColumn<>("admin");
		admin.setMinWidth(100);
		admin.setCellValueFactory(new PropertyValueFactory<>("admin"));

		table = new TableView<>();
		UserBL u = new UserBL();
		table.setItems(u.getUsers());
		table.getColumns().addAll(idColumn, user, mail, password, admin);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(idl, id, deleteU, table);

		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.showAndWait();
	}
}
