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
		//��ȡ�������ĳ�ʼ��������resourceName��������ָ����Դ��ϵ�еĻ���
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
		
		//���ؿͻ�����ѡ��Locale����
		Locale locale = request.getLocale();
		//���localeΪnull��������Ӧ��Ӣ�ĵ�Locale����
		if(null == locale) {
			locale = new Locale("en");
		}
		
		ResourceBundle bundle = ResourceBundle.getBundle(resourceName, locale);
		
		HttpSession session = req.getSession();
		
		//����Դ����������Ϊsession���������
		//������ҳ���У�����ֱ�Ӵ�session�����еõ���Դ��
		session.setAttribute("resource", bundle);
		
		//������Ӧ���ĵı��뷽ʽΪutf-8
		resp.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
		
	}

	@Override
	public void destroy() {
		resourceName = null;
	}

}
