package servlet.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class DBServlet2 extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5582179702496207810L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		ResultSetMetaData rsd = null;
			
		try {
			//使用了 MyServletContextListener 监听，初始化数据库
			DataSource ds = (DataSource)getServletContext().getAttribute("dataSource");
//			Context ctx = new InitialContext();
//			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/bookstore");
			conn = ds.getConnection();
			stm = conn.createStatement();
			rs = stm.executeQuery("select * from bookinfo");
			if(null != rs) {
				rsd = rs.getMetaData();
				for(int i= 1; i <= rsd.getColumnCount(); i++) {
					System.out.print(rsd.getColumnName(i));
					System.out.print("\t\t");
				}
				
				while(rs.next()) {
					System.out.println();
					for(int i = 1; i <= rsd.getColumnCount(); i++) {
						System.out.print(rs.getString(i));
						System.out.print("\t\t");
					}
				}
			}
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
