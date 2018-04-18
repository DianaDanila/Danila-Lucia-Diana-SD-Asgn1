package ui.controller;

import datamodel.sd.bll.UserBLL;
import datamodel.sd.dao.UserDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.model.User;
import ui.view.UpdatePlayerWindow;

public class UpdatePlayerController {

	public UpdatePlayerController(UpdatePlayerWindow window) {
		window.updateButtonHandler(e -> {
			UserDao udao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
			UserBLL u = new UserBLL(udao);
			User user = u.findByUsername(window.getName());
			user.setPassword(window.getPass());
			u.update(user);
			window.close();
		});
	}
}
