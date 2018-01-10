
package servlet.listener.onlineUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OnlineUserServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8753812011350922662L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("user");
		String password = req.getParameter("password");
		
		if(null == name || null == password || name.equals("") || password.equals("")) {
			resp.sendRedirect("login.html");
		}else {
			HttpSession session  = req.getSession();
			User user = (User) session.getAttribute("user");
			if(null == user || !name.equals(user.getName())) {
				user = new User(name);
				session.setAttribute("user", user);
			}
			
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			
			out.println("欢迎用户<b>" + name + "</b>");
			UserList ul = UserList.getInstance();
			out.println("当前在线用户列表：<br>");
			Enumeration<String> enums = ul.getUserList();
			int i = 0;
			while (enums.hasMoreElements()) {
				out.print(enums.nextElement());
				out.print("&nbsp;&nbsp;&nbsp;&nbsp;");
				if(++i==10) {
					out.print("<br>");
				}
			}
			out.println("<br>当前在线人数：" + i);
			out.println("<p><a href=onlineOut>退出登录</a>");
			out.close();
		}
	}

}
