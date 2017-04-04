package gd.web.serviceImpl;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gd.web.domain.UserDAO;
import gd.web.entity.InStreamEntity;
import gd.web.entity.OutStreamEntity;
import gd.web.entity.UserEntity;
import gd.web.service.InStreamService;
import gd.web.service.OutStreamService;
import gd.web.service.UserService;
import gd.web.util.Util;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private InStreamService inStreamService;

	@Autowired
	private OutStreamService outStreamService;
	
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

	@Override
	public UserEntity login(UserEntity userEntity) {
		// TODO Auto-generated method stub
		return userDAO.login(userEntity);
	}

	@Override
	public void initStreamTable(int staId) {
		// TODO Auto-generated method stub
		if(staId > 0){
			InStreamEntity inStreamEntity = inStreamService.getActiveRecord(staId);
			if(inStreamEntity == null || Util.getFormatDate().compareTo(inStreamEntity.getCurrDate())>0){
				inStreamService.createRecord(staId);
			}
			OutStreamEntity outStreamEntity = outStreamService.getActiveRecord(staId);
			if(outStreamEntity==null || Util.getFormatDate().compareTo(outStreamEntity.getCurrDate())>0){
				outStreamService.createRecord(staId);
			}
		}
	}
/*	public void updateUserMap(HttpSession session){

		ServletContext context = session.getServletContext(); 
		Map<Integer,String> userMap ;
		userMap = (Map<Integer, String>) context.getAttribute("user_map");
		if(userMap==null){
			userMap = new HashMap<Integer,String>();
			context.setAttribute("user_map", userMap);
		}
		if(userMap.containsKey(userEntity.getId())){
			return "had_loaded";
		}
		userMap.put(userEntity.getId(), userEntity.getUserName());
	}*/
	
	
}
