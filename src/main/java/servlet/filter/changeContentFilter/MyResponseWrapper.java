package servlet.filter.changeContentFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 重写 getWriter和 getOutPutStream方法，用构建在 ByteArrayOutputStream 之上的
 * PrintWriter 对象和 ServletOutputStream对象替换web容器创建的PrintWriter和
 * ServletOutputStream对象
 * @author Administrator
 *
 */
public class MyResponseWrapper extends HttpServletResponseWrapper{

	private ByteArrayOutputStream baos;
	
	private ByteArrayServletOutputStream basos;
	
	private PrintWriter pw;
	
	public MyResponseWrapper(HttpServletResponse response) {
		super(response);
		//创建ByteArrayOutputStream
		baos = new ByteArrayOutputStream();
		
		//使用ByteOutputStream对象作为参数
		//构造ByteArrayServeltOutputStream（baos）;
		basos = new ByteArrayServletOutputStream(baos);
		
		//用 ByteArrayOutputStream 对象构造PrintWriter对象
		pw = new PrintWriter(baos);
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return pw;
	}
	
	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return basos;
	}
	
	public byte[] toByteArray() {
		return baos.toByteArray();
	}
}
