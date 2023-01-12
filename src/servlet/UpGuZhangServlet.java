package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import service.GuZhangService;

@WebServlet("/UpGuZhang")

public class UpGuZhangServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String student_name = request.getParameter("student_name");
		String student_id = request.getParameter("student_id");
		String student_room = request.getParameter("student_room");
		String student_error = request.getParameter("student_error");
		if(student_name==null || student_id==null || student_room==null || student_error==null || student_name.equals("") 
	|| student_id.equals("") || student_room.equals("") || student_error.equals("")) {
			out.print("{\"msg\":\"数据不能为空\"}");	
		}else if (student_room.indexOf("#")==-1) {
			out.print("{\"msg\":\"宿舍格式有误\"}");
		}
		else {
			GuZhangService service = new GuZhangService();
			JSONObject msg = service.UpGuZhang(student_name, student_id, student_room, student_error);
			out.print(msg);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
