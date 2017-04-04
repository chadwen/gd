package gd.web.serviceImpl.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.annotation.Autowired;

import gd.web.entity.UserEntity;
import gd.web.service.UserService;

public class SessionListener implements HttpSessionListener{

	@Autowired
	private UserService userService;
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		System.out.println("\nstart of the listener!!!");
		HttpSession session = event.getSession();

		
		ServletContext context = session.getServletContext();
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
		System.out.println("end of the listener!!!\n");
	}

}
