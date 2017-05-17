package gd.web.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import gd.web.entity.ChartDataEntity;
import gd.web.entity.UserEntity;
import gd.web.entity.viewModel.UserInfo;
import gd.web.service.StationService;
import gd.web.service.UserService;
import gd.web.util.Enum;
import gd.web.util.Util;
import io.goeasy.GoEasy;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StationService stationService;
	
	//@Autowired
	//private ChartDataService chartDataService;
	
	
	@RequestMapping(value="/get/session",method = RequestMethod.GET)
	public String getSession(Model model,HttpSession session){
		
		UserInfo userInfo = new UserInfo();
		userInfo.setPriv((String)session.getAttribute("priv"));
		userInfo.setUserId((Integer)session.getAttribute("userMap"));
		userInfo.setUserName((String)session.getAttribute("xde"));
		userInfo.setStaId((Integer)session.getAttribute("xde"));
		
		System.out.println(userInfo.getPriv());
		System.out.println(userInfo.getUserName());
		System.out.println(userInfo.getUserId());
		System.out.println(userInfo.getStaId());
		
		ServletContext context = session.getServletContext();
		Map<Integer,String> userMap ;
		userMap = (Map<Integer, String>) context.getAttribute("user_map");

		if(userMap==null){
			userMap = new HashMap<Integer,String>();
			context.setAttribute("user_map", userMap);
		}
		System.out.println("\n\nhere is the userId");
		for(int item : userMap.keySet()){
			System.out.println(item+":"+userMap.get(item));
			
		}
		userMap.keySet();
		model.addAttribute("userMap", userMap);
		return "jsp/getSession";
	}
	// what's that????????????????
	//@RequestMapping(value="detail/{id}",method=RequestMethod.GET)
	//public String detail(@PathVariable int id,Model model){
	//public @ResponseBody List<Goods> testAjax(@RequestParam String types){}
	@RequestMapping(value="/test/{userName}/{id}",method = RequestMethod.POST)
	public void testMthod(@PathVariable String userName,@PathVariable int id,HttpSession session){
		if(session==null){
			return;
		}
		System.out.println(id);
		System.out.println(userName);
		System.out.println(session.getId());
		System.out.println(session.getCreationTime());
		Date date= new Date(session.getCreationTime());
		System.out.println(date);
		System.out.println(session.getAttribute("userName"));
		System.out.println(session.getAttribute("userId"));
		System.out.println(session.getAttribute("priv"));
		System.out.println(session.getAttribute("staId"));
		
		
		/*HttpSession session = new HttpSession();
		System.out.println(session==null);
		System.out.println(session.getId());
		//test single point load
		//HttpSession session =request.getSession();
		ServletContext context = session.getServletContext(); 
		//ApplicationContext acx = WebApplicationContextUtils.getWebApplicationContext(context);
		Map<Integer,String> userMap ;
		userMap = (Map<Integer, String>) context.getAttribute("user_map");
		if(userMap==null){
			userMap = new HashMap<Integer,String>();
			context.setAttribute("user_map", userMap);
		}
		userMap.put(id, userName);
		String usrName = userMap.get(2);
		if(usrName!=null){
			userMap.remove(2);
		}
		for(int item : userMap.keySet()){
			System.out.println(item);
		}*/
		/*//test goEasy
		GoEasy goEasy = new GoEasy("bf8b21fc-dbde-4d1f-9fee-bd1f39641b73");
		//String msgs[] = {"msg1","msg2","msg3","msg4"};//error
		goEasy.publish("demo_channel", "Hello world!");
		
		//test Calendar
		Calendar cal = Calendar.getInstance();
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));*/
	}
	@RequestMapping(value="/entry",method = RequestMethod.GET)
	public String entry(){
		UserEntity userEntity = userService.getUserByName("admin");
		if(userEntity == null){
			userEntity = new UserEntity();
			userEntity.setUserName("admin");
			userEntity.setPassword("admin");
			userEntity.setPriv(Enum.ADMINISTRATOR.toString());
			userEntity.setPhone("1021");
			userEntity.setStaId(0);
			userService.addUser(userEntity);
		}
		return "jsp/map";
	}
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String loginGet(){
		return "jsp/login";
	}
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public @ResponseBody String loginPost(UserEntity userEntity,HttpSession session){
		
		userEntity = userService.login(userEntity);
		if(userEntity==null){
			// no user
			return "mismatch";
		}
		
		ServletContext context = session.getServletContext(); 
		Map<Integer,String> userMap ;
		userMap = (Map<Integer, String>) context.getAttribute("user_map");
		if(userMap==null){
			userMap = new HashMap<Integer,String>();
			context.setAttribute("user_map", userMap);
		}
		if(userMap.containsKey(userEntity.getId())){
			return "hadLoaded";
		}
		userMap.put(userEntity.getId(), userEntity.getUserName());
		
		
		session.setAttribute("userName", userEntity.getUserName());
		session.setAttribute("userId", userEntity.getId());
		session.setAttribute("priv", userEntity.getPriv());
		session.setAttribute("staId", userEntity.getStaId());
		for(int item : userMap.keySet()){
			System.out.println(item);
		}
		
		
		userService.initStreamTable(userEntity.getStaId());
		return "success";
	}
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public String logOut(HttpSession session){
		ServletContext context = session.getServletContext(); 
		Map<Integer,String> userMap = (Map<Integer, String>) context.getAttribute("user_map");
		Object id = session.getAttribute("userId");
		session.setAttribute("priv",Enum.NULLPRIV.toString());
		session.removeAttribute("userName");
		session.removeAttribute("userId");
		session.removeAttribute("staId");
		if(userMap==null){
			return "redirect:/";
		}
		userMap.remove(id);
		
		//test
		userMap.get(id);//session id
		return "redirect:/";
	}
	@RequestMapping(value="/confirmPwd",method = RequestMethod.POST)
	public @ResponseBody String confirmPwd(int userId, String oriPwd){
		
		//UserEntity ue = userService.getUserById(Integer.parseInt(userId));
		UserEntity ue = userService.getUserById(userId);
		if(ue==null){
			return "nouser";
		}
		String pwd = ue.getPassword();
		System.out.println(pwd);
		boolean b1 = ue.getPassword().equals(oriPwd);
		boolean b2 = pwd.equals(oriPwd);
		if(ue.getPassword().equals(oriPwd)){
			return "success";
		}
		
		return "mismatch";
	}
	@RequestMapping(value="/modifyPwd",method = RequestMethod.POST)
	public @ResponseBody String modifyPwd(int userId, String newPwd, HttpSession session){
		UserEntity ue = userService.getUserById(userId);
		if(ue==null){
			return "nouser";
		}
		ue.setPassword(newPwd);
		userService.updateUser(ue);ServletContext context = session.getServletContext(); 
		Map<Integer,String> userMap = (Map<Integer, String>) context.getAttribute("user_map");
		if(userMap==null || userMap.size() == 0){
			return "nouser";
		}if(userMap.containsKey(userId)){
			userMap.remove(userId);
		}
		return "success";
	}
	
	@RequestMapping(value="/clearStatus/{userId}",method = RequestMethod.POST)
	public void clearStatus(@PathVariable int userId, HttpSession session){
		ServletContext context = session.getServletContext(); 
		Map<Integer,String> userMap = (Map<Integer, String>) context.getAttribute("user_map");
		if(userMap==null || userMap.size() == 0){
			return ;
		}
		if(userMap.containsKey(userId)){
			userMap.remove(userId);
		}
	}
	@RequestMapping(value="/resetpwd/{userId}",method = RequestMethod.POST)
	public void resetpwd(@PathVariable int userId, HttpSession session){
		UserEntity ue = userService.getUserById(userId);
		if(ue == null){
			return;
		}
		ue.setPassword("123456");
		userService.updateUser(ue);
	}
	
	@RequestMapping(value="/getUserInfo",method = RequestMethod.POST)
	public @ResponseBody UserInfo getPriv(HttpSession session){
		/*if(session.getAttribute("priv")==null){
			return null;
		}*/
		UserInfo userInfo = new UserInfo();
		if((Integer)session.getAttribute("staId") != null && (Integer)session.getAttribute("staId")!= 0){
			ServletContext context = session.getServletContext(); 
			Map<Integer,String> userMap = (Map<Integer, String>) context.getAttribute("user_map");
			if(userMap == null){
				return userInfo;
			}
			if(userMap.containsKey((Integer)session.getAttribute("userId"))){
				userInfo.setPriv((String)session.getAttribute("priv"));
				userInfo.setUserId((Integer)session.getAttribute("userId"));
				userInfo.setUserName((String)session.getAttribute("userName"));
				userInfo.setStaId((Integer)session.getAttribute("staId"));
				userInfo.setStation(stationService.getStationById(userInfo.getStaId()));
				return userInfo;
			}else{
				session.setAttribute("priv", Enum.NULLPRIV.toString());
				session.removeAttribute("userId");
				session.removeAttribute("userName");
				session.removeAttribute("staId");
			}
		}
		if((Integer)session.getAttribute("staId") == null){
			session.setAttribute("priv", Enum.NULLPRIV.toString());
		}
		userInfo.setPriv((String)session.getAttribute("priv"));
		userInfo.setUserId((Integer)session.getAttribute("userId"));
		userInfo.setUserName((String)session.getAttribute("userName"));
		userInfo.setStaId((Integer)session.getAttribute("staId"));
		/*if(userInfo.getStaId() != null && userInfo.getStaId() != 0){
			userInfo.setStation(stationService.getStationById(userInfo.getStaId()));
		}*/
		return userInfo;
	}
	
	//uesless
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
