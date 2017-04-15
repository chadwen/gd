package gd.web.entity.viewModel;

import java.util.ArrayList;

public class ChartData {

	private ArrayList<Integer> outDataList;
	
	private ArrayList<Integer> inDataList;
	
	//only two elements, IN and OUT id in chartdata table
	private ArrayList<Integer> ids;
	
	private ArrayList<Integer> hourList;
	
	private int staId;
	
	private String brief;

	public ArrayList<Integer> getOutDataList() {
		return outDataList;
	}

	public void setOutDataList(ArrayList<Integer> outDataList) {
		this.outDataList = outDataList;
	}

	public ArrayList<Integer> getInDataList() {
		return inDataList;
	}

	public void setInDataList(ArrayList<Integer> inDataList) {
		this.inDataList = inDataList;
	}

	public ArrayList<Integer> getIds() {
		return ids;
	}

	public void setIds(ArrayList<Integer> ids) {
		this.ids = ids;
	}

	public ArrayList<Integer> getHourList() {
		return hourList;
	}

	public void setHourList(ArrayList<Integer> hourList) {
		this.hourList = hourList;
	}

	public int getStaId() {
		return staId;
	}

	public void setStaId(int staId) {
		this.staId = staId;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}
}
