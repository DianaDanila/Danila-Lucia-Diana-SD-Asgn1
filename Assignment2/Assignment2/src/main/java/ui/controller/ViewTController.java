package ui.controller;

import datamodel.sd.bll.TournamentBLL;
import datamodel.sd.dao.TournamentDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.model.Tournament;
import ui.view.ViewByTWindow;
import ui.view.ViewT;

public class ViewTController {

	public ViewTController(ViewT window) {
		TournamentDao tdao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
		TournamentBLL t = new TournamentBLL(tdao);

		window.updateButtonHandler(e -> {
			Tournament x = new Tournament();
			x.setId(window.getId());
			
			x.setName(window.getName());
			x = t.findById(window.getId());
			t.update(x);
			window.close();
	});
	
	window.viewByButtonHandler(e -> {
		new ViewByTWindow(window.getName());
		window.close();
	});

	window.deleteButtonHandler(e -> {
		Tournament x = new Tournament();
		x.setId(window.getId());
		t.delete(x);
		window.close();
	});
	}
	
}
