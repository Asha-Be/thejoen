<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "shopdb.*"
    import="java.util.*"
    
    %>

<!-- productList.jsp -->
<%
request.setCharacterEncoding("utf-8");

%>
<%!
//선언부 {메서드 , 전역변수,...
	List list;
%>
<%-- <%=request.getContextPath()%> //프로젝트명 --%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<script src="//code.jquery.com/jquery-3.6.1.min.js"></script>
		<script type = "text/javascript" src="script.js"></script>
		
	</head>
<body bgcolor="#996600" topmargin="20">
	<%@ include file="top.jsp"%>
	<%
		ProductDAO dao=ProductDAO.getInstance();//dao객체 열기
		list=dao.getGoodList();//dao메서드 호출
		
		if(list.isEmpty()){
			//상품이 없을때
			out.println("등록된 상품이 없습니다.");
			
		}else{
			//상품이 있을때
			%>
			<table border="1" width="80%" cellpadding="3">
				<tr>
					<%
							for(int i=0; i<list.size(); i++){
								if(i%3==0){
									out.println("</tr><tr>");
								}//if-end
								ProductDTO dto=(ProductDTO)list.get(i);
								%>
								<td>
									<table border="0">
										<tr align="center" bgcolor="#d0d0d0" height="120%">
											<td colspan="2">
												<%=dto.getName() %>
											</td>
										</tr>

										<tr >
											<td colspan="2" align="center">
												<%
												if(dto.getStock()>0){//상품 수량이 남아있으면 링크를 눌러 상세보기
													%>
													<a href="javaScript:productDetail('<%=dto.getCode()%>')">
													<img src="<%=request.getContextPath()%>/imgs/<%=dto.getImage()%>" width="150" height="150">
													</a>
													<%
												}else{
													//매진되엇다면
													out.println("품절");
												}//else-end
												%>
											</td>
										</tr>
										
										<tr>
											<td>상품코드</td>
											<td><%=dto.getCode()%></td>
										</tr>

										<tr>
											<td>가격</td>
											<td><%=dto.getPrice()%></td>
										</tr>
										
										<tr>
											<td>출시 날짜</td>
											<td><%=dto.getRegdate()%></td>
										</tr>

										<tr>
											<td>회사</td>
											<td><%=dto.getComp()%></td>
										</tr>

										<tr>
											<td>stock,물량</td>
											<td>
											<%
											if(dto.getStock()>0){
												out.println(dto.getStock()+"개");
											}else{
												out.println("품절");
											}//else-end
											%>
											</td>
										</tr>
										
									</table>
								</td>
								<%
							}//for-end
					%>
				</tr>
			</table>
			<%
		}//else-end
	 %>
	 <form name="detail" method="post" action="ProductDetail.jsp">
	 	<input type="hidden" name="code"><!--  값은 자바스크립트에서 넣는다.  -->
	 </form>
	 
	<%@ include file="bottom.jsp"%>
</body>
</html>