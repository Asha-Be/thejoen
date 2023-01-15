<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%-- writeForm --%>
<html>
<head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>Insert title here</title>
      
      <script src="//code.jquery.com/jquery-3.6.1.min.js" src = "script.js"></script>
      <script type = "text/javascript" src = "script.js"></script>
      <link rel="stylesheet" type="text/css" href="css_list.css">
      
   </head>

<%
int no = 0;
%>

<body>
<%
if(request.getParameter("no") != null){
   //순번이하나뚜없으면 (빈글이면)
   no = Integer.parseInt(request.getParameter("no"));
   
}//if-end

%>
</body>

   <h2>공지 작성</h2>

<form name = "writeForm" method = "post" action = "writePro.jsp" onsubmit = "return check()">
<input type = "hidden" name = "no" value = "<%=no%>"/>
<%--/****/  --%>

<table>
   <tr>
      <td colspan = "2" align = right>
         <a href = "list.jsp">회사 공지사항</a>
      </td>
   </tr>
   
   <tr>
      <td>작성자</td>
      <td><input type = "text" name = "writer" id = "writer" size = "30"></td>
   </tr>
   
   <tr>
      <td>제목</td>
      <td>   
         <input type = "text" name = "title" id = "title" size = "50">         
      </td>
   </tr>
   
   <tr>
      <td>내용</td>
      <td>
         <textarea name = "content" id = "content" rows = "15" cols = "50"></textarea>
      </td>
   </tr>
   
   <tr>
      <td>ID</td>
      <td><input type = "text" name = "id" id = "id" size = "30"></td>
   </tr>
   
   <tr>
      <td colspan = "2" align = "center">
         <input type = "submit" value = "글쓰기">
      </td>
   </tr>
</table>
</form>

</html>