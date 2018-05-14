package client;

import java.util.HashSet;

import command.Response;
import command.WriteArticleCommand;
import command.WriteArticleResponse;
import dto.ArticleDTO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WriteWindow {
	public static void display(String title) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Button write = new Button("Write");
		
		Label titleLabel = new Label("Title:");
		GridPane.setConstraints(titleLabel, 0, 0);

		final TextField titlea = new TextField();
		titlea.setPromptText("title");
		GridPane.setConstraints(titlea, 1, 0);
		
		Label authorLabel = new Label("Author:");
		GridPane.setConstraints(authorLabel, 0, 1);

		final TextField author = new TextField();
		author.setPromptText("author");
		GridPane.setConstraints(author, 2, 0);
		
		Label abstractLabel = new Label("Abstract:");
		GridPane.setConstraints(abstractLabel, 0, 2);

		final TextField abstracta = new TextField();
		abstracta.setPromptText("abstract");
		GridPane.setConstraints(abstracta, 3, 0);
		
		Label bodyLabel = new Label("Body:");
		GridPane.setConstraints(bodyLabel, 0, 2);

		final TextField body = new TextField();
		body.setPromptText("body");
		GridPane.setConstraints(body, 3, 0);
		
		Label iLabel = new Label("Image:");
		GridPane.setConstraints(iLabel, 0, 3);

		final TextField i = new TextField();
		i.setPromptText("image");
		GridPane.setConstraints(i, 4, 0);
		
		
		write.setOnAction(e -> {
			System.out.println(i.getText());
			ArticleDTO a = new ArticleDTO(titlea.getText(), abstracta.getText(), author.getText(), body.getText(), new HashSet<String>(), i.getText());
			Client.addMessage(new WriteArticleCommand(a));
			
			Response r = Client.getResponse();
			
			if(r instanceof WriteArticleResponse) {
				window.close();
			}
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(titleLabel, titlea, authorLabel, author, abstractLabel, abstracta, bodyLabel, body, iLabel, i, write);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 500, 500);
		window.setScene(scene);
		window.showAndWait();
	}
}
