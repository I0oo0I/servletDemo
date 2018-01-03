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
		out.println("<title>��¼ҳ��</title>");
		out.println("</head><body>");
		
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		if("zhangsan".equals(name) && "123456".equals(password)) {
			ServletContext context = getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher("/hi");
			rd.forward(req, resp);			//ת������Ҳ��ԭ������ķ�ʽҲת���ˣ����ԭ������post��forward����Ҳ��post
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("login2");
			rd.include(req, resp);		   //include����ת��ԭ����һЩ��Ϣ�ᱣ������<title>��¼ҳ��</title>���ᱣ��������forward��ԭ���Ļ����
		}
		
		out.println("</body></html>");
		out.close();
		System.out.println("___________________________login����");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
