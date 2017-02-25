package gd.web.service;

import gd.web.entity.User;


public interface UserService {
	
	User getUserByName(String userName);
	
	User getUserById(int id);
	
	void addUser(User user);
	
	void deleteUser(User user);
	
	void updateUser(User user);
}
