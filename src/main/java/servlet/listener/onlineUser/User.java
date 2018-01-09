package servlet.listener.onlineUser;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Userʵ���˼����ӿڣ���User�������sessionʱ���ͻᴥ���󶨵ķ���
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
		ul.addUser(name);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		ul.removeUser(name);
	}
	
}
