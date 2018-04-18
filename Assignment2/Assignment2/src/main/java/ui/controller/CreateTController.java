package ui.controller;

import datamodel.sd.bll.TournamentBLL;
import datamodel.sd.dao.TournamentDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.model.Tournament;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ui.view.CreateTWindow;

public class CreateTController {

	public CreateTController(CreateTWindow window) {
	window.addCreateButtonHandler(e -> {
		TournamentDao tdao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
		TournamentBLL tb = new TournamentBLL(tdao);
		Tournament t = new Tournament();
		t.setName(window.getName());
		t.setIdwinner(0);
		t.setStatus(window.getStat());
		t.setType(window.getType());
		if(window.getType().equals("free") && window.getFee()>0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.setContentText("Free means no money!");
			alert.showAndWait();
		}
		t.setFee(window.getFee());
		t.setWinmoney(0);
		tb.insert(t);
		window.close();
	});
	}

}
