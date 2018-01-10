package common;

public class StringUtils {

	/**
	 * 将特殊字符转换成对应的实体引用或字符引用
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
				case '\r':		//回车
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
}
