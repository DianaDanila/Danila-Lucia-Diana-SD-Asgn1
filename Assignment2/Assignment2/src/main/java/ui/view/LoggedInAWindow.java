package ui.view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoggedInAWindow {
	
	private Button createT;
	private Button viewT;
	private Button viewG;
	private Button viewU;
	private Button viewM;

	public LoggedInAWindow(String title) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		createT = new Button("Create Tournament");
		viewT = new Button("View Tournament");
		viewG = new Button("View Games");
		viewU = new Button("View Users");
		viewM = new Button("View Matches");

		/*viewT.setOnAction(e -> {
			ViewT.display(1);
		});
		createT.setOnAction(e -> {
			CreateT.display();
		});

		viewG.setOnAction(e -> {
			ViewG.display(0);
		});
		viewU.setOnAction(e -> {
			ViewU.display();
		});
		viewM.setOnAction(e -> {
			ViewM.display();
		});*/
		VBox layout = new VBox(10);

		layout.getChildren().addAll(viewT, createT, viewG, viewM, viewU);
		Scene scene = new Scene(layout, 600, 600);
		window.setScene(scene);
		window.show();
	}
	
	public void addViewTButtonHandler(EventHandler<ActionEvent> e) {
		this.viewT.setOnAction(e);
	}
	
	public void addCreateTButtonHandler(EventHandler<ActionEvent> e) {
		this.createT.setOnAction(e);
	}
	
	public void addViewGButtonHandler(EventHandler<ActionEvent> e) {
		this.viewG.setOnAction(e);
	}
	
	public void addViewUButtonHandler(EventHandler<ActionEvent> e) {
		this.viewU.setOnAction(e);
	}
	
	public void addViewMButtonHandler(EventHandler<ActionEvent> e) {
		this.viewM.setOnAction(e);
	}
	
}
