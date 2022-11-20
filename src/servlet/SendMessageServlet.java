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

/**
 * Servlet implementation class fault
 */
@WebServlet("/sendMessage")
public class SendMessageServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession se = request.getSession();
		ServletContext application = se.getServletContext(); 
		String sid = (String) application.getAttribute("seesionid");
		String cookie=request.getHeader("Cookie");
		String[] session = cookie.split("=");
		String JSESSIONID = session[1];
		if(JSESSIONID.equals(sid)){	
			String openid = request.getParameter("openid");
			String fault_ms = request.getParameter("fault_ms");
			String name = request.getParameter("name");
			System.out.println(openid);
			System.out.println(fault_ms);
			System.out.println(name);
		}
		System.out.println("session≤ª∆•≈‰");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
