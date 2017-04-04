package gd.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gd.web.util.ExcelUtil;

@Controller
@RequestMapping(value="/data")
public class DataManipulateController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping(value="/get/{id}",method = RequestMethod.GET)
	public void test(@PathVariable int id, HttpServletResponse response, HttpSession session) throws IOException{
		String excelName = "StreamData";
		String[] list =  new String[]{"firstcol","secondcol","thirdcol"};
		List<Integer> dataList = new ArrayList<Integer>();
		dataList.add(11);
		dataList.add(12);
		dataList.add(13);
		HSSFWorkbook wb = ExcelUtil.setWorkbook(list,dataList);
		response.setContentType("application/vnd.ms-excel");    
        response.setHeader("Content-disposition", "attachment;filename="+ excelName +".xls");    
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);    
        ouputStream.flush();    
        ouputStream.close();
	}

}
