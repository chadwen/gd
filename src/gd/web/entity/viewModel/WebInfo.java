package gd.web.entity.viewModel;

import java.util.List;

public class WebInfo {
	
	private int connectedNum;
	
	private List<UserInfo> staList;

	private String message;
	
	private boolean success;
	
	

	public int getConnectedNum() {
		return connectedNum;
	}

	public void setConnectedNum(int connectedNum) {
		this.connectedNum = connectedNum;
	}

	public List<UserInfo> getStaList() {
		return staList;
	}

	public void setStaList(List<UserInfo> staList) {
		this.staList = staList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
