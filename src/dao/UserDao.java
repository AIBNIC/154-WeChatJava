package dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.fastjson.JSONObject;

import entry.UserInfo;
import means.DbManager;

public class UserDao {
	DbManager db = new DbManager();
	PreparedStatement pstm = null;
	ResultSet rs = null;

//	验证登录
	public JSONObject searchUserById(String loginid) {
		Connection con = db.getConnection("lww", "xzw", "***REMOVED***","211.66.88.169");
		try {
			String sql = "select * from Wx_users where user_number=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, loginid);
			rs = pstm.executeQuery();
			JSONObject stu = new JSONObject();
			if (rs.next()) {
				
				
				String user_password = rs.getString("user_password");
				String user_name = rs.getString("user_name");
				String user_number = rs.getString("user_number");
				String user_floor = rs.getString("user_floor");
				String user_identity = rs.getString("user_identity");
				String wx_openid = rs.getString("wx_openid");
				int level = rs.getInt("level");
				String wx_check = rs.getString("wx_check");
				stu.put("user_password", user_password);
				stu.put("user_name", user_name);
				stu.put("user_number", user_number);
				stu.put("user_floor", user_floor);
				stu.put("user_identity", user_identity);
				stu.put("wx_openid", wx_openid);
				stu.put("level", level);
				stu.put("wx_check", wx_check);
				return stu;
			} else {
				stu.put("msg", "找不到人");
				return stu;
			}
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

//获取session_key和openid
	public String getOppid(String JSCODE) {
		BufferedReader in = null;
//		拼装url
		String AppId = "***REMOVED***";
		String AppSecret = "***REMOVED***";
		String url = "https://api.weixin.qq.com/sns/jscode2session?" + "appid=" + AppId + "&secret=" + AppSecret
				+ "&js_code=" + JSCODE + "&grant_type=authorization_code";

		URL weChatUrl;
		try {
			weChatUrl = new URL(url);
			URLConnection connection = weChatUrl.openConnection();
			// 设置通用的请求属性
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String sb = "";
//			读取数据
			String line;
			while ((line = in.readLine()) != null) {
				sb += line;
			}
			return sb;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

//更换openid
	public void upData(String old_openid, String openid, String user_number) {
		Connection con = db.getConnection("lww", "xzw", "***REMOVED***","211.66.88.169");		
		String sql = "UPDATE wx_users set wx_openid=? , wx_check=1 where user_number=? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, openid);
			pstm.setString(2, user_number);
			int count = pstm.executeUpdate();

			if (count != 0) {
//				System.out.println("成功更换code");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	查询网管
	public UserInfo searchUserByOpenid(String check_openid) {
		Connection con = db.getConnection("lww", "xzw", "***REMOVED***","211.66.88.169");	
		try {
			System.out.println(check_openid);
			String sql = "select * from wx_users where wx_openid=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, check_openid);
			rs = pstm.executeQuery();
			if (rs.next()) {
				String user_name = rs.getString("user_name");
				String user_number = rs.getString("user_number");
				String user_password = rs.getString("user_password");
				String user_floor = rs.getString("user_floor");
				String user_identity = rs.getString("user_identity");
				String openid = rs.getString("wx_openid");
				String level = rs.getString("level");
				String wx_check = rs.getString("wx_check");
				UserInfo info = new UserInfo(user_number, user_password, user_floor, user_identity, openid,
				level, user_name,wx_check);
				return info;
			}
			return null;
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

//	更改密码
	public int Changpwd(String newuserpwd, String userid, String userpwd) {
		try {
			System.out.println(newuserpwd);
			System.out.println(userid);
			System.out.println(userpwd);
			
			Connection con = db.getConnection("lww", "xzw", "***REMOVED***","211.6.88.169");	
			String sql = "UPDATE wx_users set user_password=? where user_number=? and user_password=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, newuserpwd);
			pstm.setString(2, userid);
			pstm.setString(3, userpwd);
			int count = pstm.executeUpdate();
			return count;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
}
