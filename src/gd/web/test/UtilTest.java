package gd.web.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import gd.web.util.Enum;

public class UtilTest {

	@Test
	public void testEnum(){
		String admin = Enum.ADMINISTRATOR.toString();
		System.out.println(admin);
	}
	
	@Test
	public void testCal(){

		Calendar cal = Calendar.getInstance();
		System.out.println(cal.getTime().toString());
		System.out.println(new Date());
		System.out.println(cal.get(Calendar.HOUR_OF_DAY));
	}
	@Test
	public void testComp(){
		System.out.println("2013-09-21".compareTo("2013-09-19"));
	}
}
