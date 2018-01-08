package servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GreetServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2818596859186449500L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		String user = (String) session.getAttribute("user");
		
		Cookie [] cookies = req.getCookies();
		if(null != cookies && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				System.out.println(cookie.getComment());
				System.out.println(cookie.getDomain());
				System.out.println(cookie.getMaxAge());
				System.out.println(cookie.getName());
				System.out.println(cookie.getPath());
				System.out.println(cookie.getSecure());
				System.out.println(cookie.getValue());
				System.out.println(cookie.getVersion());
				System.out.println(cookie.getClass());
			}
			
		}
		
		if(null == user) {
			//resp.sendRedirect("sessionLogin");
			//µ±ä¯ÀÀÆ÷½ûÓÃcookieÊ±
			resp.sendRedirect(resp.encodeRedirectURL("sessionLogin"));
		}else {
			resp.setContentType("text/html;charset=gb2312");
			PrintWriter out = resp.getWriter();
			
			out.println("<html<head><title>»¶Ó­Ò³Ãæ</title></head>");
			out.println("<body>");
			
			OutPutSessionInfo.printSessionInfo(out, session);
			
			out.println("<p>");
			out.println("»¶Ó­Äã," + user+ "</p>");
			out.println("<a href="+resp.encodeURL("sessionLogin")+">ÖØÐÂµÇÂ¼</a>");
			out.println("<a href="+resp.encodeURL("sessionLogout")+">×¢Ïú</a>");
			out.println("</body></html>");
			out.close();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
