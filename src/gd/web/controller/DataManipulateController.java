package gd.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gd.web.util.ExcelUtil;
import gd.web.entity.viewModel.DataTable;
import gd.web.service.StationService;
import gd.web.util.Enum;

@Controller
@RequestMapping(value="/data")
public class DataManipulateController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private StationService stationService;
	

	@RequestMapping(value="/export",method = RequestMethod.GET)
	public String export(){
		return "jsp/export";
	}
	
	
	@RequestMapping(value="/get/{id}",method = RequestMethod.POST)
	public void test(@PathVariable int id, HttpServletResponse response, HttpSession session) throws IOException{
		String excelName = "StreamData-"+id;
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
	//@RequestMapping(value="/get/{startDate}/{endDate}/{staId}/{direction}",method = RequestMethod.POST)
	//for test
	@RequestMapping(value="/get/{staId}",method = RequestMethod.GET)
	//public void generateExcel(@PathVariable String startDate,@PathVariable String endDate,@PathVariable int staId,@PathVariable String direction, HttpServletResponse response, HttpSession session) throws IOException{
	//for test
	public void generateExcel(@PathVariable int staId, HttpServletResponse response, HttpSession session) throws IOException{
		//for test
		String startDate = "2017-04-10";
		String endDate = "2017-04-16";
		//int staId=1;
		String direction=Enum.BOTH.toString();
		
		
		List<DataTable> dataTables = stationService.generateDataTable(startDate,endDate,staId,direction);
		if(dataTables == null){
			return ;
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			wb = ExcelUtil.generateExcel(dataTables);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String excelName = "StreamData";
		String[] list =  new String[]{"firstcol","secondcol","thirdcol"};
		response.setContentType("application/vnd.ms-excel");    
        response.setHeader("Content-disposition", "attachment;filename="+ excelName +".xls");    
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
		
	}

}
