package bll;

import java.util.List;

import dao.UserDao;
import model.User;

public class UserBLL {

	private UserDao udao;

	public UserBLL(UserDao udao) {
		this.udao = udao;
	}

	public boolean login(String name, String pass) {
		User user = udao.findByUserName(name);
		if (user != null) {
			if (user.getPassword().equals(pass)) {
				return true;
			}
		}
		return false;
	}

	public boolean isAdmin(String name) {
		User x = udao.findByUserName(name);
		if (x != null) {
			return x.isAdmin();
		}
		return false;
	}

	public User findByUserName(String name) {
		return udao.findByUserName(name);
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
	
	public User findById(int id) {
		return udao.findById(id);
	}
}
