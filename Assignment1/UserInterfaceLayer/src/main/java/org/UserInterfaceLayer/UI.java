package org.UserInterfaceLayer;

import org.BusinessLayer.UserBL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UI extends Application {

	Stage window;

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Ping Pong Tournaments");

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);

		Label nameLabel = new Label("Email:");
		GridPane.setConstraints(nameLabel, 0, 0);

		final TextField mail = new TextField();
		mail.setPromptText("email");
		GridPane.setConstraints(mail, 1, 0);

		Label passLabel = new Label("Password:");
		GridPane.setConstraints(passLabel, 0, 1);

		final PasswordField pass = new PasswordField();
		pass.setPromptText("password");
		GridPane.setConstraints(pass, 1, 1);

		Button loginButton = new Button("Log In");
		Button createButton = new Button("Create Account");
		GridPane.setConstraints(loginButton, 1, 2);
		GridPane.setConstraints(createButton, 1, 3);
		loginButton.setOnAction(e -> {
			UserBL u = new UserBL();
			int id = u.login(mail.getText(), pass.getText());
			if (id != 0) {
				if (u.isAdmin(mail.getText())) {
					window.close();
					LoggedInA.display("Ping Pong Tournaments");
				} else {
					window.close();
					LoggedInP.display("Ping Pong Tournaments", id);

				}
			}
		});

		createButton.setOnAction(e -> {
			CreateA.display();
		});

		grid.getChildren().addAll(nameLabel, mail, passLabel, pass, loginButton, createButton);
		grid.setAlignment(Pos.CENTER);

		Scene scene = new Scene(grid, 300, 200);
		window.setScene(scene);
		window.show();
	}

	public static void main(String[] args) {
		launch(args);
		/*
		 * Tournament t = TournamentsDao.findById(1); if(t==null) { throw new
		 * NoSuchElementException("not found"); }
		 * 
		 * TournamentBL x = new TournamentBL(); List<Tournament> to =
		 * x.getTournaments(); for (Tournament ti: to) { System.out.println(ti); }
		 */

		/*
		 * MatchBL y = new MatchBL(); List<Match> ma = y.getMatches(); for (Match mi :
		 * ma) { System.out.println(mi); }
		 */

		/*
		 * UserBL u = new UserBL(); u.findByMail("lucia_diana1995@yahoo.com", "123");
		 * 
		 * UserBL u2 = new UserBL(); u2.findByMail("lucia_diana1995@yahoocom", "123");
		 * 
		 * UserBL u3 = new UserBL(); u3.findByMail("lucia_diana1995@yahoo.com", "1234");
		 */

		/*
		 * GameBL g = new GameBL(); g.updateScore(6, 1);
		 * System.out.println(g.findById(6));
		 */

		// Tournament t = new Tournament("World Championship", "playing", 0);
		// TournamentBL td = new TournamentBL();
		// td.updateN(2, "eu");
		// td.delete(1);

		// User u = new User("Diana", "lucia_diana1995@yahoo.com", "123");
		// UserBL ud = new UserBL();
		// ud.insert(u);
		// ud.update(1, "234");

		// UserBL u = new UserBL();
		// u.delete(1);
	}

}
