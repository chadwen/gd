package gd.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gd.web.entity.StationEntity;
import gd.web.service.StationService;

@Controller
@RequestMapping(value="/station")
public class StationController {
	@Autowired
	private StationService stationService;
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	
	@RequestMapping(value="/preAdd",method = RequestMethod.GET)
	public String preAddStation(String posx,String posy,Model model){
		
		model.addAttribute("posx", posx);
		model.addAttribute("posy", posy);
		return "jsp/adminUser";
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addStation(StationEntity stationEntity){

		stationService.addStation(stationEntity);
		
		StationEntity newSta = stationService.getEntityByAlias(stationEntity.getStaAlias());
		if(newSta==null){
			return "jsp/error";
		}
		//set chartdata here
		return "jsp/map";
	}
	@RequestMapping(value="/update",method = RequestMethod.GET)
	public String updatePoint(int id){
		
		return "jsp/updatePoint";
	}
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public String deletePoint(int id){
		
		return "jsp/map";
	}
}
