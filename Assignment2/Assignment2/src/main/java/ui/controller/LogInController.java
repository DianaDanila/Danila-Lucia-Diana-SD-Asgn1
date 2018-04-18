package ui.controller;

import datamodel.sd.bll.UserBLL;
import datamodel.sd.dao.UserDao;
import datamodel.sd.dao.factory.DaoFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ui.view.CreateAWindow;
import ui.view.LogInWindow;
import ui.view.LoggedInAWindow;
import ui.view.LoggedInPWindow;

public class LogInController {
	public LogInController(LogInWindow window) {
		window.addLoginButtonHandler(e -> {
			UserDao udao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
			UserBLL u = new UserBLL(udao);
			boolean id = u.login(window.getName(), window.getPass());
			if (id) {
				if (u.isAdmin(window.getName())) {
					window.close();
					new LoggedInAController(new LoggedInAWindow("Ping Pong Tournaments"));
				} else {
					window.close();
					new LoggedInPController(new LoggedInPWindow());
				}
			}
			else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("ERROR");
				alert.setHeaderText(null);
				alert.setContentText("Not valid name or pass!");
				alert.showAndWait();
			}
		});
		
		window.addCreateButtonHandler(e -> {
			new CreateAController(new CreateAWindow());
		});
	}
}
