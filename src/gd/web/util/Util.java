package gd.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Util {
	private static Calendar cal = null;
	
	public static String arrayToString(String[] array){
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i<array.length-1;i++){
			sb.append(array[i]+",");
		}
		sb.append(array[array.length-1]);
		return sb.toString();
	}
	
	public static <T>String arrToStr(T[] array){
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i<array.length-1;i++){
			sb.append(array[i]+",");
		}
		sb.append(array[array.length-1]);
		return sb.toString();
	}
	
	public static ArrayList<Integer> stringToList(String str){
		String[] arr = str.split(",");
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(String item : arr){
			list.add(Integer.parseInt(item));
		}
		return list;
	}
	
	public static String getFormatDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
		//cal = Calendar.getInstance();
		//return sdf.format(cal.getTime());
	}
	
	public static String getFormatDateAll(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
		//cal = Calendar.getInstance();
		//return sdf.format(cal.getTime());
	}
	public static Date stringToDate(String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(dateFormat);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getCurrHour(){
		cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static int getIntervalDay(Date od){
		cal = Calendar.getInstance();
		//SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Date od = sdf.parse(formatData);
		Date nd = new Date();
		cal.setTime(od);
		long odm = cal.getTimeInMillis();
		cal.setTime(nd);
		long ndm = cal.getTimeInMillis();
		
		int days = (int)(ndm-odm)/(1000*24*60*60);
		//System.out.println(days);
		return days;
	}

	public static void countList(ArrayList<Integer> inDataList, String datas) {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = stringToList(datas);
		for(int i = 0 ; i < 24 ; i++){
			inDataList.set(i,inDataList.get(i)+list.get(i));
		}
	}
}
