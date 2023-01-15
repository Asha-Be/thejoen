<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "oracle.*"
    %>

    
    <%
    request.setCharacterEncoding("utf-8");
   
    %>
    
    <jsp:useBean id="dto" class = "oracle.BoardDTO">
    	<jsp:setProperty name = "dto" property = "*"/>
    
    </jsp:useBean>
    
    <%
    
    String ip = request.getRemoteAddr();//ip
    dto.setIp(ip);
    
    BoardDAO dao = BoardDAO.getInstance();//객체보기, 객체 생성
    dao.insertBoard(dto); //dao 메서드 호출
    
    response.sendRedirect("list.jsp");
   %>