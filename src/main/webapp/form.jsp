<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.ResourceBundle" %>
<%
	ResourceBundle bundle = (ResourceBundle) session.getAttribute("resource");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><%=bundle.getString("title") %></title>
</head>
<body>
	<form action="" method="post">
		<%=bundle.getString("name") %><input type="text" name="name"><br>
		<%=bundle.getString("email") %><input type="text" name="email"><br>
		<input type="reset" value="<%=bundle.getString("reset")%>">
		<input type="submit" value="<%=bundle.getString("submit")%>">
	</form>
</body>
</html>