<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "shopdb.*"%>
<!-- top.jsp -->
<%
String memId=(String)session.getAttribute("memId");
String log="";
String mem="";

if(memId==null){
	//로그인 하지 않았을 경우
	log="<a href='loginForm.jsp'>로그인</a>";
	mem="<a href='inputForm.jsp'>회원가입</a>";
}else{
	//로그인 했을 경우
	log="<a href='logOut.jsp'>로그아웃</a>";
	mem="<a href='updateForm.jsp'>내정보 수정</a>";
}//else-end

%>

<table width="80%" align="center" bgColor="#ffff99">
	<tr bgcolor="#ffcc00">
		<td><b><%=log %></b></td>
		<td><b><%=mem %></b></td>
		<td><b><a href="productList.jsp">상품목록</a></b></td>
		<td><b><a href="cartList.jsp">장바구니</a></b></td>
		<td><b><a href="orderList.jsp">구매목록</a></b></td>
	</tr>
</table>

