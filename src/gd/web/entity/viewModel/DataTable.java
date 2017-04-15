package gd.web.entity.viewModel;

//for build excel file
public class DataTable {

	private String dateTime;
	
	private int staId;
	
	private String staBrief;
	
	private String direction;
	
	private int total;
	
	private int average;
	
	private String datas;

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getStaId() {
		return staId;
	}

	public void setStaId(int staId) {
		this.staId = staId;
	}

	public String getStaBrief() {
		return staBrief;
	}

	public void setStaBrief(String staBrief) {
		this.staBrief = staBrief;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getAverage() {
		return average;
	}

	public void setAverage(int average) {
		this.average = average;
	}

	public String getDatas() {
		return datas;
	}

	public void setDatas(String datas) {
		this.datas = datas;
	}
	
}
