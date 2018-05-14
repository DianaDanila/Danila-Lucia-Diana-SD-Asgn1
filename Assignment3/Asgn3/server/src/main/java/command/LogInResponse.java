package command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LogInResponse implements Response {

	public boolean checkLogIn;
	public boolean isAdmin;

	@JsonCreator
	public LogInResponse(@JsonProperty(value = "ckeckLogIn") boolean c, @JsonProperty(value = "isAdmin") boolean a) {
		this.checkLogIn = c;
		this.isAdmin = a;
	}

	public boolean isCheckLogIn() {
		return checkLogIn;
	}

	public void setCheckLogIn(boolean checkLogIn) {
		this.checkLogIn = checkLogIn;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
