<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.*"
    %>
<%--inputPro.jsp --%>

<%
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="dto" class="member.MemberDTO">
   <jsp:setProperty name="dto" property="*"/>
</jsp:useBean>

<%
MemberDAO dao=MemberDAO.getInstance();//객체 얻기
dao.insertMember(dto);//dao 메서드 호출
response.sendRedirect("main.jsp");
%>