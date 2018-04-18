package org.BusinessLayer;

import dao.UsersDao;
import javafx.collections.ObservableList;
import pingpong.User;

public class UserBL {
	public int login(String mailu, String p) {
		int x = UsersDao.findByMail(mailu, p);
		return x;
	}

	public int findByMail(String mailu, String p) {
		return UsersDao.findByMail(mailu, p);
	}

	public boolean isAdmin(String mailu) {
		boolean x = UsersDao.isAdmin(mailu);
		return x;
	}

	public void insert(User u) {
		UsersDao.insert(u);
	}

	public void update(String u, String p) {
		UsersDao.updatePass(u, p);
	}

	public void delete(int id) {
		UsersDao.deleteById(id);
	}

	public ObservableList<User> getUsers() {
		return UsersDao.getUsers();
	}
}
