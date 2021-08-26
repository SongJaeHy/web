<%@page import="kr.co.ictedu.UsersVO"%>
<%@page import="kr.co.ictedu.UsersDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1 세션을 통해 아이디를 가져옵니다.
	//	 만약 로그인 상태가 아니면 로그인창으로 리다이렉트합니다.
 	String sessionId = (String)session.getAttribute("i_s");
	if(sessionId == null){
 		response.sendRedirect("user_login_form.jsp");
	}
	// 2. dao를 통해 UsersVO를 가지고 와야 합니다.
	UsersDAO dao = UsersDAO.getInstance();
	UsersVO user = new UsersVO();
	user.setUid(sessionId);
	
	
	// 3. 들고온 UsersVO를 이용해 아래 html태그의 value속성에 표현식을 이용해
	//		UsersVO의 아이디, 이름, 이메일을 기입하게 만들어줍니다.
	UsersVO resultData = dao.getUserInfo(user);
	System.out.println("DB에서 가져온 데이터 :"+ resultData);
	
	// resultData내부의 데이터가 null인 경우는 조회가 실패한 경우이므로 로그인창으로 돌아가기
	if(resultData.getUid() == null){
		session.invalidate();
		response.sendRedirect("user_login_form.jsp");
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%= resultData.getUid() %> 계정 정보 수정</h1>

	<form action="user_update_ok.jsp" method="post">
	<input type="text" name="uid" placeholder="아이디" 
	readonly required value="<%= resultData.getUid() %>"><br/>
	<input type="password" name="upw" placeholder="비밀번호" required><br/>
	<input type="text" name="uname" 
	placeholder="이름" value="<%= resultData.getUname() %>" required><br/>
	<input type="text" name="email" 
	placeholder="이메일" value="<%= resultData.getEmail() %>"><br/>
	<input type="submit" value="계정정보수정하기">
	
	
	</form>
</body>
</html>