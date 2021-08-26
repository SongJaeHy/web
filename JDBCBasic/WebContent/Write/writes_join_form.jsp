<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글 제목창입니다.</h1>
	<form action="write_join.jsp" method="post">
		<input type="text" name="id" placeholder="ID"/><br/>
		<input type="text" name="uname" placeholder="글쓴이"/><br/>
		<input type="date" name="date" placeholder="발행연도"/><br/>
		<input type="submit" value="제출">
		
	</form>
</body>
</html>