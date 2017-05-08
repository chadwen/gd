package gd.web.domain;

import org.springframework.transaction.annotation.Transactional;

import gd.web.entity.UserEntity;

public interface UserDAO {
	@Transactional
	UserEntity getUserByName(String userName);
	
	@Transactional
	UserEntity getUserById(int id);
	
	@Transactional
	void addUser(UserEntity userEntity);
	
	@Transactional
	void deleteUser(UserEntity userEntity);
	
	@Transactional
	void updateUser(UserEntity userEntity);

	@Transactional
	UserEntity login(UserEntity userEntity);

	@Transactional
	UserEntity getUserByStaId(int id);

	@Transactional
	void deleteUserById(int id);
	
}
