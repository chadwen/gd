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
	
	/**
	 * add a park
	 * @param parkEntity
	 * @return
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public String addPark(ParkEntity parkEntity){

		if("".equals(parkEntity.getBrief())){
			parkEntity.setBrief(parkEntity.getFullName());
		}
		parkService.addPark(parkEntity);
		return "jsp/map";
	}
	/**
	 * when refresh with the special method, do not go to "POST"
	 * @return
	 */
	@RequestMapping(value="/add",method = RequestMethod.GET)
	public String addParkGet(){
		return "jsp/map";
	}
	/**
	 * get park POST
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/get/{id}",method = RequestMethod.POST)
	public @ResponseBody ParkEntity getPark(@PathVariable int id){
		return parkService.getParkById(id);
	}
	/**
	 * update park
	 * @param parkEntity
	 * @return
	 */
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String updatePark(ParkEntity parkEntity){
		parkEntity.setModifyTime(new Date());
		parkService.updatePark(parkEntity);
		return "redirect:/user/entry";
	}
	/**
	 * when refresh with the special method, do not go to "POST"
	 * @return
	 */
	@RequestMapping(value="/update",method = RequestMethod.GET)
	public String updateParkGet(){
		return "redirect:/user/entry";
	}
	/**
	 * delete park. in fact, set isValid to 0.
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method = RequestMethod.GET)
	public String deleteParkGet(@PathVariable int id){
		parkService.deletePark(id);
		return "redirect:/user/entry";
	}
	
	//useless
	/*@RequestMapping(value="/delete/{id}",method = RequestMethod.POST)
	public String deletePark(@PathVariable int id){
		parkService.deletePark(id);
		return "redirect:/user/entry";
	}*/
}
