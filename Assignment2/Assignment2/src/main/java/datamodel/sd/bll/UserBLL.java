package datamodel.sd.bll;

import java.util.List;

import datamodel.sd.dao.UserDao;
import datamodel.sd.model.Tournament;
import datamodel.sd.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserBLL {

	private UserDao udao;

	public UserBLL(UserDao udao) {
		this.udao = udao;
	}

	public boolean login(String name, String pass) {
		User x = udao.findByUserName(name);
		if (x != null) {
			if (x.getPassword().equals(pass)) {
				return true;
			}
		}
		return false;
	}

	public boolean isAdmin(String name) {
		User x = udao.findByUserName(name);
		return x.isAdmin();
	}

	public void insert(User u) {
		udao.insert(u);
	}

	public void update(User u) {
		udao.update(u);
	}

	public void delete(User u) {
		udao.delete(u);
	}

	public List<User> getUsers() {
		return udao.getUsers();
	}

	public void addMoney(User user, int money) {

		int now = user.getAccountbalance();
		user.setAccountbalance(now + money);
		udao.update(user);
	}

	public void withdrawMoney(User user, int money) {

		int now = user.getAccountbalance();
		if (now - money >= 0) {
			user.setAccountbalance(now - money);
			udao.update(user);
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("ERROR");
			alert.setHeaderText(null);
			alert.setContentText("Not enough funds!");
			alert.showAndWait();
		}
	}

	public User findByUsername(String username) {
		return udao.findByUserName(username);
	}

	public void enrol(User u, Tournament t) {
		if (t.getType().equals("paid")) {
			if (u.getAccountbalance() > t.getFee()) {
				this.withdrawMoney(u, t.getFee());
			} else {
				System.out.println("Not enough funds.");
				return;
			}
		}
		u.addTournament(t);
		t.addUser(u);
		udao.update(u);
	}

	public User findById(int id) {
		return udao.findById(id);
	}

}
