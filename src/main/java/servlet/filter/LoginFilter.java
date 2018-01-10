package servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ��¼����
 * @author Administrator
 *
 */
public class LoginFilter implements Filter{

	private static final String LOGIN_URI = "login_uri";
	
	private static final String HOME_URI = "home_uri";
	
	private String login_page;
	
	private String home_page;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//��web.xml�ж�ȡ���õĵ�¼ҳ����ҳ��uri
		login_page = filterConfig.getInitParameter(LOGIN_URI);
		home_page =filterConfig.getInitParameter(HOME_URI);
		
		if(null == login_page || null == home_page) {
			throw new ServletException("û��ָ����¼ҳ�����ҳ");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session  = req.getSession();
		
		//��ȡ�û������uri
		String request_uri = req.getRequestURI();
		//��ȡwebӦ�õ�������·��
		String ctxPath = req.getContextPath();
		//ȥ��������·�����ĵ�ʣ��Ĳ���
		String uri = request_uri.substring(ctxPath.length());
		
		//�жϷ��ʵ��Ƿ��ǵ�¼ҳ��
		if(login_page.equals(uri)) {
			String strLogin = req.getParameter("action");
			//�ж϶����Ƿ��ʵ�¼ҳ�棬�����ύ��¼��Ϣ
			if("login".equals(strLogin)) {
				String name = req.getParameter("name");
				String password = req.getParameter("password");
				
				if("zhangsan".equals(name) && "1234".equals(password)) {
					session.setAttribute("isLogin", true);
					session.setAttribute("user", name);
					
					//����ԭ����Ҫ���ʵ�ҳ�棬��¼֮���������
					String orgin_uri = req.getParameter("origin_uri");
					if(null != orgin_uri && !"".equals(orgin_uri)) {
						resp.sendRedirect(orgin_uri);
					}else {
						resp.sendRedirect(ctxPath + home_page);
					}
					return;
				}else {
					//��֤ʧ��,�ͽ���Ҫ���ʵ�ҳ�棬��������������������¼ҳ�棬�´εĵ�¼��֤��ʱ��
					//�ڴ�����
					String origin_uri = req.getParameter("origin_uri");
					if(null != origin_uri && !"".equals(origin_uri)) {
						req.setAttribute("origin_uri", origin_uri);
					}
					
					resp.setContentType("text/html;charset=utf-8");
					PrintWriter out = resp.getWriter();
					
					out.println("<h2>�û������������������������</h2>");
					RequestDispatcher rd = req.getRequestDispatcher(login_page);
					
					rd.include(req, resp);
					return;
				}
			}else {
				//���ʵ�¼ҳ��
				chain.doFilter(request, response);
				return;
			}
		}else {
			//���ʵĲ��ǵ�¼ҳ�棬��Ҫ�ж��û���û�е�¼
			Boolean isLogin = (Boolean) session.getAttribute("isLogin");
			if(null != isLogin && true == isLogin) {
				chain.doFilter(request, response);
				return;
			}else {
				//û�е�¼�����û������uri��Ϊorigin_uri������¼ҳ�棬������¼�ɹ�֮�󣬼����������uri
				//����
				String strQuery = req.getQueryString();
				if(null != strQuery) {
					request_uri = request_uri + "?" + strQuery;
				}
				req.setAttribute("origin_uri", request_uri);
				RequestDispatcher rd = req.getRequestDispatcher(login_page);
				rd.forward(req, resp);
				return;
			}
		}
	}

	@Override
	public void destroy() {
		
	}

}
