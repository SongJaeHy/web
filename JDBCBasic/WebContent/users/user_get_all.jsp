<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.ictedu.UsersVO"%>
<%@page import="kr.co.ictedu.UsersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	// 0. 로그인하지 않은 사용자 처리
	String sessionId = (String)session.getAttribute("i_s");
	if(sessionId==null){
		response.sendRedirect("user_login_form.jsp");
	}

	// DB에서 전체 회원 데이터를 들고 나옵니다.
	// 1. getAllUser()메서드를 dao를 이용해 호출합니다.
	//	  호출시 리턴되는 ArrayList를 변수에 담아주세요.
	UsersDAO dao = UsersDAO.getInstance();
	ArrayList<UsersVO> userList = dao.getAllUser();
	// 디버깅 결과 DB내 전체 데이터가 출력되면 성공
	System.out.println(userList);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전체 회원 목록</h1>

	<table border="1">
		<thead>
			<tr>
				<th>회원 아이디</th>
				<th>회원 이름</th>
				<th>회원 이메일</th>
			</tr>
		</thead>
		<tbody>
		<%-- userList는 VO의 집합이고, 여기서
		UsersVO를 하나하나 순차적으로 뽑아서 출력해야 하므로
		향상된 for문을 사용합니다. --%>
		<%-- 
		<% for(UsersVO user: userList){ %>
			<tr>
				<td><%=user.getUid() %></td>
				<td><%=user.getUname() %></td>
				<td><%=user.getEmail() %></td>
			</tr>
		<% } %>
		--%>
		<%-- JSTL 적용 버전 
		forEach 구문은 반복문이고, Items에 향상된 for문의 우측요소
		그리소 var에 왼쪽 요소를 집어넣어주면 됩니다.--%>
			<c:forEach var="user" items="<%= userList%>">
				<tr>
					<td>${user.uid }</td>
					<td>${user.uname }</td>
					<td>${user.email }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>