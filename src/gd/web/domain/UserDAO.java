package gd.web.domain;

import gd.web.entity.User;

public interface UserDAO {

	User getUserByName(String userName);
	
	User getUserById(int id);

	void addUser(User user);

	void deleteUser(User user);
	
	void updateUser(User user);
	
}
