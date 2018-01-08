package servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5690025277756954869L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=gb2312");
		
		HttpSession session = req.getSession();
		String user = (String)session.getAttribute("user");
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
		
		out.println("<head><title>��¼ҳ��</title></head>");
		out.println("<body>");
		
		OutPutSessionInfo.printSessionInfo(out, session);
		
		out.println("<p>");
		//out.println("<form method=post action=loginchk>");
		//�����������cookieʱ��ʹ���������дURL
		out.println("<form method=post action="+resp.encodeURL("loginchk")+">");
		out.println("<table>");
		
		out.println("<tr>");
		out.print("<td>�������û���</td>");
		
		if(null==user) {
			out.println("<td><input type=text name=user></td>");
		}else {
			out.println("<td><input type=text name=user value="+user+"></td>");
		}
		
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>����������:</td>");
		out.println("<td><input type=password name=password></td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td><input type=submit value=��¼></td>");
		out.println("<td><input type=reset value=����></td>");
		out.println("</tr>");
		
		out.println("</table>");
		
		out.println("</form");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
