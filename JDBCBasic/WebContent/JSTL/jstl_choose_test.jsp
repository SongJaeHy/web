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
	<c:choose>
		<c:when test="${param.score >=90 && param.score <= 100}">
			<p><b>당신의 학점은 A학점입니다.</b></p>
		</c:when>
		<c:when test="${param.score >=80&& param.score <= 100}">
			<p><b>당신의 학점은 B학점입니다.</b></p>
		</c:when>
		<c:when test="${param.score >=70&& param.score <= 100}">
			<p><b>당신의 학점은 C학점입니다.</b></p>
		</c:when>
		<c:when test="${param.score >=60&& param.score <= 100}">
			<p><b>당신의 학점은 D학점입니다.</b></p>
		</c:when>
		<c:otherwise>
			<p><b>당신의 학점은 F학점입니다.</b></p>
		</c:otherwise>
	</c:choose>
	
</body>
</html>