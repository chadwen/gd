package gd.web.service;

import org.springframework.stereotype.Component;

import gd.web.entity.User;

@Component
public interface UserService {
	
	User getUserByName(String userName);
	
	User getUserById(int id);
	
	void addUser(User user);
	
	void deleteUser(User user);
	
	void updateUser(User user);
}
