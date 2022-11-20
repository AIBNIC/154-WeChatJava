package service;

import com.alibaba.fastjson.JSONObject;

import dao.LogDao;
import entry.UserInfo;

public class LoginLogService extends Thread {
	private JSONObject info;
	private String ip;
	public LoginLogService(JSONObject info,String ip) {
		this.info = info;
		this.ip = ip;
	}
	 public void run() {
		String user_name = info.getString("user_name");
		String user_number = info.getString("user_number");
		String type = "µÇÂ¼";
		UserInfo loginfo = new UserInfo(user_name,user_number,type,ip,null);
		LogDao dao = new LogDao();
		dao.UpLog(loginfo);
	 }
}
