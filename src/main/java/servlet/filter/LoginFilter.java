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
 * 登录过滤
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
		//在web.xml中读取配置的登录页和首页的uri
		login_page = filterConfig.getInitParameter(LOGIN_URI);
		home_page =filterConfig.getInitParameter(HOME_URI);
		
		if(null == login_page || null == home_page) {
			throw new ServletException("没有指定登录页面或主页");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session  = req.getSession();
		
		//获取用户请求的uri
		String request_uri = req.getRequestURI();
		//获取web应用的上下文路径
		String ctxPath = req.getContextPath();
		//去除上下文路径，的到剩余的部分
		String uri = request_uri.substring(ctxPath.length());
		
		//判断访问的是否是登录页面
		if(login_page.equals(uri)) {
			String strLogin = req.getParameter("action");
			//判断动作是访问登录页面，还是提交登录信息
			if("login".equals(strLogin)) {
				String name = req.getParameter("name");
				String password = req.getParameter("password");
				
				if("zhangsan".equals(name) && "1234".equals(password)) {
					session.setAttribute("isLogin", true);
					session.setAttribute("user", name);
					
					//这是原来需要访问的页面，登录之后继续访问
					String orgin_uri = req.getParameter("origin_uri");
					if(null != orgin_uri && !"".equals(orgin_uri)) {
						resp.sendRedirect(orgin_uri);
					}else {
						resp.sendRedirect(ctxPath + home_page);
					}
					return;
				}else {
					//验证失败,就将来要访问的页面，继续保存起来，传到登录页面，下次的登录验证的时候
					//在传上来
					String origin_uri = req.getParameter("origin_uri");
					if(null != origin_uri && !"".equals(origin_uri)) {
						req.setAttribute("origin_uri", origin_uri);
					}
					
					resp.setContentType("text/html;charset=utf-8");
					PrintWriter out = resp.getWriter();
					
					out.println("<h2>用户名或者密码错误，请重新输入</h2>");
					RequestDispatcher rd = req.getRequestDispatcher(login_page);
					
					rd.include(req, resp);
					return;
				}
			}else {
				//访问登录页面
				chain.doFilter(request, response);
				return;
			}
		}else {
			//访问的不是登录页面，则要判断用户有没有登录
			Boolean isLogin = (Boolean) session.getAttribute("isLogin");
			if(null != isLogin && true == isLogin) {
				chain.doFilter(request, response);
				return;
			}else {
				//没有登录，则将用户请求的uri作为origin_uri传到登录页面，这样登录成功之后，继续访问这个uri
				//参数
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
