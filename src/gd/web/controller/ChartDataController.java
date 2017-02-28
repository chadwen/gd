package gd.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gd.web.entity.ChartDataEntity;
import gd.web.entity.StationEntity;
import gd.web.service.ChartDataService;

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
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public void setChartData(StationEntity sta){
		ChartDataEntity cde = new ChartDataEntity();
		cde.setStaId(6);
		chartDataService.addChartData(cde);
		
	}
	
}
