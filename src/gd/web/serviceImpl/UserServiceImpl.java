package gd.web.serviceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.UserDAO;
import gd.web.entity.UserEntity;
import gd.web.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserEntity getUserByName(String userName) {
		return userDAO.getUserByName(userName);
	}

	@Override
	public UserEntity getUserById(int id) {
		return userDAO.getUserById(id);
	}

	@Override
	public void addUser(UserEntity userEntity) {
		userDAO.addUser(userEntity);
	}

	@Override
	public void deleteUser(UserEntity userEntity) {
		userDAO.deleteUser(userEntity);
		
	}

	@Override
	public void updateUser(UserEntity userEntity) {
		userDAO.updateUser(userEntity);
		
	}
	
	
}
