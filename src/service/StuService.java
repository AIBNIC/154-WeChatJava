package service;

import java.rmi.RemoteException;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import dao.XywUser;
import entry.StuInfo;

public class StuService {
	XywUser user = new XywUser();
	AmbmsService service = new AmbmsService();
//	1代表能查到学生信息，0代表查不到学生信息
	public StuInfo search(String content) throws Exception {
		StuInfo stuinfo = user.Search(content, content, content);
		if(stuinfo.getStu_name()!=null || !"".equals(stuinfo.getStu_name())) {
			return stuinfo;
		}
		return null;
	}
//	重置宿舍次数
	public JSONObject uproom(String userid) {
		JSONObject rs = new JSONObject();
		int count = user.uproom(userid);
		if(count==1) {
			rs.put("status", rs);
			return rs;
		}
		return null;
	}
}
