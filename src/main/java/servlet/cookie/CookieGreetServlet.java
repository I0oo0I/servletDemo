package servlet.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieGreetServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5834614225998735924L;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		if(null != cookies && cookies.length > 0) {
			String name = null;
			String pwd = null;
			
			a:for(int i = 0; i<cookies.length; i++) {
				Cookie cookie = cookies[i];
				String cName = cookie.getName();
				if("userInfo".equals(cName)) {
					String cValue = cookie.getValue();
					if(null != cValue) {
						String [] values = cValue.split("&");
						if(null != values && values.length > 0) {
							for(String value : values) {
								String [] v = value.split("-");
								if("username".equals(v[0])) {
									name = v[1];
									continue;
								}
								if("password".equals(v[0])) {
									pwd = v[1];
									continue;
								}
								if(null != name && null != pwd) {
									break a;
								}
							}
						}
					}
				}
			}
			
			if("zhangsan".equals(name) && "1234".equals(pwd)) {
				resp.setContentType("text/html;charset=gb2312");
				PrintWriter out = resp.getWriter();
				
				out.println("<html>");
				out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\"");
				out.println("<head><title>»¶Ó­Ò³Ãæ</title></head>");
				out.println("<body>");
				out.println(name +"£¬»¶Ó­Äã");
				out.println("<a href=cookieLogin>ÖØÐÂµÇÂ¼</a>");
				out.println("<body></html>");
				out.close();
				return;
			}
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("cookieLogin");
		rd.forward(req, resp);
	}
}
