<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "mysql1.*"
    %>

num:<%= request.getParameter("num")  %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel = "stylesheet" type = "text/css" href = "style.css">
	</head>
<%
//list.jsp가 보내준 데이터 받기

int num=Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");

BoardDAO dao = BoardDAO.getInstance();//dao 객체얻기
BoardDTO dto = dao.getBoard(num); //dao 메서드 호출

int ref= dto.getRef();
int step = dto.getRe_step();
int level = dto.getRe_level();
%>
<body>
  <h2>글내용보기</h2>
  <table border="1">
    <tr>
      <td>글번호</td>
      <td><%=dto.getNum() %></td>
      
      <td>조회수</td>
      <td><%=dto.getReadcount() %></td>
    </tr>
    
    <tr>
      <td>작성자</td>
      <td><%=dto.getWriter() %></td>
      
      <td>작성일</td>
      <td><%=dto.getRegdate() %></td>
    </tr>
    
    <tr>
      <td>글제목</td>
      <td colspan="3">
        <%=dto.getSubject() %>
      </td>
    </tr>
    
    <tr>
      <td colspan="4">
         글내용
        <pre><%=dto.getContent() %></pre>
      </td>
    </tr>

			
			<tr>
		   <td colspan="4" align="right">
		   <a href="writeForm.jsp">[새글쓰기]</a>
		   <a href="updateForm.jsp?num=<%=dto.getNum()%>&pageNum=<%=pageNum%>">[글수정]</a>
		   <a href="deleteForm.jsp?num=<%=dto.getNum()%>&pageNum=<%=pageNum%>">[글삭제]</a>
		   <a href="writeForm.jsp?num=<%=num%>&ref=<%=ref %>&re_step=<%=step %>&re_level=<%=level %>">[답글쓰기]</a>
		   <a href="list.jsp?num=<%=pageNum%>">[리스트]</a>
		   </td>
										
			</tr>
			
			
		</table>
</body>
</html>