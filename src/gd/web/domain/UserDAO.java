package gd.web.domain;

import org.springframework.transaction.annotation.Transactional;

import gd.web.entity.User;

public interface UserDAO {
	@Transactional
	User getUserByName(String userName);
	@Transactional
	User getUserById(int id);
	@Transactional
	void addUser(User user);
	@Transactional
	void deleteUser(User user);
	@Transactional
	void updateUser(User user);
	
}
