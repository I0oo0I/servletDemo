package servlet.listener.onlineUser;

import java.util.Enumeration;
import java.util.Vector;

public class UserList {

	private static final UserList userList = new UserList();
	
	private Vector<String> v;
	
	/**
	 * 单例模式，将构造方法设置成private，别人就不可以随意生成UserList对象了，只能自己内部使用
	 * userList = new UserList(); 初始化一次
	 * 为什么是单例，因为统计在线应该是公共的, 对所有的页面都应该是同一个，不论哪个类访问它，都访问的
	 * 是同一个
	 */
	private UserList() {
		v = new Vector<String>();
	}
	
	public static UserList getInstance() {
		return userList;
	}
	
	public void addUser(String name) {
		if(null != name) {
			v.addElement(name);
		}
	}
	
	public void removeUser(String name) {
		if(null != name) {
			v.remove(name);
		}
	}
	
	public Enumeration<String> getUserList(){
		return v.elements();
	}
	
	public int getUserCount() {
		return v.size();
	}
}
