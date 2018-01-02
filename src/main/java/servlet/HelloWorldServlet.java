package servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorldServlet implements Servlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("_______________________��ʼ��HelloWorldServlet_______________________");
	}

	@Override
	public ServletConfig getServletConfig() {
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("_______________________doService_______________________");
		String path = req.getServletContext().getRealPath("/");
		System.out.println(path);
	}

	@Override
	public String getServletInfo() {
		return null;
	}

	@Override
	public void destroy() {
		System.out.println("_______________________����HelloWorldServlet_______________________");
	}

}
