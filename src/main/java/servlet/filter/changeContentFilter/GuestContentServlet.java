package servlet.filter.changeContentFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class GuestContentServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8017915181440581977L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DataSource ds = (DataSource)req.getServletContext().getAttribute("dataSource");
		Connection conn;
		Statement stmt;
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println("<html><head><title>网上书店留言板</title></head>");
		out.println("<body><a href=view/say.html>我要留言</a>");
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from guestbook order by gst_time desc");
			//移动游标到结果集的最后一行
			rs.last();
			//得到当前行的行数，也就得到数据库中留言的总数
			int rowCount = rs.getRow();
			if(rowCount == 0) {
				out.println("当前没有任何的留言！");
				out.flush(); //这个要加，不然 MyResponseWrapper toByteArray 无法得到缓冲区的内容
				//out.close(); 会将内存中的数据写出，但是在过滤器中 先调用了 respWrapper.toByteArray()，如果没有 out.flush()，将无法获取数据
				return;
			}else {
				out.println("共有<strong>"+rowCount+"</strong>条留言");
			}
			
			String strCurPage = req.getParameter("page");
			//表示当前页
			int curPage ;
			if(strCurPage == null) {
				curPage = 1;
			}else {
				curPage = Integer.parseInt(strCurPage);
			}
			
			//定义每页显示的留言数
			int countPerPage = 5;
			//计算显示所有留言需要的总页数
			int pageCount = (rowCount+(countPerPage-1))/countPerPage;
			
			//移动游标至结果集中指定的行，如果显示的是第一页，curPage=1，
			//游标移动到第一行
			rs.absolute((curPage-1)*countPerPage+1);
			
			if(curPage == 1) {
				out.print("第一页&nbsp;&nbsp;&nbsp;&nbsp;");
				out.print("上一页&nbsp;&nbsp;&nbsp;&nbsp;");
			}else {
				out.print("<a href=guestContent?page=1>第一页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
				out.print("<a href=guestContent?page="+(curPage-1) + ">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			
			if(curPage == pageCount) {
				out.print("下一页&nbsp;&nbsp;&nbsp;&nbsp;");
				out.print("最后页&nbsp;&nbsp;&nbsp;&nbsp;");
			}else {
				out.print("<a href=guestContent?page="+(curPage+1) + ">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
				out.print("<a href=guestContent?page=" + pageCount +">最后页</a>&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			
			int i = 0;
			
			//以循环的方式取出每页要显示的数据
			while(i < countPerPage && !rs.isAfterLast()){
				out.println("<hr color=\"blue\" size=\"2\"><br>");
				out.println("用户名：" + rs.getString("gst_user"));
				out.println("&nbsp;&nbsp;");
				
				Date time = rs.getDate("gst_time");
				out.println("留言时间：" + time);
				out.println("&nbsp;&nbsp;");
				out.println("用户IP：" + rs.getString("gst_ip") + "<br>");
				
				out.println("主题：" + rs.getString("gst_title") + "<br>");
				out.println("内容：" + rs.getString("gst_content") );
				i ++;
				rs.next();
			}
			out.flush();
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
