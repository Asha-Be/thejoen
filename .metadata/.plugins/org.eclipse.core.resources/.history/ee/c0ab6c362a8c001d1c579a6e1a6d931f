<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.*"
    %>
<%--loginPro.jsp--%>

<%
request.setCharacterEncoding("utf-8");

String id=request.getParameter("id");
String pw=request.getParameter("pw");

MemberDAO dao=MemberDAO.getInstance();
int x=dao.userCheck(id, pw);

if(x==1){
   session.setAttribute("id",id);
   response.sendRedirect("main.jsp");
}else if(x==0){
   %>
   <script>
   alert("암호틀림");
   history.back();
   </script>
   <%
}else if(x==-1){
   %>
   <script>
   alert("없는 id입니다");
   history.back();
   </script>
<%
}
%>