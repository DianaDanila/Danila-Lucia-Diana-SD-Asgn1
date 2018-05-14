package client;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import dto.ArticleDTO;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewOneArticle {

	public static void display(ArticleDTO a) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);
		
		final TextArea titlea = new TextArea(a.getTitle());
		titlea.setFont(Font.font ("Verdana", 15));
		titlea.setWrapText(true);
		titlea.setMaxHeight(80);
		//GridPane.setConstraints(titlea, 0, 0);
		
		
		final TextField author = new TextField(a.getAuthor());
		author.setFont(Font.font ("Verdana", 12));
		//GridPane.setConstraints(author, 1, 1);
		
		final TextArea abstracta = new TextArea(a.getAbstracta());
		abstracta.setFont(Font.font ("Verdana", 10));
		abstracta.setWrapText(true);
		abstracta.setMaxHeight(80);
		//GridPane.setConstraints(abstracta, 2, 2);
		
		final TextArea body = new TextArea(a.getBody());
		body.setFont(Font.font ("Verdana", 10));
		body.setWrapText(true);
		body.setMaxHeight(200);
		//GridPane.setConstraints(body, 3, 3);
		
		final TextArea related = new TextArea("RELATED:\n" + a.getRelated().toString());
		related.setFont(Font.font ("Verdana", 10));
		related.setWrapText(true);
		related.setMaxHeight(100);
		//GridPane.setConstraints(related, 4, 4);
		
		@SuppressWarnings("unused")
		BufferedImage img = null;
		VBox layout = new VBox(10);
		layout.setAlignment(Pos.TOP_CENTER);

		final ImageView selectedImage = new ImageView();   
		Image image1;
		try {
			image1 = new Image(new FileInputStream(a.getImage()));
			selectedImage.setImage(image1);
			layout.getChildren().addAll(selectedImage);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		layout.getChildren().addAll(titlea, author, abstracta, body, related);
		Scene scene = new Scene(layout, 300, 500);
		window.setScene(scene);
		window.showAndWait();
	}
	
	
	
}
