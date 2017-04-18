package gd.web.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import gd.web.entity.viewModel.DataTable;

public class ExcelUtil {
      
	
	public static <T> HSSFWorkbook generateExcel(List<T> list) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException{
/*		Class<?> demo = Class.forName("gd.web.entity.viewModel.DataTable");
		T inst = (T) demo.newInstance();
		//((DataTable) inst).getStaId();
		Method[] g = inst.getClass().getDeclaredMethods();
		Field[] g1 = inst.getClass().getDeclaredFields();
		Field staId = inst.getClass().getDeclaredField("staId");*/
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("data");    
        sheet.setDefaultColumnWidth(10);
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //generate header!!
        String[] excelHeader = getHeader();
        for(int i = 0 ; i < 29;i++){
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            //sheet.autoSizeColumn(i);        	
        }
		//generate body!!
        String sml = "";
        int rowNum = 0;
		for(T item : list){
			++rowNum;
        	row = sheet.createRow(rowNum);
			row.createCell(0).setCellValue((String)item.getClass().getDeclaredMethod("getDateTime").invoke(item));
			sml = row.getCell(0).toString();
			row.createCell(1).setCellValue((String)item.getClass().getDeclaredMethod("getStaBrief").invoke(item));
			sml = row.getCell(1).toString();
			row.createCell(2).setCellValue((String)item.getClass().getDeclaredMethod("getDirection").invoke(item));
			sml = row.getCell(2).toString();
			row.createCell(3).setCellValue((Integer)item.getClass().getDeclaredMethod("getTotal").invoke(item));

			sml = row.getCell(3).toString();
			row.createCell(4).setCellValue((Double)item.getClass().getDeclaredMethod("getAverage").invoke(item));

			sml = row.getCell(4).toString();
			String datas = (String)item.getClass().getDeclaredMethod("getDatas").invoke(item);
			ArrayList<Integer> dataList = Util.stringToList(datas);
			for(int i = 0 ; i < 24; i++){
				row.createCell(i+5).setCellValue((int)dataList.get(i));
			}
		}
		
		
        return wb;
	}
	private static String[] getHeader(){
		String[] header = new String[29];
		header[0]="日期";
		header[1]="站点";
		header[2]="方向";
		header[3]="总量";
		header[4]="平均值";
		for(int i = 0 ; i < 24 ; i++){
			header[i+5] = i>=10 ? ""+i+"时" : "0"+i+"时";
		}
		return header;
	}
    public static <T> HSSFWorkbook setWorkbook(String[] excelHeader,List<T> list) {    
        HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("data");    
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            //sheet.autoSizeColumn(i);
        }    
        for(int i = 0 ; i < list.size(); i++){
        	row = sheet.createRow(i+1);
        	row.createCell(0).setCellValue((Integer) list.get(0));
        	row.createCell(1).setCellValue((Integer) list.get(1));
        	row.createCell(2).setCellValue((Integer) list.get(2));
        }
        /*for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);  
            Student student = list.get(i);  
            row.createCell(0).setCellValue(student.getSno());  
            row.createCell(1).setCellValue(student.getName());  
            row.createCell(2).setCellValue(student.getAge());  
        }  */
     
        return wb;    
    } 
    
    // useless
    @Deprecated
    public void test(){
    	POIFSFileSystem file = new POIFSFileSystem();

        Workbook workbook = null;

        try {
        	workbook = WorkbookFactory.create(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
