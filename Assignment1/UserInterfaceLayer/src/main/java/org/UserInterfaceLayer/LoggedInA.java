package org.UserInterfaceLayer;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import org.BusinessLayer.GameBL;

import javafx.geometry.*;

public class LoggedInA {
	static boolean answer;

    public static void display(String title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        //Label label = new Label();
        //label.setText(message);

        //Create two buttons
        Button createP = new Button("Create Player");
        
        Button deleteP = new Button("Delete Player");
        Button createT = new Button("Create Tournament");
        Button viewT = new Button("View Tournament");
        Button updateT = new Button("Update Tournaments");
        Button deleteT = new Button("Delete Tournament");
        Button viewG = new Button("View Games");
        Button viewU = new Button("View Users");

        //Clicking will set answer and close window
        viewT.setOnAction(e -> {
            ViewT.display(1);
        });
        createT.setOnAction(e -> {
            CreateT.display();
        });
        
        viewG.setOnAction(e -> {
            ViewG.display();
        });
        viewU.setOnAction(e -> {
            ViewU.display();
        });
        /*updateS.setOnAction(e -> {
        	GameBL g = new GameBL();
        	//int idg = g.getCurrentGame(id);
        	g.updateScoreByPlayer(0, 0, id);
            answer = false;
            window.close();
        });*/

        VBox layout = new VBox(10);
        //VBox layout2 = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(viewT, createT, viewG, viewU);
        ///layout1.setAlignment(Pos.BASELINE_LEFT);
        //layout2.getChildren().addAll(viewT, viewM);
        //layout2.setAlignment(Pos.BASELINE_LEFT);
        Scene scene = new Scene(layout, 600, 600);
        window.setScene(scene);
        window.showAndWait();
    }
}
