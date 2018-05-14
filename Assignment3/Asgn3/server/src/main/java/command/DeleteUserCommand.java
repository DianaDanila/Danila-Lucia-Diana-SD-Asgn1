package command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import bll.UserBLL;
import dao.UserDao;
import model.User;

public class DeleteUserCommand implements Command {
	private String username;
	
	@JsonCreator
	public DeleteUserCommand(@JsonProperty(value = "username") String u) {
		this.username = u;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Response execute() {
		
		UserDao udao = new UserDao();
		UserBLL ubll = new UserBLL(udao);
		User u = ubll.findByUserName(username);
		ubll.delete(u);
		
		DeleteUserResponse r = new DeleteUserResponse();
		
		return r;
	}
}
