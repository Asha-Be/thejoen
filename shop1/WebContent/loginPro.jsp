<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- loginPro.jsp

 -->
 
<%
String memId=request.getParameter("memId");
String pw=request.getParameter("pw");

if(memId != null || memId.length()>0){
	//로그인이되엇음!
	session.setAttribute("memId", memId); //**************
	response.sendRedirect("index.jsp");
}

%>