package servlet.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieLoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3526761063925281826L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if("chk".equals(action)) {
			String name = req.getParameter("user");
			String pwd = req.getParameter("password");
			if(null != name && null != pwd) {
				if(name.equals("zhangsan") && pwd.equals("1234")) {
					StringBuffer sb = new StringBuffer();
					sb.append("username-");
					sb.append(name);
					sb.append("&password-");
					sb.append(pwd);
					
					Cookie cookie = new Cookie("userInfo", sb.toString());
					cookie.setMaxAge(1800);
					resp.addCookie(cookie);
					
					resp.sendRedirect("cookieGreet");
					return;
				}else {
					resp.setContentType("text/html;charset=gb2312");
					PrintWriter out = resp.getWriter();
					out.println("�û��������������<a href=cookieLogin>���µ�¼</a>");
					return;
				}
			}
		}else {
			resp.setContentType("text/html;charset=gb2312");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\"");
			out.println("<head><title>��¼ҳ��</title></head>");
			out.println("<body>");
			out.println("<p>");
			out.println("<form method=post action=cookieLogin?action=chk>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>�������û���</td>");
			out.println("<td><input type=text name=user></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>����������</td>");
			out.println("<td><input type=password name=password></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td><input type=submit value=��¼></td>");
			out.println("<td><input type=reset value=����></td>");
			out.println("</tr>");
			out.println("</table></form></body></html>");
			out.close();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
