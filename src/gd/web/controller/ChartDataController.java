package gd.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import antlr.collections.List;
import gd.web.entity.ChartDataEntity;
import gd.web.entity.StationEntity;
import gd.web.service.ChartDataService;
import gd.web.util.Util;
import gd.web.util.Enum;

@Controller
@RequestMapping(value="/chartdata")
public class ChartDataController {
	@Autowired
	private ChartDataService chartDataService;

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	

	/**
	 *  when this method will be called? 
	 *  	when OPERATOR sign in, Ajax nothing to return.
	 *  	when ADMINISTRATOR want to watch the data of the station, Ajax nothing to return.
	 *  	when 
	 * @param staId
	 * @param currHour
	 */
	@RequestMapping(value="/init/{staId}",method = RequestMethod.POST)
	public void initChart(@PathVariable int staId,int currHour){
		ChartDataEntity chartDataEntity = chartDataService.getEntityByStaId(staId,Enum.IN.toString());
		if(chartDataEntity == null){
			
		}
		int missHour = currHour+24-chartDataEntity.getCurrHour();
		if(missHour>0){
			chartDataService.resetChart(missHour,staId);
		}
		
	}
	
	@RequestMapping(value="/update/{id}",method = RequestMethod.POST)
	public void updateChartData(@PathVariable int id,String datas,int currHour){
		
		ChartDataEntity chartDataEntity = chartDataService.getEntityById(id);
		if(chartDataEntity==null){
			return;
		}
		chartDataEntity.setDatas(datas);
		chartDataEntity.setCurrHour(currHour);
		chartDataService.updateChartData(chartDataEntity);

		chartDataService.processStreamTable(chartDataEntity.getDirection(),chartDataEntity.getStaId(),chartDataEntity.getDatas());
		
	}
	
	
	
	
	//uesless now!!!
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
