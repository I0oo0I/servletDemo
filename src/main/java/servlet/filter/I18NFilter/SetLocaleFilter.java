package servlet.filter.I18NFilter;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SetLocaleFilter implements Filter{

	private String resourceName = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//获取过滤器的初始化参数，resourceName参数用于指定资源包系列的基名
		resourceName = filterConfig.getInitParameter("resouceName");
		if(null == resourceName) {
			throw new UnavailableException("no define resource");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//返回客户端首选的Locale对象
		Locale locale = request.getLocale();
		//如果locale为null，创建对应于英文的Locale对象
		if(null == locale) {
			locale = new Locale("en");
		}
		
		ResourceBundle bundle = ResourceBundle.getBundle(resourceName, locale);
		
		HttpSession session = req.getSession();
		
		//将资源包对象设置为session对象的属性
		//在其他页面中，可以直接从session对象中得到资源包
		session.setAttribute("resource", bundle);
		
		//设置响应正文的编码方式为utf-8
		resp.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		resourceName = null;
	}

}
