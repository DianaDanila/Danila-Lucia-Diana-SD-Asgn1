package command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import bll.UserBLL;
import dao.UserDao;
import model.User;

public class CreateUserCommand implements Command {
	private String username;
	private String password;
	
	@JsonCreator
	public CreateUserCommand(@JsonProperty(value = "username") String u, @JsonProperty(value = "password") String p) {
		this.username = u;
		this.password = p;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Response execute() {
		
		UserDao udao = new UserDao();
		UserBLL ubll = new UserBLL(udao);
		User u = new User();
		u.setName(username);
		u.setPassword(password);
		u.setAdmin(false);
		u.setArticles(null);
		ubll.insert(u);
		
		CreateUserResponse r = new CreateUserResponse();
		
		return r;
	}
}
