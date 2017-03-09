package gd.web.entity;

public class PointCommon {
	
	private int id;

	private String alias;
	
	private String fullName;
	
	private String addr;
	
	private String brief;
	
	private String imgPath;
	
	private int totalLot;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public int getTotalLot() {
		return totalLot;
	}

	public void setTotalLot(int totalLot) {
		this.totalLot = totalLot;
	}
}
