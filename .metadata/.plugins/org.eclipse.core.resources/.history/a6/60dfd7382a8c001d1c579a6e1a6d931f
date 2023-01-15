<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.*"
    %>
    <link rel="stylesheet" type="text/css" href="css_list.css">
<%--modify.jsp--%>
<%
String pw=request.getParameter("pw");
MemberDAO dao=MemberDAO.getInstance();
String id=(String)session.getAttribute("id");
int x=dao.pwCheck(id,pw);


%>

<%
if(x==1){
   %>
   <a href="modifyForm.jsp">내정보수정</a>   
   <a href="deleteForm.jsp">탈퇴</a>
   <%
}else if (x==-1){
   %>
   <form method="post" action="modify.jsp">
 암호:<input type="password" name="pw" size="20">
 	<input type = "hidden" name = "id" value = "<%=id%>">
  <input type="submit" value="암호확인">
</form>
   <%
}//else-end
%>

