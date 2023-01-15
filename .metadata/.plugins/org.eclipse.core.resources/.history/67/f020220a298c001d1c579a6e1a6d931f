<!-- 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "notice.*"%>

<%-- deleteForm 
num: <%=request.getParameter("num")%>
--%>
<% 
String no = request.getParameter("no");
String nowPage = request.getParameter("page");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<script type = "text/javascript">
			function check(){
				if(!document.delForm.id.value){
					alert("관리자 아이디를 입력해주세요.");
					document.delForm.id.focus();
					return false;
				}
				return true;
			}
		</script>
		
	</head>
<body>
	
	<h2>공지 수정</h2>
	<form name = "delForm" method = "post" action = "deletePro.jsp" onSubmit = "return check()">
		<table align = "center">
			 <tr>
     <tr>
        <td align="center" colspan="2">
         	 공지사항을 삭제하시려면 관리자 ID를 입력해주세요.
        </td>
      </tr>
      
      <tr>
      	<td align = "center">
      		<input type = "text" name = "id">
      		<input type = "hidden" name = "num" value = "<%=no%>">
      		<input type = "hidden" name = "page" value = "<%=nowPage %>">
     	 </td>
      </tr>
      
      <tr>
      	<td align = "center" colspan = "2">
      	<input type = "submit" value = "글삭제">
      	<input type = "reset" value = "다시입력">
      	<input type = "button" value = "취소" onClick = "location='list.jsp?page=<%=nowPage%>'">
      </tr>
      </table>
      </form>

</body>
</html>

 -->