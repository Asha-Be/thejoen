<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "shopdb.*"%>
<!-- loginForm.jsp -->

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script src="//code.jquery.com/jquery-3.6.1.min.js"></script>
		<link rel="stylesheet" type="text/css" href="style.css">
		<script type = "text/javascript" src="script.js"></script>
	</head>
<body>
	<h2>로그인 폼</h2>
	<form method="post" name = "login" action="loginPro.jsp">
		<table width="30%" border="1" bgcolor="ivory">

			<tr>
				<td>아이디</td>
				<td><input type = "text" name="memId" id="memId" size="30"></td>
			</tr>

			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw" id="pw" size="30"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="로그인" onClick="loginCheck()" >
				<input type="reset" value="다시입력">
				</td>
			</tr>
			
			
		</table>
	</form>
</body>
</html>