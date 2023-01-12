package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import service.WeChatUserService;

@WebServlet("/ChangKey")
public class ChangKeyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String user_number = request.getParameter("user_number");
		String password = request.getParameter("password");
		String newpassword = request.getParameter("newpassword");
		if(user_number != null && password != null && newpassword!=null &&
				!user_number.equals("") && !password.equals("") && !newpassword.equals("")) {
			WeChatUserService service = new WeChatUserService();
			JSONObject rs = service.Changpwd(newpassword, user_number, password);
			out.print(rs);
		}else {
			out.print("{\"msg\":\"密码不能为空\"}");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
