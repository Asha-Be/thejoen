<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "member.*"
    %>
    
    <%
    
    request.setCharacterEncoding("utf-8");
    %>
    <jsp:useBean id = "dto" class = "member.MemberDTO">
    	<jsp:setProperty name = "dto" property = "*"/>
    </jsp:useBean>
    
    <%
    
    String id = (String)session.getAttribute("id");
    dto.setId(id);
    
    MemberDAO dao = MemberDAO.getInstance();
    dao.updateMember(dto); //dao메서드 호출
    
    
    
    %>
<html>
<body>
	<table>
		<tr>
			<td align = "center">
				<b>회원정보가 수정되었습니다.</b>
			</td>
		</tr>
		<tr>
			<td>
			<form>
					<input type = "button" value = "홈으로" onClick="location='main.jsp'">
				</form>
				2초 후에는 홈으로 이동 합니다.
				<meta http-equiv="Refresh" content = "2;url=main.jsp">
			</td>
		</tr>
	</table>
	

</body>
</html>