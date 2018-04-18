package ui.controller;

import datamodel.sd.bll.UserBLL;
import datamodel.sd.dao.UserDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.model.User;
import ui.view.CreateAWindow;

public class CreateAController {
	public CreateAController(CreateAWindow a) {
		
		a.addCreateButtonHandler(e -> {
			System.out.println("dvdbg");
			UserDao udao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
			UserBLL g = new UserBLL(udao);
			User u = new User();
			u.setName(a.getName());
			u.setPassword(a.getPass());
			g.insert(u);
			a.close();
		});
	}
}
