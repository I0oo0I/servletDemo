package servlet.filter.changeContentFilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * ��д getWriter�� getOutPutStream�������ù����� ByteArrayOutputStream ֮�ϵ�
 * PrintWriter ����� ServletOutputStream�����滻web����������PrintWriter��
 * ServletOutputStream����
 * @author Administrator
 *
 */
public class MyResponseWrapper extends HttpServletResponseWrapper{

	private ByteArrayOutputStream baos;
	
	private ByteArrayServletOutputStream basos;
	
	private PrintWriter pw;
	
	public MyResponseWrapper(HttpServletResponse response) {
		super(response);
		//����ByteArrayOutputStream
		baos = new ByteArrayOutputStream();
		
		//ʹ��ByteOutputStream������Ϊ����
		//����ByteArrayServeltOutputStream��baos��;
		basos = new ByteArrayServletOutputStream(baos);
		
		//�� ByteArrayOutputStream ������PrintWriter����
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
