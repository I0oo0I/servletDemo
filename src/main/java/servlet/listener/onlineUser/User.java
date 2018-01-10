package servlet.listener.onlineUser;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * User实现了监听接口，当User对象加入session时，就会触发绑定的方法
 * @author Administrator
 *
 */
public class User implements HttpSessionBindingListener{

	private String name;
	
	private UserList ul = UserList.getInstance();

	public User() {
		
	}
	
	public User(String name){
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println(this.name + "触发session绑定事件");
		ul.addUser(name);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println(this.name + "触发session移除事件");
		ul.removeUser(name);
	}
	
}
