<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--deleteForm.jsp --%>
num:<%=request.getParameter("num") %>

<%
String num=request.getParameter("num");
String nowPage=request.getParameter("page");
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
	h2{text-align: center;}
	table{margin: auto; width:35%;}
	</style>
	
	<script type="text/javascript">
	function check(){
		if(!document.delForm.pw.value){
			alert("암호를 필수 입력 입니다");
			document.delForm.pw.focus();
			return false;
		}
		return true;
	}
	
	</script>
</head>
<body>
  <h2>글삭제 폼</h2>
  <form name="delForm" method="post" action="deletePro.jsp" onSubmit="return check()">
    <table border="1">
    
      <tr>
        <td align="center" colspan="2">
         	 암호를 입력 하세요
        </td>
      </tr>
      
      <tr>
        <td>암호입력</td>
        <td>
          <input type="text" name="pw">
          <input type="hidden" name="num" value="<%=num %>">
          <input type="hidden" name="page" value="<%=nowPage %>">
        </td>
      </tr>
      
      <tr>
        <td align="center" colspan="2">
          <input type="submit" value="글삭제">
          <input type="reset" value="다시입력">
          <input type="button" value="취소" onClick="location='list.jsp?page=<%=nowPage%>'">
        </td>
      </tr>
    </table>
  </form>
</body>
</html>