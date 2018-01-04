package servlet.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DBServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5582179702496207810L;
	
	private String url;
	
	private String user;
	
	private String password;
	
	@Override
	public void init() throws ServletException {
		String driverClass = getInitParameter("driverClass");
		url = getInitParameter("url");
		user = getInitParameter("user");
		password = getInitParameter("password");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			throw new ServletException("加载数据驱动失败");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		Statement stm = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			stm = conn.createStatement();
			stm.addBatch("insert into bookinfo values('1', 'Java', '小红', '电子工业出版社', '2016-05-24', 99, 100, null)");
			stm.addBatch("insert into bookinfo values('2', 'C++', '小红', '电子工业出版社', '2016-05-24', 99, 100, null)");
			stm.addBatch("insert into bookinfo values('3', 'PHP', '小红', '电子工业出版社', '2016-05-24', 99, 100, null)");
			stm.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("连接数据库失败");
		} finally {
			try {
				if(null != stm) {
					stm.close();
					stm = null;
				}
				if(null != conn) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
	}

}
