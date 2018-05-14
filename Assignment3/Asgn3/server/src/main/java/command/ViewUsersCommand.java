package command;

import java.util.ArrayList;
import java.util.List;

import bll.UserBLL;
import bll.dto.UserDTO;
import dao.UserDao;
import model.User;

public class ViewUsersCommand implements Command {

	
	public Response execute() {

		UserDao dao = new UserDao();
		UserBLL bll = new UserBLL(dao);
		List<User> users = bll.getUsers();

		List<UserDTO> dto = new ArrayList<UserDTO>();
		for (User a : users) {
			dto.add(new UserDTO(a));
		}

		ViewUsersResponse r = new ViewUsersResponse(dto);

		return r;
	}
}
