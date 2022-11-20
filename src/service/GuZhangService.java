package service;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

import dao.GuZhangDao;
import dao.XywUser;
import entry.StuInfo;

public class GuZhangService {
	GuZhangDao dao = new GuZhangDao();

//	��ѯȫ��
	public ArrayList<?> searchall() {
		ArrayList<?> gzs = dao.searchAll();
		return gzs;
	}

//	��ѯĳһ��
	public ArrayList searchByDs(String ds) {
		ArrayList gzs = dao.searchByDs(ds);
		return gzs;
	}

//ɾ������
	public JSONObject delataGuZhang(String sb) {
		JSONObject json = JSONObject.parseObject(sb);
		String fault_id = json.getString("fault_id");
		String repair_id = json.getString("repair_id");
		int rs = dao.delate(repair_id, fault_id);
		JSONObject rsjson = new JSONObject();
//		1Ϊ�޸ĳɹ���0Ϊ�޸�ʧ�ܣ�2Ϊ��̨�������
		if (rs == 0) {
			rsjson.put("msg", "�޸�ʧ��");
		} else if (rs == 1) {
			rsjson.put("rs", "�޸ĳɹ�");
		} else {
			rsjson.put("msg", "��̨��������");
		}
		return rsjson;
	}

//	�ϴ�����
//	0Ϊ�޸�ʧ�ܣ�1Ϊ�޸ĳɹ���2Ϊ��̨�������
	public JSONObject UpGuZhang(String student_name, String student_id, String student_room, String student_error) {
		XywUser user = new XywUser();
		StuInfo info = user.Search(student_name, student_id, student_name);
		JSONObject rsjson = new JSONObject();
		if (info.getStu_name() != null) {
			String[] room = student_room.split("#");
			String lh = "����" + room[0] + "��";
			String fh = room[1];
			int rs = dao.UpGuZhang(student_name, student_id, lh, fh, student_error);
			if (rs == 0) {
				rsjson.put("msg", "�޸�ʧ��");
			} else if (rs == 1) {
				rsjson.put("success", "�޸ĳɹ�");
			} else {
				rsjson.put("msg", "��̨��������");
			}
			return rsjson;
		}
		rsjson.put("msg", "ѧ�Ż�����������");
		return rsjson;
	}

}
