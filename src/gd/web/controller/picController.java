package gd.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping(value="/rec",method = RequestMethod.GET)
	public String recordGet(HttpSession session){
		ServletContext context = session.getServletContext();
		ArrayList<String> records = (ArrayList<String>) context.getAttribute("myrecords");
		if(records == null){
			records = new ArrayList<String>();
			context.setAttribute("myrecords", records);
		}
		
		session.setAttribute("records", records);
		return "jsp/pic/record";
	}
	
	@RequestMapping(value="/rec",method = RequestMethod.POST)
	public String recordPost(String str, HttpSession session){
		
		/*ServletContext context = session.getServletContext();
		ArrayList<String> records = (ArrayList<String>) context.getAttribute("myrecords");
		if(records == null){
			records = new ArrayList<String>();
			context.setAttribute("myrecords", records);
		}
		records.add(str);*/
		addRecord(str,session);
		
		return "redirect:/pic/rec";
	}
	//@RequestMapping(value="/upll",method = RequestMethod.POST)
	public String uploadFile(MultipartFile file, HttpSession session) throws IllegalStateException, IOException{
		String path = "D:/newfolder/";
		String fileName = file.getOriginalFilename();
		File dir = new File(path,fileName);
		if(!dir.exists()){
            dir.mkdirs();
        }
		file.transferTo(dir);
		
		addRecord(fileName,session);
		
		return "redirect:/pic/rec";
	}
	
	private void addRecord(String str, HttpSession session){
		ServletContext context = session.getServletContext();
		ArrayList<String> records = (ArrayList<String>) context.getAttribute("myrecords");
		if(records == null){
			records = new ArrayList<String>();
			context.setAttribute("myrecords", records);
		}
		records.add(str);
	}
}
