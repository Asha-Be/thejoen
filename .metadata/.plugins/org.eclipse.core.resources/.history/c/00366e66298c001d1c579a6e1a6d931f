<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="notice.*"
    %>
<%--deletePro.jsp --%>
 <%
 int no=Integer.parseInt(request.getParameter("no"));
 String pageNum=request.getParameter("pageNum");
 
 NoticeDAO dao=NoticeDAO.getInstance();//dao객체 얻기 
 dao.deleteNotice(no);
 
 response.sendRedirect("list.jsp?pageNum="+pageNum);
 %>