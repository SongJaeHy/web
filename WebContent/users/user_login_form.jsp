<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>로그인 창</h1>
	<form action="/MyFirstWeb/login.do" method="post">
		<input type="text" name="uId" placeholder="ID"><br/>
		<input type="password" name="uPw" placeholder="PW"><br/>
		<input type="submit" value="로그인">
				
	</form>
		<a href="/MyFirstWeb/users/users_join_form.jsp"><input type="button" value="회원가입"></a><br/>
</body>
</html>