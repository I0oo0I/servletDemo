package common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class StringUtils {

	/**
	 * �������ַ�ת���ɶ�Ӧ��ʵ�����û��ַ�����
	 * @param str
	 * @return
	 */
	public static String toHtml(String str) {
		if(null != str) {
			StringBuffer sb = new StringBuffer();
			int len = str.length();
			for(int i = 0; i < len; i++) {
				char c = str.charAt(i);
				switch (c) {
				case ' ':
					sb.append("&nbsp;");
					break;
				case '\n':
					sb.append("<br>");
					break;
				case '\r':		//�س�
					break;
				case '\'':
					sb.append("&#39;");
					break;
				case '<':
					sb.append("&lt;");
					break;
				case '>':
					sb.append("&gt;");
					break;
				case '&':
					sb.append("&amp;");
					break;
				case '"':
					sb.append("&#34;");
					break;
				case '\\':
					sb.append("&#92");
					break;
				default:
					sb.append(c);
				}
			}
			return sb.toString();
		}
		return null;
	}
	
	/**
	 * �滻content�����ݣ����粻������
	 * @param content
	 * @param hm
	 * @return
	 */
	public static String replaceText(String content, HashMap<String, String> hm) {
		StringBuffer sb = new StringBuffer(content);
		Set<String> keys = hm.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()) {
			String key = (String) it.next();
			int index = sb.indexOf(key);
			while(-1 != index) {
				sb.replace(index, index+key.length(), (String) hm.get(key));
				index = sb.indexOf(key);	//�����ж����Ҫ�滻
			}
		}
		return sb.toString();
	}
}
