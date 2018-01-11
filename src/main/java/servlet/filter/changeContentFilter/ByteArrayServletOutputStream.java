package servlet.filter.changeContentFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * 继承 ServletOutputStream ，替换掉  HttpServlet response.getOutputStream() 返回的 
 * ServletOutputStream 对象
 * @author Administrator
 *
 */
public class ByteArrayServletOutputStream extends ServletOutputStream{

	ByteArrayOutputStream baos;
	
	ByteArrayServletOutputStream(ByteArrayOutputStream baos){
		this.baos = baos;
	}

	@Override
	public void setWriteListener(WriteListener writeListener) {
		
	}

	@Override
	public void write(int b) throws IOException {
		baos.write(b);
	}

	@Override
	public boolean isReady() {
		return false;
	}

}
