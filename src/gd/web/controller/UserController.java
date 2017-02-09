package gd.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gd.web.entity.User;
import gd.web.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private User user;
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	//search user
	@RequestMapping(value="/user",method = RequestMethod.GET)
	public String searchUser(String userName){
		User user = userService.getUserByName(userName);
		System.out.println("\n\nnow run the system!");
		if(user!=null){
			System.out.println(user.getUserName());
			System.out.println(user.getPassword());
		}else{
			System.out.println("no result");			
		}
		
		return "jsp/hello";
	}
	
	@RequestMapping(value="addU",method=RequestMethod.GET)
	public String addUser(){
		logger.info("go into addUser");
		User u1 = new User();
		u1.setUserName("admin");
		u1.setPassword("admin");
		u1.setPriv(1);
		//userService.addUser(u1);
		searchUser("admin");
		logger.info("end of addUser");
		return "jsp/hello";
	}
	
	
}
