package gd.web.util;

import java.io.File;
import java.io.IOException;
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

public class ExcelUtil {
      
    public static <T> HSSFWorkbook setWorkbook(String[] excelHeader,List<T> list) {    
        HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet("Campaign");    
        HSSFRow row = sheet.createRow((int) 0);    
        HSSFCellStyle style = wb.createCellStyle();    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);    
    
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);    
            cell.setCellValue(excelHeader[i]);    
            cell.setCellStyle(style);    
            sheet.autoSizeColumn(i);    
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
