package servlet.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TradeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3606033923186984924L;
	
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
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		resp.setContentType("text/html;charset=gb2312");
		PrintWriter out = resp.getWriter();
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false); //关闭自动提交
			//开启事务，这个是禁止脏读和重复读取
			conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select price, amount from bookinfo where id = '3'");
			
			if(null != rs) {
				rs.next();
				float price = rs.getFloat(1);
				int amount = rs.getInt(2);
				
				int num = Integer.parseInt(req.getParameter("amount"));
				if(amount > 0 && amount >= num) {
					pstmt = conn.prepareStatement("update bookinfo set amount= ? "
							+ "where id = '3'");
					
					pstmt.setInt(1, amount - num);
					pstmt.executeUpdate();
					
					double totalPrice = 1000;
					if(totalPrice >= (num * price)) {
						conn.commit();
					}else {
						conn.rollback();
						out.println("你的余额不足");
						out.close();
						return;
					}
				} else {
					out.println("库存不足，该买失败");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != pstmt) {
					pstmt.close();
				}
				if(null != stmt) {
					stmt.close();
				}
				if(null != conn) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
