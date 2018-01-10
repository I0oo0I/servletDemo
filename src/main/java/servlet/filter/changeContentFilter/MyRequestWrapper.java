package servlet.filter.changeContentFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import common.StringUtils;

/**
 * 使用获取器拦截请求和修改响应，修改传入的参数，修改返回的响应的内容
 * 为什么要用 HttpServletRequestWrapper
 * 		HttpServletRequest request.getParameter(name), 可以获取到参数,但是在过滤器中拦截到，修改了传入的参数
 * 		但是，HttpServletRequest 没有提供 setParameter， 即使在过滤器中修改了，request也没有办法带到servlet中
 * 为什么不直接使用 HttpServletRequest
 * 		HttpServletRequest 是接口，我们也可以用它，但是必须实现它，但是里面的方法很多，必须全部实现，操作起来比较麻烦
 * 
 * HttpServletRequestWrapper实现了 HttpServletRequest接口，在过滤器中，我们会使用自定义的 MyRequestWrapper，替换到 HttpServletRequest
 * @author Administrator
 *
 */
public class MyRequestWrapper extends HttpServletRequestWrapper{

	public MyRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	/**
	 * 重写getParameter方法，对请求的值进行过滤。
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
