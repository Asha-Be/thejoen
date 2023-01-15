<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "mysql1.*"
    %>
num:<%= request.getParameter("num")  %><br>

pageNum : <%= request.getParameter("pageNum") %>

<%
request.setCharacterEncoding("utf-8");
/*
if(session.getAttribute("id") == null){
	out.println("<h2>로그인을 해야 글을 수정할 수 있습니다.</h2>");
	return;
}
*/
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	
	<script type = "text/javascript">
	function check(){
		if(document.updateForm.pw.value==""){
			alert("암호를 필수 입력");
			document.updateForm.pw.focus();
			return false;
		}
		document.updateForm.submit();//서버로 전송
	}//check-end
	
	</script>
</head>

<%
//content에서 보내준 내용 받기

int num = Integer.parseInt(request.getParameter("num"));
String pageNum=request.getParameter("pageNum");

BoardDAO dao = BoardDAO.getInstance(); //dao 객체보기
BoardDTO dto = dao.getUpdata(num); // dao 메서드 호출
%>

<body>

	<h2>글 수정 폼</h2>
	<form name="updateForm" method = "post" action = "updatePro.jsp?pageNum=<%=pageNum %>">
		<table>
			<tr>
			<td>작성자</td>
			<td><input type = "text" name = "writer" id = "writer" size = "30" value="<%=dto.getWriter()%>">
					<input type="hidden" name = "num" value ="<%= num%>">
			</td>
			</tr>

			<tr>
			<td>글 제목</td>
			<td><input type = "text" name = "subject" id = "subject" size = "50" value="<%=dto.getSubject()%>">
					
			</td>
			</tr>

	
			<tr>
			<td>글 내용</td>
			<td>
			<textarea name = "content" rows = "10" cols = "50"><%=dto.getContent() %></textarea>
			</td>
			</tr>

			<tr>
			<td>암호</td>
			<td>
			<input type = "password" name = "pw" size = "20">
			</td>
			</tr>

			<tr>
			<td colspan = "2" align="center">
				<input type = "button" value ="글수정" onClick="check()">
				<input type = "reset" value = "다시입력">
				<input type = "button" value = "리스트" onClick="location ='list.jsp'">
			</td>
			</tr>
			
			</table>
	</form>
</body>
</html>