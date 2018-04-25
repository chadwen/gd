package gd.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/eve")
public class EVEController {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@RequestMapping(value="",method = RequestMethod.GET)
	public String index(HttpSession session){
		return "jsp/eve/index";
		//return "redirect:inf";
	}
	
	@RequestMapping(value="/inf",method = RequestMethod.GET)
	public String getINF(Model model,HttpSession session){
		List<Gmr> li = new ArrayList<Gmr>();
		newGmr(li);
		model.addAttribute("listM", li);
		return "jsp/eve/EVEINF";
	}
	
	
	private void newGmr(List<Gmr> li){
		li.add(new Gmr("艾玛海军","热能",""));
		li.add(new Gmr("卡尼迪王国（艾玛）","电磁","热能"));
		li.add(new Gmr("萨沙共和国（艾玛）","电磁","热能"));
		li.add(new Gmr("血袭者同盟（艾玛）","电磁","热能"));

		li.add(new Gmr("盖伦特海军","热能",""));
		li.add(new Gmr("天蛇（盖伦特）","动能","热能"));
		
		li.add(new Gmr("古斯塔斯海盗（加达里）","动能","热能"));
		li.add(new Gmr("加达里海军","动能",""));
		li.add(new Gmr("莫德团（加达里）","动能","热能"));

		li.add(new Gmr("米玛塔尔","热能",""));
		li.add(new Gmr("天使联合企业（米玛塔尔）","爆炸","动能"));
		
		li.add(new Gmr("雇佣兵","热能",""));
		li.add(new Gmr("图克尔部落","热能",""));
		li.add(new Gmr("自由无人机","电磁","热能"));
		li.add(new Gmr("克姆尼","热能",""));
		li.add(new Gmr("冬眠者","任意",""));
	}
	
	public class Gmr{
		public Gmr(){}
		public Gmr(String name,String lowest, String lower){
			this.name = name;
			this.lowestResistance = lowest;
			this.lowerResistance = lower;
		}
		private String name;
		private String lowestResistance;
		private String lowerResistance;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLowestResistance() {
			return lowestResistance;
		}
		public void setLowestResistance(String lowestResistance) {
			this.lowestResistance = lowestResistance;
		}
		public String getLowerResistance() {
			return lowerResistance;
		}
		public void setLowerResistance(String lowerResistance) {
			this.lowerResistance = lowerResistance;
		}
		
	}

}
