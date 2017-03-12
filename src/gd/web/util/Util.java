package gd.web.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Util {
	private static Calendar cal = Calendar.getInstance();
	
	public static String getFormatDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
		//return sdf.format(cal.getTime());
	}
	public static String getFormatDateAll(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
		//return sdf.format(cal.getTime());
	}
	public static int getCurrHour(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}
}
