package servlet.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		String jndi = sc.getInitParameter("jndi");
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup(jndi);
			//��ds������ΪServletContext�����ԣ����е�servlet�����Է���
			sc.setAttribute("dataSource", ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
