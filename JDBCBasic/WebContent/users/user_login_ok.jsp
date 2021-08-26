<%@page import="kr.co.ictedu.UsersVO"%>
<%@page import="kr.co.ictedu.UsersDAO"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
  	// 0. post방식 받아오기 처리
  	response.setCharacterEncoding("utf-8");
	request.setCharacterEncoding("utf-8");  	

  	// 1. 아이디 비밀번호 받아오기
 	String id = request.getParameter("uid");
  	String pw = request.getParameter("upw");
  	
  // if~else문으로 try~catch~finally를 감싸서
  // 세션이 존재할 때는 DB에서 데이터를 가져오는 로직을 생략해주세요.
  	String idSession = (String)session.getAttribute("i_s");
  	if(idSession != null){
  		id = idSession;
  	} else{
  		// 1. dao 생성
  		UsersDAO dao = UsersDAO.getInstance();
  		
  		// 2. dao로 로그인 검사
		UsersVO user = new UsersVO();
		user.setUid(id);
		user.setUpw(pw);
		
		int loginResultNum = dao.userLogin(user);
		
		if(loginResultNum == 1){
			session.setAttribute("i_s", id);
			session.setAttribute("p_s", pw);
		} else if(loginResultNum == 0){
			response.sendRedirect("user_login_form.jsp");
		}
  		
  	}

		  
  
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2><%=id %>님 환영합니다.</h2>
	<h1>로그인이 완료되었습니다.</h1>
	<a href="user_logout.jsp">로그아웃하기</a><br/>
	<a href="user_delete_form.jsp">회원탈퇴하기</a><br/>
	<a href="user_update_form.jsp">회원수정하기</a><br/>
	<a href="user_get_all.jsp">회원 목록 보기</a>
</body>
</html>