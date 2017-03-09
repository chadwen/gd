package gd.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import gd.web.entity.ParkEntity;
import gd.web.entity.StationEntity;
import gd.web.service.ParkService;
import gd.web.service.StationService;

@Controller
@RequestMapping(value="/map")
public class MapController {

	@Autowired
	private StationService stationService;
	
	@Autowired
	private ParkService parkService;
	
	@RequestMapping(value="initStation",method=RequestMethod.POST)
	public @ResponseBody List<StationEntity> initStation(){
		return stationService.getAllStationEntity();
	}
	
	@RequestMapping(value="initPark",method=RequestMethod.POST)
	public @ResponseBody List<ParkEntity> initPark(){
		return parkService.getAllParkEntity();
	}
}
