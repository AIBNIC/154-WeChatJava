package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import sum.SearchStuInf;

@WebServlet("/searchUserInfo")
public class SearchUserInfoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			HttpSession se = request.getSession();
			ServletContext application = se.getServletContext();
			String sid = (String) application.getAttribute("seesionid");
			String cookie = request.getHeader("Cookie");
			String ip = request.getRemoteAddr();  
			String[] session = cookie.split("=");
			String JSESSIONID = session[1];
			if (JSESSIONID.equals(sid)) {
				String content = request.getParameter("content");
				String user = request.getParameter("user");
				SearchStuInf info = new SearchStuInf();
				JSONObject stuinfo;
				stuinfo = info.searchUserOnline(content, user,ip);
				if (stuinfo != null) {
					out.print(stuinfo);
				}

			} else {
				System.out.println("sessionid为空或session不正确");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
