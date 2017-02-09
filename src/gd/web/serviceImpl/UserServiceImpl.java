package gd.web.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.UserDAO;
import gd.web.entity.User;
import gd.web.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User getUserByName(String userName) {
		return userDAO.getUserByName(userName);
	}

	@Override
	public User getUserById(int id) {
		return userDAO.getUserById(id);
	}

	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
	}

	@Override
	public void deleteUser(User user) {
		userDAO.deleteUser(user);
		
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
		
	}
	
	
}
