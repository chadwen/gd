package gd.web.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gd.web.entity.StationEntity;
import gd.web.entity.viewModel.PointCommon;
import gd.web.service.StationService;

@Controller
@RequestMapping(value="/station")
public class StationController {

	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private StationService stationService;
	
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addStation(StationEntity stationEntity){

		stationService.addStation(stationEntity);
		
		StationEntity newSta = stationService.getEntityByAlias(stationEntity.getAlias());
		if(newSta==null){
			return "jsp/error";
		}
		//set chartdata here
		return "jsp/map";
	}
	@RequestMapping(value="/getall",method = RequestMethod.POST)
	public @ResponseBody List<StationEntity> getAllStation(){
			
		return stationService.getAllStationEntity();
	}
	
	@RequestMapping(value="/get/{id}",method = RequestMethod.POST)
	public @ResponseBody StationEntity getStation(@PathVariable int id){
			
		return stationService.getStationById(id);
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String updateStation(PointCommon pointCommon){
		
		stationService.updateStation(stationService.convertToStation(pointCommon));
		return "redirect:/user/entry";
	}
	/**
	 * test method update
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/update/{id}",method = RequestMethod.GET)
	public String testupdateStation(@PathVariable int id){
		StationEntity se = new StationEntity();
		se.setId(id);
		se.setAlias("XXXXXXXXXXXXXX");
		//stationService.updateStation(se);
		
		return "redirect:/user/entry";
		//return "jsp/map";
	}
	
	@RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
	public String deleteStation(@PathVariable int id){
		stationService.deleteStation(id);
		return "redirect:/user/entry";
	}
	//delete station. in fact, set isValid to 0.
	@RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
	public String deleteSta(@PathVariable int id){
		stationService.deleteStation(id);
		return "redirect:/user/entry";
	}
}
