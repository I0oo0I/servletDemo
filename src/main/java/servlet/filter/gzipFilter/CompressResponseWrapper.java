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
		//����Ӧ���������GZIPOutputStream����
		gzipsos = new GZIPServletOutputStream(response.getOutputStream());
		//��GZIPServletOutputStream������Ϊ����������PrintWriter����
		pw = new PrintWriter(gzipsos);
	}
	
	/**
	 * ����ʵ�ʳ��Ⱥ�ѹ����ı�ͷ���Ȳ�һ��
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
	 * ������������������õ�GZIPOutputStream�����Ա���ɽ�ѹ������д��������Ĳ���
	 * @return
	 */
	public GZIPOutputStream getZIPOutputStream() {
		return gzipsos.getGZIPOutputStream();
	}
}
