<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor = "ivory">
	<%
	if(session.getAttribute("id") == null){
		//로그인을 하지 않았다면
	%>

		<table width="90%" height = "90%" align = "center">
			<tr>
				<td align = "left">
					<a href = "main.jsp">
						<img src="../imgs/insta" width = "50" height = "50">
					</a>
				</td>
				
				<td height = "15%" align = "right"> <!-- 메뉴판 -->
					<a href = "loginForm.jsp">로그인</a>&nbsp;
					<a href = "inputForm.jsp">회원가입</a>&nbsp;
					<a href = "../shop/productList.jsp">상품목록</a>&nbsp;
					<a href = "../shop/orderList.jsp">구매목록</a>&nbsp;
					<a href = "../mysql/list.jsp">게시판(mysql)</a>&nbsp;
					<a href = "../oracle/list.jsp">게시판(oracle)</a>&nbsp;
					<a href = "../qna/list.jsp">QNA(mysql)</a>&nbsp;
					<a href = "../notice/list.jsp">공지사항(mysql)</a>&nbsp;
				
				</td>	
			</tr>
			
			<tr>
				<td colspan = "2">
					메인입니다.
				</td>
			</tr>
		</table>
		
		
	<%	
	}else{
		//로그인이 된 상태
	%>
		<table width = "90%" height = "90%" align = "center">
			<tr>
				<td align = "left">
					<a href = "main.jsp">
						<img src="../imgs/insta" width = "50" height = "50">
					</a>
				</td>
				<!-- 홈으로가는아이콘 -->
				
				<td valign = "top" align = "right" >
				
					<br><br>
					<a href = "logOut.jsp">로그아웃</a>&nbsp;
					<a href = "modify.jsp">회원 정보 변경</a>&nbsp;
					<a href = "../shop/productList.jsp">상품목록</a>&nbsp;
					<a href = "../shop/orderList.jsp">구매목록</a>&nbsp;
					<a href = "../mysql/list.jsp">게시판(mysql)</a>&nbsp;
					<a href = "../oracle/list.jsp">게시판(oracle)</a>&nbsp;
					<a href = "../qna/list.jsp">QNA(mysql)</a>&nbsp;
					<a href = "../notice/list.jsp">공지사항(mysql)</a>&nbsp;
						<%=(String)session.getAttribute("id") %>님 환영합니다.
				</td>
				
			</tr>
			
			<tr>
				<td colspan = "2">
					메인입니다.
				</td>
			</tr>
			
		</table>
		
		
	<%	
	}//else-end
	%>
	
</body>
</html>