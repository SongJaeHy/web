<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>수정창</h1>
	<form action="http://localhost:8181/MyFirstWeb/boardupdateok.do" method="post">
	
		<!--  hidden 태그를 이용해 나머지 요소들도 다 첨부해주세요. -->
		<input type="hidden" value="${board.bId }" name="bId" />
		<input type="hidden" value="${board.bHit }" name="bHit" />
		<input type="hidden" value="${board.bDate }" name="bDate" />
		<input type="hidden" value="${board.bName }" name="bName" />
		
		<table border ="1">
			<tr>
				<td>글 번호</td>
				<td>${board.bId }</td>
				<td>조회수</td>
				<td>${board.bHit }</td>
			</tr>
			<tr>
				<td>글 쓴 날짜</td>
				<td>${board.bDate }</td>
			</tr>
			<tr>
				<td>글 제목</td>
				<td><input type="text" name="title" value="${board.bTitle }"></td>
			</tr>
			<tr>
				<td>본문</td>
				<td><textarea cols="50" rows="10" name="content">${board.bContent}</textarea></td>
			</tr>
			<tr>
				<td>글쓴이</td>
				<td>${board.bName }</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="수정하기" />
					<input type="reset" value="초기화" />
					<a href="/MyFirstWeb/boardselect.do">
						<input type="button" value="리스트로">
					</a>
				</td>
		</table>
	
	</form>
</body>
</html>