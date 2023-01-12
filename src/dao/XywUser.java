package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.edu.gdngs.ambms.CardChargeProxy;
import entry.StuInfo;
import entry.UserInfo;
import means.DbManager;
import service.AmbmsService;

public class XywUser {
	DbManager db = new DbManager();
	PreparedStatement pstm = null;
	ResultSet rs = null;

	public StuInfo Search(String username, String num, String userid) {
		Connection con = db.getConnection("xyw", "xyw", "***REMOVED***","211.66.88.165");
		StuInfo info = new StuInfo();
		String sql = "SELECT * FROM"
				+ "(SELECT b.room,a.className,b.username,b.xuehao,b.userid,b.uproom FROM `xyw_user_class` a LEFT JOIN xyw_user b ON a.xuehao = b.xuehao ) "
				+ "AS a INNER JOIN xyw_room b ON b.id = a.room  where a.username=? or a.xuehao=? or a.userid=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, num);
			pstm.setString(3, userid);
			rs = pstm.executeQuery();
			if (rs.next()) {
				String lh = rs.getString("lh");
				String fh = rs.getString("fh");
				String stu_name = rs.getString("username");
				String stu_idcard = rs.getString("userid");
				String stu_number = rs.getString("xuehao");
				String stu_major = rs.getString("className");
				String stu_room = lh + fh;
				String uproom = rs.getString("uproom");
				AmbmsService service = new AmbmsService();
				String[] infos = service.EndTime(stu_number);
				String state = infos[0];
				String end_time = infos[1];
//				String end_time =null;
//				String state = "电信";
				info = new StuInfo(stu_name, stu_idcard, stu_number, stu_major, stu_room, end_time,uproom,state);
//				return stuinfo;
			} else {
				info.setMsg("找不到此人");
			}

			return info;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.Clossall(con, pstm, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
//	重置宿舍次数
	public int uproom(String userid) {
		try {
			Connection con = db.getConnection("xyw", "xyw", "***REMOVED***","211.66.88.165");
			String sql = "UPDATE xyw_user set uproom=0 where xuehao=? ";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userid);
			int count = pstm.executeUpdate();
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 2;
	}
	

}
