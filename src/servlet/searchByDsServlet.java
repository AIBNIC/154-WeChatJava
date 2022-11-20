package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.GuZhangService;

@WebServlet("/searchByDs")
public class searchByDsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ds = request.getQueryString();
		System.out.println("ds"+ds);
		GuZhangService service = new GuZhangService();
		if(ds == null) {
			ArrayList stus=  service.searchall();
		}
		
		ArrayList stus = service.searchByDs(ds);
		System.out.println(stus);
		PrintWriter out = response.getWriter();
		out.print(stus);
		out.flush();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
