package gd.web.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import gd.web.util.Enum;
import gd.web.util.Util;

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
	public void getPreviousDate() throws ParseException{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		Date dp = sdf.parse("2017-03-01");
		cal.setTime(dp);
		cal.add(Calendar.DAY_OF_MONTH, -2);
		System.out.println(cal.getTime());
		Date da = sdf.parse("2017-02-27");
		cal.setTime(da);
		cal.add(Calendar.DAY_OF_MONTH, 2);
		System.out.println(cal.getTime());
	}
	@Test
	public void stringToDate() throws ParseException{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		Date dd = sdf.parse("2017-03-09");
		Date dd2 = sdf.parse("2017-03-09");
		Date dd3 = sdf.parse("2017-03-01");
		System.out.println(dd);
		System.out.println(dd.getTime()==dd2.getTime());
		cal.setTime(dd3);
		cal.add(Calendar.DAY_OF_MONTH, -2);
		System.out.println(cal.getTime());
	}
	@Test
	public void compareTwoDate() throws ParseException{
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date od = sdf.parse("2017-03-17 06:36:23");
		Date nd = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(od);
		long odm = cal.getTimeInMillis();
		cal.setTime(nd);
		long ndm = cal.getTimeInMillis();
		
		int days = (int)(ndm-odm)/(1000*24*60*60);
		System.out.println(days);
		
		
	}
	
	
	@Test
	public void testComp(){
		System.out.println("2013-09-21".compareTo("2013-09-19"));//output:1, >0
	}
	@Test
	public void testToArr(){
		String[] arr1 = {"1","2","3","4"};
		Integer[] arr2 = {7,8,9,0,12};
		Double[] arr3 = {1.21,1.35,4.2,4.0001,0.1};
		System.out.println(Util.arrToStr(arr1));
		System.out.println(Util.arrToStr(arr2));
		System.out.println(Util.arrToStr(arr3));
	}
}
