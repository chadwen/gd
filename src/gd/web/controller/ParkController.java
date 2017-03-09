package gd.web.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gd.web.entity.ParkEntity;
import gd.web.service.ParkService;

@Controller
@RequestMapping(value="/park")
public class ParkController {
	
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ParkService parkService;
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addPark(ParkEntity parkEntity){
		parkService.addPark(parkEntity);
		return "jsp/map";
	}
	
	@RequestMapping(value="/get/{id}",method = RequestMethod.POST)
	public @ResponseBody ParkEntity getPark(@PathVariable int id){
		return parkService.getParkById(id);
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String updatePark(ParkEntity parkEntity){
		parkEntity.setModifyTime(new Date());
		parkService.updatePark(parkEntity);
		return "redirect:/user/entry";
	}
	
	@RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
	public String deletePark(@PathVariable int id){
		parkService.deletePark(id);
		return "redirect:/user/entry";
	}
}
