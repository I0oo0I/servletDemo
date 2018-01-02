package servlet;

import java.io.IOException;

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
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("_______________________post����_______________________");
	}
}
