package servlet;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloWorldServlet implements Servlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
		System.out.println(sc.getMajorVersion());
		System.out.println(sc.getMinorVersion());
		System.out.println(sc.getMimeType("F:\\apache-tomcat-8.5.16-windows-x64.zip"));
		Set<String> rs = sc.getResourcePaths("/");
		if(null != rs && rs.size() > 0) {
			Iterator<String> a = rs.iterator();
			while(a.hasNext()) {
				System.out.println(a.next());
			}
		}
		System.out.println("_______________________≥ı ºªØHelloWorldServlet_______________________");
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
		System.out.println("_______________________Ω· ¯HelloWorldServlet_______________________");
	}

}
