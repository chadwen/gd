package gd.web.controller;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import antlr.collections.List;
import gd.web.entity.ChartDataEntity;
import gd.web.entity.StationEntity;
import gd.web.service.ChartDataService;
import gd.web.util.Util;

@Controller
@RequestMapping(value="/chartdata")
public class ChartDataController {
	@Autowired
	private ChartDataService chartDataService;
	@Autowired
	private ChartDataEntity chartDataEntity;
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	// when the station was added, this method would be called to add an data into ChartData table.
	// this method should be migrated to the service layer.
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public void updateChartData(int[] dataArr,int id){
		ChartDataEntity cde = chartDataService.getEntityById(id);		

		//ChartDataEntity cde = new ChartDataEntity();
		//cde.setDirection(gd.web.util.Enum.IN.toString());
		StringBuilder sb = new StringBuilder();
		for(int item : dataArr){
			sb.append(item).append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		cde.setDatas(sb.toString());
		cde.setCurrHour(8);
		//chartDataService.addChartData(cde);
		chartDataService.updateChartData(cde);
		
	}
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String setChartData(){
		ChartDataEntity cdeIN = new ChartDataEntity();
		cdeIN.setStaId(6);
		cdeIN.setCurrHour(Util.getCurrHour());
		cdeIN.setDirection(gd.web.util.Enum.IN.toString());
		//chartDataService.addChartData(cdeIN);
		ChartDataEntity cdeOUT = new ChartDataEntity();
		cdeOUT.setStaId(6);
		cdeOUT.setCurrHour(Util.getCurrHour());
		cdeOUT.setDirection(gd.web.util.Enum.OUT.toString());
		//chartDataService.addChartData(cdeOUT);
		return null;
	}
	
}
