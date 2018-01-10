package servlet.listener.onlineUser;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9100556299218832578L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		session.invalidate();
		
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>退出登录</title></head><body>");
		out.println(user.getName() + ", 你已经退出了登录");
		out.println("<a href=login.html>重新登录</a>");
		out.println("</bdoy></html>");
		out.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
