package gd.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import gd.web.entity.ChartDataEntity;
import gd.web.entity.ParkEntity;
import gd.web.entity.StationEntity;
import gd.web.entity.User;
import gd.web.service.ChartDataService;
import gd.web.service.ParkService;
import gd.web.service.StationService;
import gd.web.service.UserService;
import gd.web.util.Enum;
import io.goeasy.GoEasy;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private UserService userService;
	
	//@Autowired
	//private ChartDataService chartDataService;
	
	@Autowired
	private User user;
	
	
	// what' that????????????????
	//@RequestMapping(value="detail/{id}",method=RequestMethod.GET)
	//public String detail(@PathVariable int id,Model model){
	//public @ResponseBody List<Goods> testAjax(@RequestParam String types){}
	@RequestMapping(value="/test",method = RequestMethod.GET)
	public void testMthod(){
		
		GoEasy goEasy = new GoEasy("bf8b21fc-dbde-4d1f-9fee-bd1f39641b73");
		//String msgs[] = {"msg1","msg2","msg3","msg4"};
		goEasy.publish("demo_channel", "Hello world!");
		
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));
	}
	@RequestMapping(value="/entry",method = RequestMethod.GET)
	public String entry(){
		user = userService.getUserByName("admin");
		if(user == null){
			user.setUserName("admin");
			user.setPassword("admin");
			user.setPriv("admin");
			user.setPhone("1021");
			user.setStaId(1);
			userService.addUser(user);
		}
		return "jsp/map";
	}
	//search user
	@RequestMapping(value="/search",method = RequestMethod.GET)
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
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String addUser(){
		logger.info("go into addUser");
		User u1 = new User();
		u1.setUserName("admin");
		u1.setPassword("admin");
		u1.setPriv("admin");
		u1.setPhone("1021");
		u1.setStaId(1);
		userService.addUser(u1);
		//searchUser("admin");
		
		ChartDataEntity cde = new ChartDataEntity();
		cde.setStaId(0);
		//chartDataService.addChartData(cde);
		
		logger.info("end of addUser");
		
		
		return "jsp/hello";
	}
	//

	
	
}
