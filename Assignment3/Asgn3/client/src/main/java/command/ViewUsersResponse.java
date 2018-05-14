package command;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import dto.UserDTO;

public class ViewUsersResponse implements Response{
	private List<UserDTO> users;

	@JsonCreator
	public ViewUsersResponse(@JsonProperty(value = "users") List<UserDTO> users) {
		this.users = users;
	}

	public List<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDTO> users) {
		this.users = users;
	}
}
