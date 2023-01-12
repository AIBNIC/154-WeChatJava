package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import means.Ip;
import sum.LoginInf;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
//			用户登录的信息
			String userId = request.getParameter("xuehao");
			String userPwd = request.getParameter("password");
			String code = request.getParameter("code");
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
			    ipAddress = request.getRemoteAddr();
			}
			if (code != null || userId != null || userPwd != null 
					|| !code.equals("") || !userId.equals("") || !userPwd.equals("")) {
				LoginInf inf = new LoginInf();
				JSONObject userdata = inf.loginData(userId, userPwd, code, ipAddress);
				if (userdata.get("success") != null) {
					HttpSession session = request.getSession();
					request.getSession(true);
					String seesionid = session.getId();
					userdata.put("SessionId", seesionid);
					ServletContext application = session.getServletContext();
					application.setAttribute("seesionid", seesionid);
					session.setMaxInactiveInterval(60*20);
					out.print(userdata);
				} else {
					out.print(userdata);
				}
			} else
				out.print("重新登录");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
