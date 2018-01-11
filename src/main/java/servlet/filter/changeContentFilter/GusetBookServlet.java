package servlet.filter.changeContentFilter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class GusetBookServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 999732926914278086L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			DataSource ds = (DataSource)req.getServletContext().getAttribute("dataSource");
			Connection conn = ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("insert into guestbook(gst_user, gst_title, gst_content,"
					+ "gst_time, gst_ip) values(?,?,?,?,?)");
			req.setCharacterEncoding("utf-8");
			String name = req.getParameter("name");
			String title =req.getParameter("title");
			String content = req.getParameter("content");
			Date time = new Date(System.currentTimeMillis());
			String ip = req.getRemoteAddr();
			
			if(null == null && null == title) {
				resp.sendRedirect("view/say.html");
				return;
			}
			
			pstmt.setString(1, name);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			pstmt.setDate(4, time);
			pstmt.setString(5,ip);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			resp.sendRedirect("guestContent");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
