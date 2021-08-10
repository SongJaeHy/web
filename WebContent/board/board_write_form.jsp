<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글쓰기 창</h1>
	<form action="/MyFirstWeb/boardwrite.do" method="post">
		<table border ="1">
			<tr>
				<td>글 제목</td>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>본문</td>
				<td><textarea cols="50" rows="10" name="content"></textarea></td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<td>
				<input type="submit" value="글쓰기">
				<input type="reset" value="초기화">
				</td>
			</tr>	
		</table>
</form>
</body>
</html>