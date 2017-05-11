package gd.web.entity.viewModel;

import java.util.List;

public class WebInfo {
	
	private int ConnectedNum;
	
	private List<UserInfo> userList;

	public int getConnectedNum() {
		return ConnectedNum;
	}

	public void setConnectedNum(int connectedNum) {
		ConnectedNum = connectedNum;
	}

	public List<UserInfo> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfo> userList) {
		this.userList = userList;
	}
	
}
