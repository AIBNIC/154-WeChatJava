package dao;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import means.DbManager;
import means.Time;

public class GuZhangDao {
	DbManager db = new DbManager();
	Time time = new Time();
	PreparedStatement pstm = null;
	ResultSet rs = null;

//	查询全部故障
	public ArrayList<Map<String, String>> searchAll() {
		Connection con = db.getConnection("xyw", "xyw", "***REMOVED***","211.66.88.165");
		String sql = "SELECT a.username,a.xuehao," + "b.faultcontent,b.create_time,b.lh,b.fh,b.faultname,b.id "
				+ "FROM xyw_user a LEFT JOIN xyw_fault b "
				+ "ON a.xuehao=b.userid where b.state=0 and b.lh LIKE '北区%' ";
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			ArrayList<Map<String, String>> list = new ArrayList();
			while (rs.next()) {
				Map stu = new HashMap();
				String student_number = rs.getString("xuehao");
				String student_name = rs.getString("username");
				String floor = rs.getString("lh");
				String room = rs.getString("fh");
				String fault_name = rs.getString("faultname");
				String fault_description = rs.getString("faultcontent");
				String reporting_time = rs.getString("create_time");
				String id = rs.getString("id");

				stu.put("id", id);
				stu.put("student_number", student_number);
				stu.put("student_name", student_name);
				stu.put("floor", floor);
				stu.put("room", room);
				stu.put("fault_name", fault_name);
				stu.put("fault_description", fault_description);
				stu.put("reporting_time", reporting_time);

				list.add(stu);
				
			}
			return list;
		} catch (SQLException e) {
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

//	删除故障
	public int delate(String user_id, String fault_id) {
		Connection con = db.getConnection("xyw", "xyw", "***REMOVED***","211.66.88.165");
		String finish_time = time.getTime();
		String sql = "UPDATE xyw_fault set state=? ,finish_man=? ,finish_time=? where id=? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "1");
			pstm.setString(2, user_id);
			pstm.setString(3, finish_time);
			pstm.setString(4, fault_id);
			int count = pstm.executeUpdate();
			System.out.println(count);
//			0为修改成功，1为修改失败，2为后台代码出错
			if (count == 1) {
				return 1;
			} else {
				return 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.Clossall(con, pstm, rs);
			} catch (Exception e) {
				e.printStackTrace();

				return 2;
			}
		}
		return 2;
	}

//	根据栋数查询
	public ArrayList searchByDs(String ds) {
		Connection con = db.getConnection("xyw", "xyw", "***REMOVED***","211.66.88.165");
		try {

			String sql = "SELECT a.username,a.xuehao," + "b.faultcontent,b.create_time,b.lh,b.fh,b.faultname,b.id "
					+ "FROM xyw_user a LEFT JOIN xyw_fault b " + "ON a.xuehao=b.userid where b.state=0 and b.lh like ? ";

			pstm = con.prepareStatement(sql);
			pstm.setString(1, '%'+ds+'%');
			rs = pstm.executeQuery();
			ArrayList<Map<String, String>> list = new ArrayList();
			while (rs.next()) {
				Map stu = new HashMap();
				String student_number = rs.getString("xuehao");
				String student_name = rs.getString("username");
				String floor = rs.getString("lh");
				String room = rs.getString("fh");
				String fault_name = rs.getString("faultname");
				String fault_description = rs.getString("faultcontent");
				String reporting_time = rs.getString("create_time");
				String id = rs.getString("id");

				stu.put("id", id);
				stu.put("student_number", student_number);
				stu.put("student_name", student_name);
				stu.put("floor", floor);
				stu.put("room", room);
				stu.put("fault_name", fault_name);
				stu.put("fault_description", fault_description);
				stu.put("reporting_time", reporting_time);
				list.add(stu);

			}
			return list;
		} catch (SQLException e) {
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

// 	上报故障(1代表上传成功，0代表上传失败,2表示操作有误)
	public int UpGuZhang(String student_name ,String student_id ,String lh,String fh,String student_error) {
		try {
			String reatime_time = time.getTime();
			Connection con = db.getConnection("xyw", "xyw", "***REMOVED***","211.66.88.165");
			String sql = "INSERT INTO xyw_fault (faultname,create_time,faultcontent,userid,state,lh,fh)"
					+ "VALUES('其他故障',?,?,?,'0',?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, reatime_time);
			pstm.setString(2, student_error);
			pstm.setString(3, student_id);
			pstm.setString(4, lh);
			pstm.setString(5, fh);
			int count = pstm.executeUpdate();
			System.out.println(count);
			if(count == 1) {
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 2;
	}

}
