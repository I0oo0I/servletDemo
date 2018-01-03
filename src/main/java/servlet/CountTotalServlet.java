package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CountTotalServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7493796509628902835L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = req.getServletContext();
		Integer count = 0;
		synchronized (sc) {
			count = (Integer) sc.getAttribute("count");
			if(null == count) {
				count = 1;
			}else {
				count = count + 1;
			}
			sc.setAttribute("count", count);
		}
		
		resp.setContentType("text/html;charset=gb2312");
		
		PrintWriter out = resp.getWriter();
		out.println("<html><head>");
		out.println("<title>页面统计访问</title>");
		out.println("</head><body>");
		out.println("页面被访问了" + "<b>" + count +"</b> 次");
		out.println("</body></html>");
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
