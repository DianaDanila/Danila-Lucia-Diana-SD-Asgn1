package ui.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateTWindow {
	
	Button create;
	Stage window;
	
	 TextField name;
	 TextField stat;
	 TextField type;
	 TextField fee;

	public CreateTWindow() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);

		Label nameLabel = new Label("Name:");
		GridPane.setConstraints(nameLabel, 0, 0);

		name = new TextField();
		name.setPromptText("name");
		GridPane.setConstraints(name, 1, 0);

		Label statusLabel = new Label("Status:");
		GridPane.setConstraints(statusLabel, 0, 1);

		stat = new TextField();
		stat.setPromptText("Status");
		GridPane.setConstraints(stat, 2, 0);

		
		Label typeLabel = new Label("Type:");
		GridPane.setConstraints(statusLabel, 0, 2);

		type = new TextField();
		type.setPromptText("Type");
		GridPane.setConstraints(type, 3, 1);
		
		Label feeLabel = new Label("Fee:");
		GridPane.setConstraints(statusLabel, 0, 3);

		fee = new TextField();
		fee.setPromptText("Fee");
		GridPane.setConstraints(fee, 4, 1);
		
		
		

		create = new Button("Create");

		
		VBox layout = new VBox(10);

		layout.getChildren().addAll(nameLabel, name, statusLabel, stat, typeLabel, type, feeLabel, fee, create);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 200, 400);
		window.setScene(scene);
		window.show();
	}
	
	public void addCreateButtonHandler(EventHandler<ActionEvent> e) {
		this.create.setOnAction(e);
	}
	
	public String getName() {
		return this.name.getText();
	}
	
	public String getStat() {
		return this.stat.getText();
	}

	public int getFee() {
		return Integer.parseInt(this.fee.getText());
	}
	
	public String getType() {
		return this.type.getText();
	}
	
	public void close() {
		window.close();
	}
}
