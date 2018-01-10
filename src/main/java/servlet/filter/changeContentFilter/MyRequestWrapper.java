package servlet.filter.changeContentFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import common.StringUtils;

/**
 * ʹ�û�ȡ������������޸���Ӧ���޸Ĵ���Ĳ������޸ķ��ص���Ӧ������
 * ΪʲôҪ�� HttpServletRequestWrapper
 * 		HttpServletRequest request.getParameter(name), ���Ի�ȡ������,�����ڹ����������ص����޸��˴���Ĳ���
 * 		���ǣ�HttpServletRequest û���ṩ setParameter�� ��ʹ�ڹ��������޸��ˣ�requestҲû�а취����servlet��
 * Ϊʲô��ֱ��ʹ�� HttpServletRequest
 * 		HttpServletRequest �ǽӿڣ�����Ҳ�������������Ǳ���ʵ��������������ķ����ܶ࣬����ȫ��ʵ�֣����������Ƚ��鷳
 * 
 * HttpServletRequestWrapperʵ���� HttpServletRequest�ӿڣ��ڹ������У����ǻ�ʹ���Զ���� MyRequestWrapper���滻�� HttpServletRequest
 * @author Administrator
 *
 */
public class MyRequestWrapper extends HttpServletRequestWrapper{

	public MyRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	/**
	 * ��дgetParameter�������������ֵ���й��ˡ�
	 */
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if(null != value) {
			return StringUtils.toHtml(value.trim());
		}
		return null;
	}

}
