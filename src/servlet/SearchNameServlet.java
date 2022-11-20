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
@WebServlet("/searchName")
public class SearchNameServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession se = request.getSession();
		ServletContext application = se.getServletContext(); 
		String sid = (String) application.getAttribute("seesionid");
		String cookie=request.getHeader("Cookie");
		String[] session = cookie.split("=");
		String JSESSIONID = session[1];
		if(JSESSIONID.equals(sid)){	
			String info = request.getParameter("info");
			String user = request.getParameter("user");
		}else {
			System.out.println("sessionid为空或session不正确");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
