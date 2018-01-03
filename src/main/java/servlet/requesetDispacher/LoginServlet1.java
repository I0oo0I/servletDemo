package servlet.requesetDispacher;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet1 extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8824290213783021756L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=gb2312");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><head>");
		out.println("<title>登录页面</title>");
		out.println("</head><body>");
		
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		if("zhangsan".equals(name) && "123456".equals(password)) {
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/hi");
			rd.forward(req, resp);			//转发请求，也将原来请求的方式也转发了，如果原请求是post，forward方法也是post
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("login2");
			rd.include(req, resp);		   //include的跳转，原来的一些信息会保留，如<title>登录页面</title>，会保留下来，forward的原来的会清空
		}
		
		out.println("</body></html>");
		out.close();
		System.out.println("___________________________login结束");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
