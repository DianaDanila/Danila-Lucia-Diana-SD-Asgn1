package command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
}
