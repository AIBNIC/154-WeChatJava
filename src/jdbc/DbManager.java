package jdbc;

import java.sql.*;

public class DbManager {

	Connection con = null;

	public Connection getConnection(String database , String name , String pwd) {
		String url = "jdbc:mysql://127.0.0.1/"+" "+"?autoReconnect=true&amp;failOverReadOnly=false";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, name, pwd);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库驱动出错");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL语句有误");
			e.printStackTrace();
		}
		return con;
	}

	public void Clossall(Connection con, PreparedStatement pst, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
