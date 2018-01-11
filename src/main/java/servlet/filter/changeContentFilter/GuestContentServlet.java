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
		out.println("<html><head><title>����������԰�</title></head>");
		out.println("<body><a href=view/say.html>��Ҫ����</a>");
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
										ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from guestbook order by gst_time desc");
			//�ƶ��α굽����������һ��
			rs.last();
			//�õ���ǰ�е�������Ҳ�͵õ����ݿ������Ե�����
			int rowCount = rs.getRow();
			if(rowCount == 0) {
				out.println("��ǰû���κε����ԣ�");
				out.flush(); //���Ҫ�ӣ���Ȼ MyResponseWrapper toByteArray �޷��õ�������������
				//out.close(); �Ὣ�ڴ��е�����д���������ڹ������� �ȵ����� respWrapper.toByteArray()�����û�� out.flush()�����޷���ȡ����
				return;
			}else {
				out.println("����<strong>"+rowCount+"</strong>������");
			}
			
			String strCurPage = req.getParameter("page");
			//��ʾ��ǰҳ
			int curPage ;
			if(strCurPage == null) {
				curPage = 1;
			}else {
				curPage = Integer.parseInt(strCurPage);
			}
			
			//����ÿҳ��ʾ��������
			int countPerPage = 5;
			//������ʾ����������Ҫ����ҳ��
			int pageCount = (rowCount+(countPerPage-1))/countPerPage;
			
			//�ƶ��α����������ָ�����У������ʾ���ǵ�һҳ��curPage=1��
			//�α��ƶ�����һ��
			rs.absolute((curPage-1)*countPerPage+1);
			
			if(curPage == 1) {
				out.print("��һҳ&nbsp;&nbsp;&nbsp;&nbsp;");
				out.print("��һҳ&nbsp;&nbsp;&nbsp;&nbsp;");
			}else {
				out.print("<a href=guestContent?page=1>��һҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;");
				out.print("<a href=guestContent?page="+(curPage-1) + ">��һҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			
			if(curPage == pageCount) {
				out.print("��һҳ&nbsp;&nbsp;&nbsp;&nbsp;");
				out.print("���ҳ&nbsp;&nbsp;&nbsp;&nbsp;");
			}else {
				out.print("<a href=guestContent?page="+(curPage+1) + ">��һҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;");
				out.print("<a href=guestContent?page=" + pageCount +">���ҳ</a>&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			
			int i = 0;
			
			//��ѭ���ķ�ʽȡ��ÿҳҪ��ʾ������
			while(i < countPerPage && !rs.isAfterLast()){
				out.println("<hr color=\"blue\" size=\"2\"><br>");
				out.println("�û�����" + rs.getString("gst_user"));
				out.println("&nbsp;&nbsp;");
				
				Date time = rs.getDate("gst_time");
				out.println("����ʱ�䣺" + time);
				out.println("&nbsp;&nbsp;");
				out.println("�û�IP��" + rs.getString("gst_ip") + "<br>");
				
				out.println("���⣺" + rs.getString("gst_title") + "<br>");
				out.println("���ݣ�" + rs.getString("gst_content") );
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
