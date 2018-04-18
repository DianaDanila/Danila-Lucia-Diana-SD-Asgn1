package ui.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoggedInPWindow {
	Stage window ;
	private Button updateP;
	private Button viewT;
	private Button viewG;
	private Button enrol;
	private Button viewM;

	public LoggedInPWindow() {
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);
		viewT = new Button("View Tournaments");
		viewM = new Button("View Matches");
		updateP = new Button("Update Player");
		viewG = new Button("View Games");
		enrol = new Button("Enrol");


		VBox layout = new VBox(10);

		layout.getChildren().addAll(viewT, viewM, updateP, viewG,enrol);
		layout.setAlignment(Pos.BASELINE_LEFT);
		Scene scene = new Scene(layout, 600, 600);
		window.setScene(scene);
		window.show();
	}
	
	public void addViewTButtonHandler(EventHandler<ActionEvent> e) {
		this.viewT.setOnAction(e);
	}
	
	public void addViewGButtonHandler(EventHandler<ActionEvent> e) {
		this.viewG.setOnAction(e);
	}
	
	public void addViewMButtonHandler(EventHandler<ActionEvent> e) {
		this.viewM.setOnAction(e);
	}
	
	public void updateButtonHandler(EventHandler<ActionEvent> e) {
		this.updateP.setOnAction(e);
	}
	
	public void enrolButtonHandler(EventHandler<ActionEvent> e) {
		this.enrol.setOnAction(e);
	}
}
