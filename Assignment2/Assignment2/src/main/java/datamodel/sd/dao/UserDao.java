package datamodel.sd.dao;

import java.util.List;

import datamodel.sd.model.User;

public interface UserDao extends Dao<User> {

	@Override
	User findById(int id);

	@Override
	void delete(User objectToDelete);

	@Override
	void update(User objectToUpdate);

	@Override
	void insert(User objectToCreate);
	
	User findByUserName(String username);

	List<User> getUsers();
	
}
