<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "shopdb.*" %>

<html>
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<link rel="stylesheet" type="text/css" href="style.css">
		<script src="//code.jquery.com/jquery-3.6.1.min.js"></script>
		<script type = "text/javascript" src="script.js"></script>

	</head>
<body bgcolor="#996600" topmargin="100">
	<%@ include file="top.jsp"%>
	<table width="80%" bgcolor="#ffff99" height="100%">
		<%
			//top.jsp를 include로 가져와서 top에서 선언된 memId변수가 여기서도 적용되는거임
			memId=(String)session.getAttribute("memId");
		
			if(memId != null){//로그인 상태라면
				%>
				<tr>
					<td align="center">
					<%=memId%> 님의 방문을 환영 합니다.
					</td>
				</tr>
				
				<tr>
					<td align="center"><a href="productList.jsp">상품목록 보기</a></td>
				</tr>
				<%
			}else{
				//로그인 상태가 아니라면
				%>
				<tr>
					<td align="center">
						로그인 하신 후에 이용해 주세요.
					</td>
				</tr>
				<%
			}//else-end
		 %>
	</table>
	<%@ include file="bottom.jsp"%>
	
</body>
</html>\