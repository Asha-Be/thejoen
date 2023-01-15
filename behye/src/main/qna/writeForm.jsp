<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"></script>
</head>
<%


int num = 0;
int ref = 1; //글그룹
int levelNo = 0; //글순서
int ordNo = 0; //답글 깊이

//content.jsp에서 던져준 데이터 받기
if(request.getParameter("num")!=null){
	//받아온 num에 아무것도 없을때!!
	
	num = Integer.parseInt(request.getParameter("num"));
	ref = Integer.parseInt(request.getParameter("ref"));
	ordNo = Integer.parseInt(request.getParameter("ordNo"));
	levelNo = Integer.parseInt(request.getParameter("levelNo"));
	
	//num, 글 그룹, ordno, levelno 다 받아오기.
	//이건왜넣은거야그러고보면?아이게잇어야글을쓰는ㄴ구냥휴하하
	
}//if-end

%>

<body>
	
	<%
	if(num == 0){
		%>
		<h2>글쓰기</h2>	
		<%
	}else{
	%>
	<h2>답글쓰기</h2>
	<%
	}//else-end
	%>

	<form name = "writeForm" method = "post" action = "writePro.jsp" onsubmit = "return check()">
	<%-- 이 폼(정보를 제출하기 위한 대화형 컨트롤을 포함하는 문서 구획)의 이름은 writeForm.
	 post로 클라이언트에서 정보를 생성하거나업데이트하려고한다. 
	 양식데이터를 처리할 프로그램의 url요소의 특성으로 재정의할 수 있음.
	 폼의 값을 전송하기 전에, onsubmit 덕분에 check() function 혹은 클래스를 먼저 반환시켜서 실행시킬 수 있음.
	  --%>
	<input type = "hidden" name = "num" value="<%=num %>"/>
	<%-- 뭔가숨겨서넣는다. 숨긴것의 이름은 num 이며 이것의 값은 num...뭐 그때그때바뀐단다 --%>
	<input type = "hidden" name = "ref" value="<%=ref %>"/>
	<input type = "hidden" name = "levelNo" value="<%=levelNo%>"/>
	<input type = "hidden" name = "ordNo" value="<%=ordNo%>"/>
	
	<table>
		<tr>
			<td colspan = "2" align = "right">
				<a href = "list.jsp">리스트</a>
			</td>
		</tr>
		<%-- 나는 행하나에 열로 두칸이 합쳐졌으며 오른쪽정렬이 된 리스트로 돌아가는 링크를 삽입했다. --%>	
			
		<tr>
			<td>글쓴이</td>
			<td><input type = "text" name = "writer" size = "30"></td>			
		</tr>

		<tr>
			<td>제목</td>
			<td>
			<%
			if(num==0){ //만약 원글이라면 
			%>
			<input type = "text" name = "title" id = "title" size = "50">
			<%
			}else{//답글이라면?!
			%>			
			<input type = "text" name = "title" id = "title" size = "50" value = "[답글]">
			<%
			} //end
			%>
			</td>
		</tr>

		<tr>
			<td>내용</td>
			<td><textarea name = "content" rows = "10" cols = "50"></textarea></td>			
		</tr>

		<tr>
			<td>유저명</td>
			<td><input type = "text" name = "userid" size = "30"></td>			
		</tr>
		
		<tr>
			<td colspan = "2" align = "center">
			<%
			if(num==0){ //만약 원글이라면 
			%>
			<input type = "submit" value = "작성하기">
			<%
			}else{//답글이라면?!
			%>			
			<input type = "submit" value = "작성하기">
			<input type = "button" value = "리스트" onClick = "location='list.jsp'">
			
			<%
			} //end
			%>
			
			</td>
		</tr>
	</table>
	
	
	</form>
</body>
</html>