<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="mysql.*" %>
    
    <%
    request.setCharacterEncoding("utf-8");
  %>
    
    <jsp:useBean id="dto" class = "mysql.BoardDTO">
    	<jsp:setProperty name="dto" property="*"/>
    </jsp:useBean>
    
    <%
    	String pageNum = request.getParameter("pageNum");
  
    	BoardDAO dao = BoardDAO.getInstance(); //dao 객체얻기
    	int x = dao.updateBoard(dto);
    	
    	if(x==1){
    		 response.sendRedirect("list.jsp?pageNum="+pageNum);
   
    	}else{
    	%>	
    		<script>
    			alert("암호가 틀림");
    			history.back();
    		</script>
    		
    		
    		<%
    	}
    %>