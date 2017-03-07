package gd.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import gd.web.entity.ChartDataEntity;
import gd.web.entity.StationEntity;
import gd.web.entity.User;
import gd.web.service.ChartDataService;
import gd.web.service.StationService;
import gd.web.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private StationService stationService;
	
	//@Autowired
	//private ChartDataService chartDataService;
	
	@Autowired
	private User user;
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	// what' that????????????????
	//public @ResponseBody List<Goods> testAjax(@RequestParam String types){}
	@RequestMapping(value="/test",method = RequestMethod.GET)
	public void testMthod(){
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
	@ResponseBody
	@RequestMapping(value="test",method=RequestMethod.POST)
	public List<StationEntity> testMethod(){

		/*StationEntity se1 = new StationEntity();
		se1.setAddr("addr1");
		se1.setBrief("brief1");
		se1.setId(100);
		se1.setImgPath("http://pic34.photophoto.cn/20150330/0007019952833279_b.jpg");
		se1.setStaFullName("title1");
		se1.setPosx("106.435303000");
		se1.setPosy("29.827845000");
		StationEntity se2 = new StationEntity();
		se2.setAddr("addr2");
		se2.setBrief("brief2");
		se2.setId(101);
		se2.setImgPath("http://pic34.photophoto.cn/20150330/0007019952833279_b.jpg");
		se2.setStaFullName("title2");
		se2.setPosx("106.432177");
		se2.setPosy("29.824649");
		List<StationEntity> list =  new ArrayList<StationEntity>();
		list.add(se1);
		list.add(se2);*/
		
		return stationService.getAllStationEntity();
	}
	
	
}
