package servlet.session;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpSession;

public class OutPutSessionInfo {

	public static void printSessionInfo(PrintWriter out, HttpSession session) {
		out.println("<table>");
		
		out.println("<tr>");
		out.println("<td>�ػ�״̬:</td>");
		if(session.isNew()) {
			out.println("<td>�µĻỰ</td>");
		}else {
			out.println("<td>�ɵĻỰ</td>");
		}
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>�ỰID:</td>");
		out.println("<td>"+session.getId()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>����ʱ��:</td>");
		out.println("<td>" + new Date(session.getCreationTime()) + "</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>�ϴη���ʱ��:</td>");
		out.println("<td>" + new Date(session.getLastAccessedTime()) + "</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>��󲻻ʱ����:</td>");
		out.println("<td>" + session.getMaxInactiveInterval() + "</td>");
		out.println("</tr>");
		
		out.println("</table>");
	}
}
