<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "member.*"
    %>
    
    
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type = "text/javascript">

function begin(){
	ff=eval(document.delForm);
	ff.pw.focus();
}
function check(){
	ff=eval(document.delForm);
	if(ff.pw.value ==''){
		alert("암호를 입력하세요.");
		ff.pw.focus();
		return false;
	}
	
answer = confirm("정말로 탈퇴하시겠습니까?>");
if(answer==true){
	
	return true;
}else if(answer == false){
	
	return false;
}

}//check()-end

</script>
<style type = "text/css">
		h2{text-align :center;}
		table{margin:auto; width:30%;}		
</style>
<link rel="stylesheet" type="text/css" href="Login_css_list.css">
</head>
<body onload = "begin()">
	<form name = "delForm" method = "post" action = "deletePro.jsp" onSubmit = "check()">
		<table border = "1">
			<tr>
				<td>암호</td>
				<td><input type = "password" name = "pw" id = "pw" size = "10" ><br>  *필수입력</td>
			</tr>
			
			<tr>
				<td colspan = "2" align = "center">
					<input type = "submit" value = "회원탈퇴">
					<input type = "button" value = "취소" onClick="location='main.jsp'">
				</td>
			</tr>
			
		</table>
	</form>

</body>
</html>