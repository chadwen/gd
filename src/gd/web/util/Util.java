package gd.web.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Util {
	private static Calendar cal = null;
	
	public static String arrayToString(String[] array){
		if(array.length==0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i<array.length-1;i++){
			sb.append(array[i]+",");
		}
		sb.append(array[array.length-1]);
		return sb.toString();
	}
	public static String listToString(List<Integer> list){
		if(list.size()==0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i<list.size()-1; i++){
			sb.append(list.get(i)+",");
		}
		sb.append(list.get(list.size()-1));
		return sb.toString();
	}
	
	public static <T>String arrToStr(T[] array){
		if(array.length==0){
			return "";
		}
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
	public static double getFormatDouble(double dou){
		return Math.round(dou*100)/100.0;
	}
	public static String getDecimalFormat(double dou){
		DecimalFormat df  = new DecimalFormat("###.00");
		return df.format(dou);
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
	public static List<String> getStringDateList(String startDate, String endDate) throws ParseException{  
        String start = startDate;  
        String end = endDate;  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date dBegin = sdf.parse(start);  
        Date dEnd = sdf.parse(end);  
        List<Date> addDates = getDatesBetweenTwoDate(dBegin, dEnd);  
        List<String> dateList = new ArrayList<String>();
        for(int i=0;i<addDates.size();i++){  
            //System.out.println(sdf.format(addDates.get(i))); 
            dateList.add(sdf.format(addDates.get(i)));
        } 
        return dateList;
    }  
    private static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {  
        List<Date> lDate = new ArrayList<Date>();  
        lDate.add(beginDate);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        cal.setTime(beginDate);  
        boolean bContinue = true;  
        while (bContinue) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后  
            if (endDate.after(cal.getTime())) {  
                lDate.add(cal.getTime());  
            } else {  
                break;  
            }  
        }  
        lDate.add(endDate);// 把结束时间加入集合  
        return lDate;  
    }  
}
