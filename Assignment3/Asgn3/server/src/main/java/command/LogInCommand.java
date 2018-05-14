package command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import bll.UserBLL;
import dao.UserDao;

public class LogInCommand implements Command {
	
	private String username;
	private String password;

	@JsonCreator
	public LogInCommand(@JsonProperty(value = "username") String u, @JsonProperty(value = "password") String p) {
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
		boolean check = ubll.login(username, password);
		boolean admin = ubll.isAdmin(username);
		
		LogInResponse r = new LogInResponse(check, admin);
		
		return r;
	}

}
