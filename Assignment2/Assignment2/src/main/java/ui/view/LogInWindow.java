package ui.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LogInWindow {
	private Stage window;
	private Button loginButton;
	private Button createButton;
	
	private TextField name;
	private PasswordField pass;

	public LogInWindow(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Ping Pong Tournaments");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label nameLabel = new Label("Name:");
		GridPane.setConstraints(nameLabel, 0, 0);

		name = new TextField();
		name.setPromptText("name");
		GridPane.setConstraints(name, 1, 0);

		Label passLabel = new Label("Password:");
		GridPane.setConstraints(passLabel, 0, 1);

		pass = new PasswordField();
		pass.setPromptText("password");
		GridPane.setConstraints(pass, 1, 1);

		loginButton = new Button("Log In");
		createButton = new Button("Create Account");
		
		GridPane.setConstraints(loginButton, 1, 2);
		GridPane.setConstraints(createButton, 1, 3);

		grid.getChildren().addAll(nameLabel, name, passLabel, pass, loginButton, createButton);
		grid.setAlignment(Pos.CENTER);

		Scene scene = new Scene(grid, 300, 200);
		window.setScene(scene);
		window.show();
	}
	
	public void addLoginButtonHandler(EventHandler<ActionEvent> e) {
		this.loginButton.setOnAction(e);
	}
	
	public String getName() {
		return this.name.getText();
	}
	
	public String getPass() {
		return this.pass.getText();
	}
	
	public void close() {
		window.close();
	}
	
	public void addCreateButtonHandler(EventHandler<ActionEvent> e) {
		this.createButton.setOnAction(e);
	}
}
