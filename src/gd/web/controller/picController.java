package gd.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/pic")
public class picController {
	@RequestMapping(value="",method = RequestMethod.GET)
	public String index(HttpSession session){
		if(session.getAttribute("inner") == null || !session.getAttribute("inner").toString().equals("inner")){
			return "jsp/pic/credential";
		}
		return "jsp/pic/pic0";
	}
	
	@RequestMapping(value="/{jspName}",method = RequestMethod.GET)
	public String getPicn(@PathVariable String jspName,HttpSession session){
		if(session.getAttribute("inner") == null || !session.getAttribute("inner").toString().equals("inner")){
			return "jsp/pic/credential";
		}
		return "jsp/pic/"+jspName;
	}
	@RequestMapping(value="/{path1}/{jspName}",method = RequestMethod.GET)
	public String getPath1Pic(@PathVariable String path1,@PathVariable String jspName,HttpSession session){
		if(session.getAttribute("inner") == null || !session.getAttribute("inner").toString().equals("inner")){
			return "jsp/pic/credential";
		}
		return "jsp/pic/"+path1+"/"+jspName;
	}

	@RequestMapping(value="/credential",method = RequestMethod.POST)
	public String getCredentialPOST(String pwd, HttpSession session){
		if(!pwd.toString().equals("edcxx")){			
			session.setAttribute("inner", "outter");
			System.out.println("incorrect first time");
			return "jsp/error";
		}
		if(session.getAttribute("inner") != null && !session.getAttribute("inner").toString().equals("inner")){
			System.out.println("correct but not first time");
			return "jsp/error";
		}
		session.setAttribute("inner", "inner");
		return "redirect:pic0";
	}
}
