package gd.web.test;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;

import gd.web.controller.UserController;
import gd.web.entity.ChartDataEntity;
import gd.web.service.ChartDataService;


public class ChartDataTest {

	@Autowired
	private ChartDataService chartDataService;
	private ApplicationContext applicationContext = new FileSystemXmlApplicationContext("WebRoot/WEB-INF/web.xml");
	@Test
	public void addChartData(){
		ChartDataEntity cde = new ChartDataEntity();
		cde.setStaId(0);
		//chartDataService.addChartData(cde);
	}
	@Test
	public void newTestMethod(){
//		UserController uc = new UserController();
//		uc = (UserController)applicationContext.getBean("UserController");
//		System.out.println(uc.addUser());
	}
}
