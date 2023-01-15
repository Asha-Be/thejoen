<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     import = "mysql.*"
    %>
<%--deleteForm.jsp --%>
num:<%=request.getParameter("num") %>

<%
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");

%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href = "style.css">
		
		<script type ="text/javascript">
			function eletePwCheck(){
				if($("#userid").val()==""){
					alert("암호를 입력하시오.");
					$("#userid").focus();
					return false;
					}
					}//deletepwcheck-end
		</script>
		
	</head>
	<body>
<center><b><font size="+2">글삭제</font></b></center>
  <form method = "post" action = "1contentpro.jsp?pageNum= <%=pageNum%>" onSubmit="return deletePwCheck()">
	<table cellpadding = "5" width = "300" align = "center">
			<tr height = "30">
				<td align = "center">
					암호를 입력하세요.
					</td>
			</tr>
			
			<tr height = "30">
					<td>
					비밀번호 :
					<input type = "password" name = "userid" id = "userid" size = "12">
					<input type = "hidden" name = "num" id = "num" >
					</td>
			</tr>
			
			<tr height = "30">
				<td align = "center">
					<input type = "submit" value = "글삭제">
					<input type = "button" value = "글목록" onclick = "Location='list.jsp?pageNum=<%=num%>'">
				
				</td>
			</tr>
	</table>
		
	</form>

</body>
</html>