<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "notice.*"
    %>
    
    <%
    request.setCharacterEncoding("utf-8");
    
    %>
    <jsp:useBean id = "dto" class = "notice.NoticeDTO">
    	<jsp:setProperty name = "dto" property = "*"/>
    </jsp:useBean>
    
    <%
    
    NoticeDAO dao = NoticeDAO.getInstance();
    dao.insertNotice(dto);
    
    response.sendRedirect("list.jsp");
    
    
    
    %>