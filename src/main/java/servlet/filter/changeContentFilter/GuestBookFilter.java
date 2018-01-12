package servlet.filter.changeContentFilter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.StringUtils;

public class GuestBookFilter implements Filter{

	private static final String WORD_FILE = "word_file";
	
	HashMap<String, String> hm = new HashMap<>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("��ʼ����Ӧ���������");
		//��ȡ�����˲����־���滻���ݵ��ļ�
		//���Բ����־���Ϊkey,�滻������Ϊvalue,���浽hashmap������
		String confiPath = filterConfig.getInitParameter(WORD_FILE);
		
		ServletContext sc = filterConfig.getServletContext();
		String filePath = sc.getRealPath(confiPath);
		
		try {
			FileReader fr = new FileReader(filePath); 
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			while(null != (line = br.readLine())) {
				String [] strTemp = line.split("=");
				hm.put(strTemp[0], strTemp[1]);
			}
			br.close();
		} catch (IOException e) {
			throw new ServletException("��ȡ�����ļ���Ϣ����");
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		MyRequestWrapper reqWrapper = new MyRequestWrapper(req);
		MyResponseWrapper respWrapper = new MyResponseWrapper(resp);
		
		chain.doFilter(reqWrapper, respWrapper);
		
		String content = new String(respWrapper.toByteArray());
		String result = StringUtils.replaceText(content, hm);
		resp.setContentType("text/html;charset=gb2312");
		PrintWriter out = resp.getWriter();
		out.println(result);
		out.close();
	}

	@Override
	public void destroy() {
		
	}

}
