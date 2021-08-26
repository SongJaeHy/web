<%@page import="kr.co.ictedu.UsersDAO"%>
<%@page import="kr.co.ictedu.UsersVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	response.setCharacterEncoding("utf-8");
	request.setCharacterEncoding("utf-8");  
	// 1. 폼에서 던져준 데이터를 받습니다.
	String uid = request.getParameter("uid");
	String upw = request.getParameter("upw");
	String uname = request.getParameter("uname");
	String uemail = request.getParameter("email");
	
	// 2. 던져준 데이터를 VO를 생성해 답습니다.
	UsersVO user = new UsersVO(uid, upw, uname, uemail);

	// 3. dao를 생성해 userUpdate(); 메서드를 호출해줍니다.
	
	UsersDAO dao = UsersDAO.getInstance();
	
	// 4. 업데이트 성공시 1, 실패시 0을 리턴받고
	//	  0을 리턴받았을때는 user_update_fail.jsp로 리다이렉트
	//	  1을 리턴받았을때는 하단 body태그 내에 <계정명> 수정이 완료되었습니다.
	//	  라는 메세지와 다시 로그인폼으로 돌아가는 링크를 띄어줍니다.
	int updateResultNum = dao.userUpdate(user);
	
	if(updateResultNum==0){
		response.sendRedirect("user_update_fail.jsp");
	}
	// 5. user_update_fail.jsp은 body 태그 내에
	//	  수정에 실패했습니다. 다시 시도해주세요.
	//   라는 메세지와 그 아래에 로그인폼으로 돌아가는 링크를 띄워줍니다.
 
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2><%= user.getUid() %>계정의 수정이 완료되었습니다.</h2>
	<a href="user_login_form.jsp">초기화면으로 돌아가기</a>
</body>
</html>