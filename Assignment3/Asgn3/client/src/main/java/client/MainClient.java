package client;

import java.io.IOException;
import java.net.Socket;

import com.sun.javafx.scene.control.skin.LabeledText;

import command.Response;
import command.ViewArticlesCommand;
import command.ViewArticlesResponse;
import dto.ArticleDTO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class MainClient extends Application {

	Stage window;

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("NEWS");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		Button loginButton = new Button("Log In");
		loginButton.setMinSize(100, 20);
		GridPane.setConstraints(loginButton, 1, 0);
		loginButton.setOnAction(e -> {
			window.close();
			LogInWindow.display("LogIn Window");
		});

		grid.getChildren().addAll(loginButton);
		grid.setHgap(7);
		
		
		Client.addMessage(new ViewArticlesCommand());
		
		Response r = Client.getResponse();
		
		if(r instanceof ViewArticlesResponse) {

			ListView<ArticleDTO> l = new ListView<ArticleDTO>();
			l.setItems(FXCollections.observableArrayList(((ViewArticlesResponse) r).getArticles()));
			l.setMinWidth(200);
			
			l.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					if(event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2 //&&
					          // (event.getTarget() instanceof LabeledText || ((GridPane) event.getTarget()).getChildren().size() > 0)) {
								) {
					           //window.close();
					           ArticleDTO currentItemSelected = l.getSelectionModel()
                                       .getSelectedItem();
					           ViewOneArticle.display(currentItemSelected);
					}
				}
			});
			
			grid.getChildren().addAll(l);
		}

		
		//grid.setAlignment(Pos.CENTER);

		Scene scene = new Scene(grid, 350, 400);
		window.setScene(scene);
		window.show();
	}

	public static void main(String[] args) {
		Socket s;
		try {
			s = new Socket("localhost", 9090);
			Client c = new Client(s);
			c.start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		launch(args);

	}
}
