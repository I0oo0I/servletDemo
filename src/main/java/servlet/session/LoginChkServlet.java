package servlet.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginChkServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4305582667308552241L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("gb2312");
		String name = req.getParameter("user");
		String password = req.getParameter("password");
		
		if(null == name || null == password || name.equals("") || password.equals("")) {
			//resp.sendRedirect("sessionLogin");
			//当浏览器禁用cookie时
			resp.sendRedirect(resp.encodeRedirectURL("sessionLogin"));
			return;
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("user", name);
			//当浏览器禁用cookie时
			//resp.sendRedirect("greet");
			resp.sendRedirect(resp.encodeRedirectURL("greet"));
			return;
		}
	}
}
