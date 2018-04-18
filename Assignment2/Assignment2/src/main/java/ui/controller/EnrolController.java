package ui.controller;

import datamodel.sd.bll.TournamentBLL;
import datamodel.sd.bll.UserBLL;
import datamodel.sd.dao.TournamentDao;
import datamodel.sd.dao.UserDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.model.Tournament;
import datamodel.sd.model.User;
import ui.view.EnrolView;

public class EnrolController {
	public EnrolController(EnrolView window) {
		window.updateButtonHandler(e -> {
			UserDao udao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
			UserBLL u = new UserBLL(udao);
			User user = u.findById(window.getUser());
			
			
			TournamentDao tdao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getTournamentDao();
			TournamentBLL t = new TournamentBLL(tdao);
			Tournament tu = t.findById(window.getT());
			System.out.println(user.getName()+tu.getName());
			u.enrol(user,tu);
			window.close();
		});
	}
}
