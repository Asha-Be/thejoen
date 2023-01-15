<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="Login_css_list.css">
		
		<script type = "text/javascript">
			function check(){
				ff=eval("document.loginForm");
				
				if(ff.id.value==''){
					alert("id를 입력하세요.");
					ff.id.focus();
					return false;
				}
				
				if(ff.pw.value==''){
					alert("암호를 입력해주세요.");
					ff.pw.focus();
					return false;
				}
				return true;				
			}//check-end
		</script>
		
	</head>
<body align = "center">
	<h2  align = "center">로그인</h2>
	<form name = "loginForm" method = "post" action = "loginPro.jsp" onSubmit = "return check()" >
		<table border = "1" align = "center">
		
			<tr >
				<td>ID</td>
				<td><input type = "text" name = "id" id = "id" size = "20"></td>
			</tr>
			
			<tr>
				<td>암호</td>
				<td><input type = "password" name = "pw" id = "pw" size = "20"></td>
			</tr>

			<tr>
				<td colspan = "2" align = "center">
					<input type = "submit" value = "로그인">
					<input type = "reset" value = "다시입력">
				</td>
				
			</tr>
			
		</table>
	</form>
</body>
</html>