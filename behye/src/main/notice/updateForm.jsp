<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="notice.*"
    %>
<%--updateForm.jsp --%>
<%
int no=Integer.parseInt(request.getParameter("no"));
String pageNum=request.getParameter("pageNum");

NoticeDAO dao=NoticeDAO.getInstance();//dao객체 얻기 
NoticeDTO dto=dao.getUpdate(no);//dao메서드 호출 

%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	
</head>
<body>
  <h2>공지사항 글 수정</h2>
  <form name="updateForm" method="post" action="updatePro.jsp?pageNum=<%=pageNum%>">
    <table border="1">
    
      <tr>
        <td>작성자</td>
        <td>
          <input type="text" name="writer" id="writer" size="30" value="<%=dto.getWriter()%>">
          <input type="hidden" name="no" value="<%=no%>">
        </td>
      </tr>
      
      <tr>
        <td>글제목</td>
        <td>
        <input type="text" name="title" id="title" value="<%=dto.getTitle()%>" size="50">
        </td>
      </tr>
      
      <tr>
        <td>글내용</td>
        <td>
        <textarea name="content" id="content" rows="10" cols="50"><%=dto.getContent() %></textarea>
        </td>
      </tr>
      
      <tr>
        <td colspan="2" align="center">
          <input type="submit" value="공지사항 글수정">
          <input type="reset" value="취소">
         <input type="button" value="리스트" onClick="location='list.jsp?pageNum=<%=pageNum%>'">
        </td>
      </tr>
      
      
    </table>
  </form>
</body>
</html>