package gd.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gd.web.util.ExcelUtil;
import gd.web.util.Util;
import gd.web.entity.StationEntity;
import gd.web.entity.UserEntity;
import gd.web.entity.viewModel.DataTable;
import gd.web.entity.viewModel.UserInfo;
import gd.web.entity.viewModel.WebInfo;
import gd.web.service.StationService;
import gd.web.service.UserService;
import gd.web.util.Enum;

@Controller
@RequestMapping(value="/data")
public class DataManipulateController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private StationService stationService;
	
	@Autowired
	private UserService userService;
	
	

	@RequestMapping(value="/export",method = RequestMethod.GET)
	public String export(HttpSession session){
		if(session.getAttribute("priv")==null || !(session.getAttribute("priv").toString().equals(Enum.ADMINISTRATOR.toString()))){
			return "jsp/login";
		}
		return "jsp/export";
	}
	
	@RequestMapping(value="/getWebInfo",method = RequestMethod.POST)
	public @ResponseBody WebInfo getWebInfo(HttpSession session){
		WebInfo wi = new WebInfo();
		
		ServletContext context = session.getServletContext();
		Map<Integer,Integer> connectedMap = (Map<Integer, Integer>) context.getAttribute("connected_Map");
		if(connectedMap == null || connectedMap.size() == 0){
			wi.setConnectedNum(0);			
		}else{
			wi.setConnectedNum(connectedMap.get(0));
		}
		Map<Integer,String> userMap ;
		userMap = (Map<Integer, String>) context.getAttribute("user_map");
		if(userMap==null){
			userMap = new HashMap<Integer,String>();
			context.setAttribute("user_map", userMap);
		}

		List<UserInfo> staList = new ArrayList<UserInfo>();
		Iterator<Entry<Integer, String>> itor=userMap.entrySet().iterator();

		while(itor.hasNext()){
			Map.Entry<Integer, String> entry=(Map.Entry<Integer, String>)itor.next();
			UserInfo ui = new UserInfo();
			ui.setUserId(entry.getKey());
			UserEntity ue = userService.getUserById(entry.getKey());
			if(ue != null){
				ui.setUserName(ue.getUserName());
				ui.setStaId(ue.getStaId());
				if(ui.getStaId() == 0){
					continue;
				}
			}
			ui.setBrief(stationService.getStationById(ui.getStaId()).getBrief());
			staList.add(ui);
		}
		wi.setStaList(staList);
		return wi;
	}
	
	@RequestMapping(value="/getChartData",method = RequestMethod.POST)
	public @ResponseBody List<Integer> getChartData(String ids,String chartType,String startdate,String enddate,String direction) throws ParseException{
		/*if(session.getAttribute("userId")==null){
			return null;
		}*/
		List<Integer> staIds = Util.stringToList(ids);
		
		
		List<Integer> list = stationService.getListData(startdate,enddate,staIds,direction);
		if(list==null){
			list=new ArrayList<Integer>();
			for(int i =0; i < staIds.size(); i++){
				list.add(0);
			}
		}
		if(list.size()<staIds.size()){
			int length = staIds.size()-list.size();
			for(int i = 0; i < length; i++){
				list.add(0);
			}
		}
		
		return list;
	}
	

	@RequestMapping(value="/getSingle/{startDate}/{endDate}/{staIds}/{direction}",method = RequestMethod.GET)
	public void downloadSingle(@PathVariable String startDate,@PathVariable String endDate,@PathVariable String staIds,@PathVariable String direction, HttpServletResponse response, HttpSession session) throws IOException{
		List<Integer> ids = Util.stringToList(staIds);
		List<DataTable> dataTables = stationService.generateDataTable(startDate,endDate,ids,direction);
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
		String excelName = "站点车流数据("+startDate.replace("-", ".") + "-" + endDate.replace("-", ".") + ")";
		response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8"); 
        response.setHeader("Content-Disposition", "attachment;filename="+new String(excelName.getBytes("gbk"), "iso8859-1")+".xls"); 
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
	}
	@RequestMapping(value="/getCal/{startDate}/{endDate}/{staIds}/{direction}",method = RequestMethod.GET)
	public void downloadCal(@PathVariable String startDate,@PathVariable String endDate,@PathVariable String staIds,@PathVariable String direction, HttpServletResponse response, HttpSession session) throws IOException, ParseException{
		List<Integer> ids = Util.stringToList(staIds);
		List<DataTable> dataTables = stationService.generateDataTableCal(startDate,endDate,ids,direction);
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
		String excelName = "统计车流数据("+startDate.replace("-", ".") + "-" + endDate.replace("-", ".") + ")";
		response.setContentType("application/vnd.ms-excel");  
        response.setCharacterEncoding("utf-8");  
        response.setHeader("Content-Disposition", "attachment;filename="+new String(excelName.getBytes("gbk"), "iso8859-1")+".xls"); 
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
	}
	
	@RequestMapping(value="/getAllStations",method = RequestMethod.GET)
	public void getAllStations(HttpServletResponse response, HttpSession session) throws IOException{
		List<StationEntity> staList = stationService.getAllStationEntity();
		if(staList == null){
			return ;
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		try {
			wb = ExcelUtil.generateExcelStation(staList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String excelName = "出入站点信息";
		response.setContentType("application/vnd.ms-excel");  
        response.setCharacterEncoding("utf-8");  
        response.setHeader("Content-Disposition", "attachment;filename="+new String(excelName.getBytes("gbk"), "iso8859-1")+".xls"); 
        OutputStream ouputStream = response.getOutputStream();    
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
	}
	@RequestMapping(value="/getLoginedStation",method = RequestMethod.POST)
	public @ResponseBody List<UserInfo> getLoginedStation(HttpSession session){
		/*if(session.getAttribute("userId")==null){
			return null;
		}*/
		List<UserInfo> list = new ArrayList<UserInfo>();
		ServletContext context = session.getServletContext(); 
		Map<Integer,String> userMap ;
		userMap = (Map<Integer, String>) context.getAttribute("user_map");
		if(userMap==null){
			return list;
		}
		Iterator<Entry<Integer, String>> itor=userMap.entrySet().iterator();

		while(itor.hasNext()){
			Map.Entry<Integer, String> entry=(Map.Entry<Integer, String>)itor.next();
			UserInfo ui = new UserInfo();
			ui.setUserId(entry.getKey());
			ui.setStaId(userService.getUserById(entry.getKey()).getStaId());
			if(ui.getStaId() == 0){
				continue;
			}
			ui.setBrief(stationService.getStationById(ui.getStaId()).getBrief());
			list.add(ui);
		}
		return list;
	}
	
	
	//for test useless now
	@RequestMapping(value="/get/{staId}",method = RequestMethod.GET)
	public void generateExcel(@PathVariable int staId, HttpServletResponse response, HttpSession session) throws IOException{
		//for test
		String startDate = "2017-04-10";
		String endDate = "2017-04-16";
		//int staId=1;
		String direction=Enum.BOTH.toString();
		
		List<Integer> staIds = new ArrayList<Integer>();
		staIds.add(staId);
		List<DataTable> dataTables = stationService.generateDataTable(startDate,endDate,staIds,direction);
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
