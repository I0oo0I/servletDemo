package servlet.session;

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
	private static final long serialVersionUID = 3260091595391326928L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=gb2312");
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>退出登录</title></head>");
		out.println("<body>");
		out.println("已退出登录<br>");
		out.println("<a href=sessionLogin>重新登录</a>");
		out.println("</body></html>");
		out.close();
	}
}
