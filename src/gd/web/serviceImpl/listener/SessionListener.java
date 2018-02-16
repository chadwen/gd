package gd.web.serviceImpl.listener;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;

import gd.web.entity.UserEntity;
import gd.web.service.UserService;
import gd.web.util.*;

public class SessionListener implements HttpSessionListener{

	@Autowired
	private UserService userService;
	
	//@Autowired
	//private HttpServletRequest request;
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\t");
		System.out.println("\nstart of the listener--sessionCreated!!!");
		HttpSession session = event.getSession();

		ServletContext context = session.getServletContext();
		Map<Integer,Integer> connectedMap = (Map<Integer, Integer>) context.getAttribute("connected_Map");
		if(connectedMap == null){
			connectedMap = new HashMap<Integer,Integer>();
			connectedMap.put(0, 0);
			context.setAttribute("connected_Map", connectedMap);
		}
		if(connectedMap.containsKey(0)){
			//int crrrentTotal = connectedMap.get(0);
			connectedMap.put(0, connectedMap.get(0)+1);
			//connectedMap.
		}
		System.out.println("connected num:"+connectedMap.get(0));
		System.out.println("\nend of the listener--sessionCreated!!!");
		
		String logPath = "D:\\tomcat_log\\gd\\listeningLog.txt";
		File f = new File(logPath);
		sb.append("session created!");
		sb.append("\r\n");
		FileIO.appendWriteFileWithString(f,sb.toString());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("\nstart of the listener--sessionDestroyed!!!");
		HttpSession session = event.getSession();
		ServletContext context = session.getServletContext();
		

		Map<Integer,Integer> connectedMap = (Map<Integer, Integer>) context.getAttribute("connected_Map");
		if(connectedMap == null){
			return ;
			/*connectedMap = new HashMap<Integer,Integer>();
			connectedMap.put(0, 0);
			context.setAttribute("connected_Map", connectedMap);*/
		}
		connectedMap.put(0, connectedMap.get(0)-1);
		
		Map<Integer,String> userMap = (Map<Integer, String>) context.getAttribute("user_map");
		if(userMap==null){
			System.out.println("the userMap is null");
			System.out.println("\nstart of the listener!!!");
			return;
		}
		if(userMap.containsKey(session.getAttribute("userId"))){
			System.out.println("contain special userId and will remove it");
			userMap.remove(session.getAttribute("userId"));
		}
		System.out.println("create time is  : " + new Date(session.getCreationTime()));
		System.out.println("now : "+new Date());
		System.out.println(session.getId());
		
/*		System.out.println("\nstart of the listener!!!");
		System.out.println("user id is : " + (Integer)session.getAttribute("userId") + " user name is : " + (String)session.getAttribute("userName"));
		System.out.println(session.getId());
		System.out.println("create time is  : " + new Date(session.getCreationTime()));
		System.out.println("now : "+new Date());
		System.out.println("last access time to now(minutes) : " + (Calendar.getInstance().getTimeInMillis() - session.getLastAccessedTime())/(1000*60));

		System.out.println("end of the listener!!!\n");*/

		
		/*if(session.getAttribute("userId") != null && userMap.containsKey(session.getAttribute("userId"))){
			UserEntity userEntity = userService.getUserById((Integer)session.getAttribute("userId"));
			userEntity.setModifyTime(new Date());
			userService.updateUser(userEntity);
			userMap.remove(session.getAttribute("userId"));
		}*/
		System.out.println("end of the listener--sessionDestroyed!!!\n");
	}

}
