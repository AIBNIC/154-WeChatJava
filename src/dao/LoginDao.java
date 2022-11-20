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
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import entry.UserInfo;
import jdbc.DbManager;

public class LoginDao {
	DbManager db = new DbManager();
	PreparedStatement pstm = null;
	ResultSet rs = null;
	Connection con = db.getConnection("fhy", "root", "123456");

//	验证登录
/**	
	
	public Map<String, Comparable> login(String loginid, String loginpwd) {
//		System.out.println(loginid);
		String sql = "select * from Wx_users where user_number=?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, loginid);
			rs = pstm.executeQuery();
			Map<String, Comparable> data = new HashMap();
			if (rs.next()) {
//				获取数据库密码
				String user_password = rs.getString("user_password");
//				用户输入密码与数据库密码是否匹配
				if (loginpwd.equals(user_password)) {
					String user_name = rs.getString("user_name");
					String user_number = rs.getString("user_number");
					String user_floor = rs.getString("user_floor");
					int level = rs.getInt("level");
					String wx_name = rs.getString("wx_name");
//					String wx_img = rs.getString("wx_img");
//					String wx_openid = rs.getString("wx_openid");
					String user_identity = rs.getString("user_identity");

					data.put("user_name", user_name);
					data.put("user_number", user_number);
					data.put("user_password", user_password);
					data.put("user_floor", user_floor);
					data.put("level", Integer.valueOf(level));
					data.put("wx_name", wx_name);
					data.put("user_identity", user_identity);
					return data;
				} else {
					System.out.println("密码错误");
					data.put("msg", "密码错误");
					return data;
				}
			} else {
				System.out.println("找不到人");
				data.put("msg", "用户不存在");
				return data;
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

	
//获取openid
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
**/


}
