<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%-- writeForm --%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel = "stylesheet" type = "text/css" href = "style.css" />
		<script src="//code.jquery.com/jquery-3.6.1.min.js" src = "script.js"></script>
		<script type = "text/javascript" src = script.js"></script>
	</head>
	<%
/*	if(session.getAttribute("id") == null){
		out.println("<h2>로그인 해야 글을 쓸 수 잇습니다. </h2>")
		return;
	}
*/	
	
	int num = 0;
	int ref = 1;
	int re_step = 0;
	int re_level = 0;
	
	//content.jsp에서 e던져준 데이터 받기
	if(request.getParameter("num") != null){
		num = Integer.parseInt(request.getParameter("num"));
		ref = Integer.parseInt(request.getParameter("ref"));
		re_step = Integer.parseInt(request.getParameter("re_step"));
		re_level = Integer.parseInt(request.getParameter("re_level"));
	}
	
	%>
	
<body>

<%

if(num ==0){
	%>
	<h2>글쓰기</h2>
	
	<%
}else{
	 %>
	<h2>답글쓰기</h2>
	<%
}//else-end

%>

<form name = "writeForm" method = "post" action = "writePro.jsp" onsubmit="return check()">
<input type = "hidden" name = "num" value = "<%= num  %>"/>
<input type = "hidden" name = "ref" value = "<%= ref %>"/>
<input type = "hidden" name = "re_step" value = "<%= re_step %>"/>
<input type = "hidden" name = "re_level" value = "<%= re_level %>"/>


<table>
	<tr>
		<td colspan = "2" align = "right">
			<a href = "list.jsp">리스트</a>
		</td>
	</tr>
	
	<tr>
		<td>작성자</td>
		<td><input type = "text" name = "writer" id = "writer" size = "30"></td>
	</tr>

	<tr>
		<td>글제목</td>
		<td>
		<%
		//원글
		if(num ==0){
			%>
			<input type = "text" name = "subject" id = "subject" size = "50">
			
			<%
			//답글
		}else{
			 %>
			<input type = "text" name = "subject" id = "subject" size = "50" value = "[답글]">
			
			<%
		}//else-end
		%>
		</td>
	</tr>

	<tr>
		<td>글 내용</td>
		<td>
		<textarea name = "content" id = "content" rows= "10" cols = "50"></textarea>
		</td>
	</tr>

	<tr>
		<td>암호</td>
		<td><input type = "password" name = "pw" id = "pw" size = "30"></td>
	</tr>
	
	<tr>
		<td colspan = "2" align = "center">
		<% 
		if(num ==0){//글쓰기
			%>
			<input type = "submit" value = "글쓰기">
			
			<%
		}else{//답글쓰기
			 %>
			<input type = "submit" value = "답글쓰기"> 
			<input type = "button" value = "리스트" onClick = "location ='list.jsp'">
			 <%
		}//else-end
		%>
		
		</td>
	</tr>
		
	
</table>

</form>

</body>
</html>