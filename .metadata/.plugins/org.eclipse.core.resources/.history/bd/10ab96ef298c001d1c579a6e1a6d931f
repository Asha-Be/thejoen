<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "mysql.*"
    %>

<%
request.setCharacterEncoding("utf-8");
//get 요청시 한글처리는 Tomcat의 server.snl에서 

int num = Integer.parseInt(request.getParameter("num"));
String pw = request.getParameter("pw");
String pageNum = request.getParameter("pageNum");

BoardDAO dao = BoardDAO.getInstance();
int check = dao.deleteArticle(num, pw); //dao 메서드 호출
%>
<%
if(check ==1){
	response.sendRedirect("list.jsp?pageNum="+pageNum);
}else{
	%>
	alert("암호가 틀립니다.");
	history.back();	
	<%
}//else-end
%>
