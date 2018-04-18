package ui.controller;

import datamodel.sd.bll.UserBLL;
import datamodel.sd.dao.UserDao;
import datamodel.sd.dao.factory.DaoFactory;
import datamodel.sd.model.User;
import ui.view.ViewU;

public class ViewUController {

	public ViewUController(ViewU window) {
		window.deleteButtonHandler(e -> {
			UserDao udao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
			UserBLL u = new UserBLL(udao);
			User user = u.findById(window.getId());
			u.delete(user);
			window.close();
		});
		window.addButtonHandler(e -> {
			UserDao udao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
			UserBLL u = new UserBLL(udao);
			User user = u.findById(window.getId());
			int money = window.getMoney();
			u.addMoney(user, money);;
			window.close();
		});
		
		window.withButtonHandler(e -> {
			UserDao udao = DaoFactory.getInstance(DaoFactory.Type.HIBERNATE).getUserDao();
			UserBLL u = new UserBLL(udao);
			User user = u.findById(window.getId());
			int money = window.getMoney();
			u.withdrawMoney(user, money);
			window.close();
		});
	}
}
