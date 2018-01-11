package servlet.filter.gzipFilter;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

public class GZIPServletOutputStream extends ServletOutputStream{

	private GZIPOutputStream gzipos;
	
	public GZIPServletOutputStream(ServletOutputStream sos) throws IOException {
		//ʹ����Ӧ�����������GZIPOutputStream����������
		this.gzipos = new GZIPOutputStream(sos);
	}
	
	@Override
	public boolean isReady() {
		return false;
	}

	@Override
	public void setWriteListener(WriteListener writeListener) {
		
	}

	@Override
	public void write(int b) throws IOException {
		gzipos.write(b);
	}
	
	public GZIPOutputStream getGZIPOutputStream() {
		return gzipos;
	}

}
