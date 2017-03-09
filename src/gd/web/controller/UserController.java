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
import gd.web.entity.UserEntity;
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
	private UserEntity userEntity;
	
	
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
		userEntity = userService.getUserByName("admin");
		if(userEntity == null){
			userEntity.setUserName("admin");
			userEntity.setPassword("admin");
			userEntity.setPriv("admin");
			userEntity.setPhone("1021");
			userEntity.setStaId(1);
			userService.addUser(userEntity);
		}
		return "jsp/map";
	}
	//search user
	@RequestMapping(value="/search",method = RequestMethod.GET)
	public String searchUser(String userName){
		UserEntity userEntity = userService.getUserByName(userName);
		System.out.println("\n\nnow run the system!");
		if(userEntity!=null){
			System.out.println(userEntity.getUserName());
			System.out.println(userEntity.getPassword());
		}else{
			System.out.println("no result");			
		}
		
		return "jsp/hello";
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String addUser(){
		logger.info("go into addUser");
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName("admin");
		userEntity.setPassword("admin");
		userEntity.setPriv("admin");
		userEntity.setPhone("1021");
		userEntity.setStaId(1);
		userService.addUser(userEntity);
		//searchUser("admin");
		
		ChartDataEntity cde = new ChartDataEntity();
		cde.setStaId(0);
		//chartDataService.addChartData(cde);
		
		logger.info("end of addUser");
		
		
		return "jsp/hello";
	}
	//

	
	
}
