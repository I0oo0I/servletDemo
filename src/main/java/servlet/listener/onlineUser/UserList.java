package servlet.listener.onlineUser;

import java.util.Enumeration;
import java.util.Vector;

public class UserList {

	private static final UserList userList = new UserList();
	
	private Vector<String> v;
	
	/**
	 * ����ģʽ�������췽�����ó�private�����˾Ͳ�������������UserList�����ˣ�ֻ���Լ��ڲ�ʹ��
	 * userList = new UserList(); ��ʼ��һ��
	 * Ϊʲô�ǵ�������Ϊͳ������Ӧ���ǹ�����, �����е�ҳ�涼Ӧ����ͬһ���������ĸ���������������ʵ�
	 * ��ͬһ��
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
