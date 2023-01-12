package service;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

import dao.GuZhangDao;
import dao.XywUser;
import entry.StuInfo;

public class GuZhangService {
	GuZhangDao dao = new GuZhangDao();

//	查询全部
	public ArrayList<?> searchall() {
		ArrayList<?> gzs = dao.searchAll();
		return gzs;
	}

//	查询某一栋
	public ArrayList searchByDs(String ds) {
		ArrayList gzs = dao.searchByDs(ds);
		return gzs;
	}

//删除故障
	public JSONObject delataGuZhang(String sb) {
		JSONObject json = JSONObject.parseObject(sb);
		String fault_id = json.getString("fault_id");
		String repair_id = json.getString("repair_id");
		int rs = dao.delate(repair_id, fault_id);
		JSONObject rsjson = new JSONObject();
//		1为修改成功，0为修改失败，2为后台代码出错
		if (rs == 0) {
			rsjson.put("msg", "修改失败");
		} else if (rs == 1) {
			rsjson.put("rs", "修改成功");
		} else {
			rsjson.put("msg", "后台出错，请检查");
		}
		return rsjson;
	}

//	上传故障
//	0为修改失败，1为修改成功，2为后台代码出错
	public JSONObject UpGuZhang(String student_name, String student_id, String student_room, String student_error) {
		XywUser user = new XywUser();
		StuInfo info = user.Search(student_name, student_id, student_name);
		JSONObject rsjson = new JSONObject();
		if (info.getStu_name() != null) {
			String[] room = student_room.split("#");
			String lh = "北区" + room[0] + "栋";
			String fh = room[1];
			int rs = dao.UpGuZhang(student_name, student_id, lh, fh, student_error);
			if (rs == 0) {
				rsjson.put("msg", "修改失败");
			} else if (rs == 1) {
				rsjson.put("success", "修改成功");
			} else {
				rsjson.put("msg", "后台出错，请检查");
			}
			return rsjson;
		}
		rsjson.put("msg", "学号或者姓名出错");
		return rsjson;
	}

}
