<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "member.*" %>

<%--
x = 1 삭제
x = -1 pw없음
 --%>
 
 <%
 
 request.setCharacterEncoding("utf-8");
 String id = (String)session.getAttribute("id");//세션에서 id가져오기
 String pw = request.getParameter("pw");//
 
 MemberDAO dao = MemberDAO.getInstance();//객체생성
 int x =dao.deleteMember(id, pw);
 
 if(x==1){
	 //탈퇴
	 session.invalidate(); //세션 끊기
	 response.sendRedirect("main.jsp");
	 
 }else if (x == -1){
	 %>
	 	 
	 	 <script>
	 	 	alert("암호가 틀렸습니다.");
	 	 </script>
	 	 
	 
	 <%
 }
 
 
 %>