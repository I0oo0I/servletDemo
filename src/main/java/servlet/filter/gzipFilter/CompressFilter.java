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
		System.out.println("初始化压缩过滤");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String acceptEncodingd = req.getHeader("Accept-Encoding");
		//判断浏览器支不支持压缩页面
		if(null != acceptEncodingd && acceptEncodingd.indexOf("gzip") > -1) {
			CompressResponseWrapper respWrapper = new CompressResponseWrapper(resp);
			//回复浏览器时，设置Content-Encoding实体报头，告诉浏览器正文使用了gzip压缩
			respWrapper.setHeader("Content-Encoding", "gzip");
			respWrapper.setContentType("text/html;charset=gb2312");
			chain.doFilter(req, respWrapper);
			
			//respWrapper在 Servlet中获取到响应数据之后，使用getZIPOutputStream获取到输出流
			GZIPOutputStream gzipos = respWrapper.getZIPOutputStream();
			//将内存中的数据写入输出流，无需关闭输出流
			gzipos.finish();
		}else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
		
	}

}
