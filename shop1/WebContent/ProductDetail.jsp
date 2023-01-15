<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "shopdb.*" import="java.util.*" %>

<!-- ProductDetail.jsp -->

<html>
	<head>
					<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
					<title>Insert title here</title>
					
					<script src="//code.jquery.com/jquery-3.6.1.min.js"></script>
					<link rel="stylesheet" type="text/css" href="style.css">
					<script type = "text/javascript" src="script.js"></script>
	</head>
	
	<body topmargin="20">
		<%@ include file="top.jsp" %>
		<%
			ProductDAO dao=ProductDAO.getInstance();
			String code=request.getParameter("code");
			ProductDTO dto = dao.getDetail(code);
		%>
		
		<h2> 상세보기 </h2>
		<%-- 장바구니 담기로 이동. --%>

		<form method="post" action="cartPro.jsp" align="center">
			<table width="80%" cellpadding="3" bgcolor="ivory">
				<tr bgcolor="#d0d0d0" height="120%">
					<td colspan="2">
						<font size="+2"><%=dto.getName() %></font>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<img src="<%=request.getContextPath()%>/imgs/<%=dto.getImage()%>">
						
					</td>
				</tr>
				
				<tr align="center">
					<td>상품코드</td>
					<td>
						<input type="text" name = "code" id="code" value="<%=dto.getCode()%>">
					</td>
				</tr>
				
				<tr align="center">
					<td>상품가격</td>
					<td>
						<input type = "text" name = "price" id="price" value="<%=dto.getPrice()%>" readonly>
					</td>
				</tr>
				
				<tr align="center">
					<td>출시날짜</td>
					<td><%=dto.getRegdate()%></td>
				</tr>
				
				<tr align="center">
					<td>제조회사</td>
					<td><input type="text" name="comp" id="comp" value="<%=dto.getComp()%>"></td>
				</tr>
			
		
		
			<tr align="center">
				<td>수량</td>
				<td>
					<select name="quantity" id="quantity">
					<option  value="1">1</option>
					<option  value="2">2</option>
					<option  value="3">3</option>
					<option  value="4">4</option>
					<option  value="5">5</option>
					</select>
				</td>
			</tr>
			
			<tr align="center">
				<td colspan="2">
					<input type = "submit" value="장바구니 담기" >
					<input type = "reset" value="물건 빼기" >
					
					<input type = "hidden" name="pro_no" id="pro_no" value="<%=dto.getPro_no()%>">
					<input type="hidden" name="userid" value="<%=session.getAttribute("memId")%>"memID>
					<input type="hidden" name="state" value="1">
				</td>
			</tr>
			</table>
			</form>
			<%--
			state: 상태
			1.접수중
			2.접수
			3.입금확인
			4.배송준비
			5.배송중
			6.배송완료
			 --%>

		<%@ include file="bottom.jsp" %>
	</body>
</html>