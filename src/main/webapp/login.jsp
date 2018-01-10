<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="login.jsp?action=login">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type=text name="name"></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>
					<input type="hidden" name="origin_uri" value="${requestScope.origin_uri}">
					<input type="submit" value="登录">
				</td>
				<td>
					<input type="reset" value="重置">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>