package servlet.filter.gzipFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CompressResponseWrapper extends HttpServletResponseWrapper{

	private GZIPServletOutputStream gzipsos;
	
	private PrintWriter pw;
	
	public CompressResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
		//用响应输出流创建GZIPOutputStream对象
		gzipsos = new GZIPServletOutputStream(response.getOutputStream());
		//用GZIPServletOutputStream对象作为参数，构造PrintWriter对象
		pw = new PrintWriter(gzipsos);
	}
	
	/**
	 * 重新实际长度和压缩后的报头长度不一样
	 */
	@Override
	public void setContentLength(int len) {
		
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return gzipsos;
	}
	
	@Override
	public PrintWriter getWriter() throws IOException {
		return pw;
	}
	
	/**
	 * 过滤器调用这个方法得到GZIPOutputStream对象，以便完成将压缩数据写入输出流的操作
	 * @return
	 */
	public GZIPOutputStream getZIPOutputStream() {
		return gzipsos.getGZIPOutputStream();
	}
}
