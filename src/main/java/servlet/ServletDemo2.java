package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletDemo2 extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1390989400916784132L;
	
	private String greeting;

	@Override
	public void init() throws ServletException {
		greeting = getInitParameter("greeting");
		System.out.println("���Ǵ�web.xml�ж�ȡ�ĳ�ʼ������ֵ��"+greeting);
		System.out.println("_______________________��ʼ��ServletDemo2_______________________");
	}
	
	@Override
	public void destroy() {
		System.out.println("_______________________����ServletDemo2_______________________");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		if(method.equalsIgnoreCase("get")) {
			doGet(req, resp);
		}else if(method.equalsIgnoreCase("post")) {
			doPost(req, resp);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getHeader("Accept-Language"));	// zh-CN,zh;q=0.8
		System.out.println(req.getRequestURI());				// /servletDemo/hi
		System.out.println(req.getRequestURL().toString());		// http://localhost:8080/servletDemo/hi
		System.out.println(req.getServletPath());				// /hi, web.xml�����õ�
		System.out.println("_______________________get����_______________________");
		
		resp.setContentType("text/html;charset=gb2312");
		
		PrintWriter out = resp.getWriter();
		
		Enumeration<String> headNames = req.getHeaderNames();
		
		out.println("<html><head>");
		out.println("<title>Info Page</title>");
		out.println("</head>");
		out.println("<body><center>");
		
		out.println("<table border=1 align=center>");
		out.println("<caption>Servlet ���ܵ���HTTP��Ϣ��ͷ����Ϣ</caption>");
		out.println("<tr bgcolor=#999999>");
		out.println("<th>��Ϣ��ͷ������</th>");
		out.println("</tr>");
		
		while(headNames.hasMoreElements()) {
			String name = headNames.nextElement();
			String value = req.getHeader(name);
			out.println("<tr>");
			out.println("<td>" + name + "</td>");
			out.println("<td>" + value + "</td>");
			out.println("</tr>");
		}
		
		out.println("</table><p />");
		out.println("<table border=1 align=center>");
		out.println("<caption>����������Ϣ</caption>");
		
		out.println("<tr>");
		out.println("<td>�ͻ��˵�IP��ַ</td>");
		out.println("<td>" + req.getRemoteAddr() + "</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>�ͻ��˵Ķ˿ں�</td>");
		out.println("<td>" + req.getRemotePort() + "</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>����˵�IP��ַ</td>");
		out.println("<td>" + req.getLocalAddr() + "</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>����˵Ķ˿ں�</td>");
		out.println("<td>" + req.getLocalPort() + "</td>");
		out.println("</tr>");
		
		out.println("</table>");
		
		out.println("</center></body></html>");
		out.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
