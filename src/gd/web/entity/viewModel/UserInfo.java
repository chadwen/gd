package gd.web.entity.viewModel;

import gd.web.entity.StationEntity;

public class UserInfo {

	private String priv;
	
	private String userName;
	
	private Integer userId;
	
	private Integer staId;
	
	private String brief;
	
	private StationEntity station;

	public String getPriv() {
		return priv;
	}

	public void setPriv(String priv) {
		this.priv = priv;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStaId() {
		return staId;
	}

	public void setStaId(Integer staId) {
		this.staId = staId;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public StationEntity getStation() {
		return station;
	}

	public void setStation(StationEntity station) {
		this.station = station;
	}
}
