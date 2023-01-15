<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "member.*"
    %>
<%-- confirm.jsp --%>

<%
//ajax로 넘겨준 메서드 받기

String id = request.getParameter("id");
MemberDAO dao = MemberDAO.getInstance(); //객체얻기
int x = dao.confirmID(id); //dao메서드호출
%>

{
"x":<%=x%>
}
