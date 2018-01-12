package servlet.filter.gzipFilter;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompressFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("��ʼ��ѹ������");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String acceptEncodingd = req.getHeader("Accept-Encoding");
		//�ж������֧��֧��ѹ��ҳ��
		if(null != acceptEncodingd && acceptEncodingd.indexOf("gzip") > -1) {
			CompressResponseWrapper respWrapper = new CompressResponseWrapper(resp);
			//�ظ������ʱ������Content-Encodingʵ�屨ͷ���������������ʹ����gzipѹ��
			respWrapper.setHeader("Content-Encoding", "gzip");
			respWrapper.setContentType("text/html;charset=gb2312");
			chain.doFilter(req, respWrapper);
			
			//respWrapper�� Servlet�л�ȡ����Ӧ����֮��ʹ��getZIPOutputStream��ȡ�������
			GZIPOutputStream gzipos = respWrapper.getZIPOutputStream();
			//���ڴ��е�����д�������������ر������
			gzipos.finish();
		}else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
		
	}

}
