<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "member.*" %>


<%
request.setCharacterEncoding("utf-8");
String id = (String)session.getAttribute("id");

MemberDAO dao = MemberDAO.getInstance(); // dao 얻기
MemberDTO dto = dao.getMember(id); //dao에서도 호출

%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
			<link rel="stylesheet" type="text/css" href="Login_css_list.css">
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> <!--  주소번호 -->
		<script src="//code.jquery.com/jquery-3.6.1.min.js"></script>
	
	<script>
   function findAddr() {

       new daum.Postcode({
           oncomplete: function(data) {
                document.getElementById('zipcode').value=data.zonecode;
                document.getElementById("addr").value=data.address;    
           }
       }).open();
   }//findAddr() -end
   </script>
   <script>
   function check(){
		if($('#pw').val()==''){
					alert("암호를 입력 하세요.");
					$('#pw').focus();
					return false;
					}
		if($('#pw').val() != $('#pw2').val()){
					alert("암호와 암호 확인이 다릅니다.");
					$('#pw2').val(' ');
					$('#pw').val(' ').focus();
					return false;
				}
				
	//email
	//email을 hidden으로 
		$('#email').val($('#email1').val()+$('#email2').val());
	
	//전화
	$('#tel').val($('#tel1').val()+$('#tel2').val()+$('#tel3').val());
	
	return true;
   
   }//check-end
   </script>
   </head>
<body>

<h2>내 정보 수정</h2>
<form name = "updateForm" method = "post" action = "modifyPro.jsp" onSubmit="return check()">
	<table border="1">
		<tr>
			<td>ID</td>
			<td><%=dto.getId() %></td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td><input type = "password" name = "pw" id = "pw">
			<font color="red">*필수 입력</font>
			</td>
		</tr>
		
		<tr>
			<td>암호 확인</td>
			<td><input type = "password" name = "pw2" id = "pw2"><font color = "red">*필수입력</font>
			</td>
		</tr>
		
		<tr>
			<td>이름</td>
			<td><input type = "text" name = "name" id = "name" value = "<%=dto.getName() %>"></td>
		</tr>

		
		
		<%
		String email = dto.getEmail();
		int idx = email.indexOf('@');
		String email1 = email.substring(0,idx);//idx직전까지 ppp@daum.net
		String email2 = email.substring(idx);
		String email3 = email.substring(idx+1);
		%>
		<tr>
			<td>이메일</td>
			<td>
				<input type = "text" name = "email1" id = "email1" value="<%=email1%>">
				@
					<select name = "email2" id = "email2">
					<option value="<%=email2%>"><%=email3%></option>
					<option value = "@naver.com">naver.com</option>
					<option value = "@daum.net">daum.net</option>
					<option value = "@nate.net">nate.net</option>
				</select>
					
						<input hidden = "hidden" name = "email" id = "email">
				
			</td>
		</tr>
	
	<%
	String tel = dto.getTel();
	
	String tel1 = tel.substring(0,3);
	String tel2 = tel.substring (3,7);
	String tel3 = tel.substring(7);
	%>
	<tr>
		<td>전화</td>
		<td><select name = "tel1" id = "tel1">
			<option value = "<%=tel1%>"><%=tel1%></option>
			<option value = "010">016</option>
			<option value = "017">017</option>
			<option value = "018">018</option>
			<option value = "011">011</option>
		</select>
		-
		<input type = "text" name = "tel2" id = "tel2" value = "<%=tel2 %>">
		-
		<input type = "text" name = "tel3" id = "tel3" value= "<%=tel3 %>">
		<input type = "hidden" name = "tel" id = "tel">
		</td>
	</tr>
	
	<tr>
	<td>우편번호</td>
	
	<td>
	<input type = "text" name = "zipcode" id = "zipcode" value = "<%=dto.getZipcode()%> " readonly >
	<input type = "button" value = "주소 찾기" onClick = "findAddr()";>
	</td>
	</tr>
	
	<tr>
		<td>주소</td>
		<td><input type = "text" name = "addr" id = "addr" value = "<%=dto.getAddr()%>" readonly></td>
	</tr>
	
	<tr>
		<td>상세 주소</td>
		<td><input type = "text" name = "addr2" id = "addr2" value = "<%=dto.getAddr2()%>" readonly>
	</tr>
	
	<tr>
	<td>
		<td colspan = "2" align = "center" >
		<input type = "submit" value = "내정보 수정">
		<input type = "button" value = "취소" onClick="location='main.jsp'">
	
	</td>
	</tr>	
	
	</table>

</form>


</body>
</html>