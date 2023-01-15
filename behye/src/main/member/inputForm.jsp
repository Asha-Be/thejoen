<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "member.*"%>

<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="//code.jquery.com/jquery-3.6.1.min.js" src = "script.js"></script>
	<!-- <link rel = "stylesheet" type = "text/css" href = "style.css"> -->
	<link rel="stylesheet" type="text/css" href="css_list.css">
	
	<!-- daum.API -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
   
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
	   //데이터 입력 여부 체크
	   
	   if($("#id").val()==''){
		   alert("id를 입력하세요.");
		  $("#id").focus();
		   return false;
	   }
	   
	   if($("#pw").val()==''){
		   alert("암호를 입력하세요.");
		   $("#pw").focus();
		   return false;
	   }
	   //암호 확인
	   
	   if($("#pw").val() != $("#pw2").val()){
		   alert("암호와 암호 확인 : 입력된 값이 다릅니다.");
			$("#pw2").val(' '); //내용 삭제		
		   $("#pw").val(' ').focus();
		   return false;
	   } 
		
	   if($("#name").val()==''){
		   alert("이름을 입력 하세요.");
		   $("#name").focus();
		   return false;
	   }
	   
	   //이메일 email1, email2, email
	   
	   if($("#email1").val() == ''){
		   alert("이메일을 입력하세요.");
		   $("#email1").focus();
		   return false;
	   }
	   
	   //email1 + email2 = email
	   
	   if($("#email1").val() != ''){
		   email=$("#email1").val()+$("#email2").val();
		   
		   $("#email").val(email); //hidden값
	   }
		
	   //전화 : tel1, tel2, tel3
	   
	   if($("#tel2").val() == '' || $("#tel3").val() == ''){
		   alert("전화번호를 입력하세요.");
		   $("#tel2").focus();
		   return false;
	   }
	   tel=$("#tel1").val()+$("#tel2").val()+$("#tel3").val();
	   $("#tel").val(tel); //hidden
	   
	   
	   //상세주소
	   
	   if($("#addr2").val() == ''){
		   alert("상세 주소를 입력하세요.");
		   $("#addr2").focus();
		   return false;
	   }
	   
	   if($("#idck").val() == 'false'){
		   alert("ID중복체크하세요.");
		   $("#id").focus();
		   return false;
	   }
	   
	   return true;
   }//check-end
   
   //ajax로 아이디 중복 체크
   //---------------------------------
   function idCheck(){
	   if($("#id").val()==''){
		   alert("id를 입력하세요.");
		   $("#id").focus();
		   return false;		   
		   
	   }else{
		   //아자아자아작스

	   $.ajax({
		   
		   type :"POST",
		   url:"confirmID.jsp",
		   data:"id="+$("#id").val(),
		   dataType : "JSON",
		   success:function(data){
		//	   alert(data.x);
			   
			   if(data.x==1){
				   alert("사용중인 id 입니다.");
				   $("#id").val(' ').focus();
				   
			   }else{
				   alert("사용 가능한 id 입니다.");
				   $('#idck').val('true');
				 
				   $("#pw").focus();
				   
			   }//else-end
			   
		   }//success-end
	   });//$.ajax-end
	   
	   }//else-end
   }//id-check()
   //---------------------
   
   function aa(){
	   if($("#idck").val() == 'false'){
		   alert("ID중복체크하세요.");
		   $("#id").focus();
		   return false;
	   }
   }
 

   </script>
	</head>
<body>
	<h2>회원가입</h2>
	<form name = "inForm" method = "post" action = "inputPro.jsp" onSubmit="return check()">
		<table border = "1">
		
			  <tr>
        <td>ID</td>
        <td>
        <input type="text" name="id" id="id" size="20">
        <input type="hidden" name="idck" id="idck" value="false">
        <input type="button" value="ID중복체크" onClick="idCheck()">
        </td>
      </tr>
      
		
			<tr>
				<td>암호</td>
				<td><input type = "password" name = "pw" id = "pw" size = "20"></td>
			</tr>
		
			<tr>
				<td>암호확인</td>
				<td><input type = "password" name = "pw2" id = "pw2" size = "20"></td>
			</tr>
		
			<tr>
				<td>이름</td>
				<td><input type = "text" name = "name" id = "name" size = "20"></td>
			</tr>
			
			<tr>
				<td>이메일</td>
				<td><input type = "text" name = "email1" id = "email1" size = "10">
				@
				<select name = "email2" id = "email2">
					<option value = "@naver.com">	naver.com</option>
					<option value = "@nate.com">	nate.com</option>
					<option value = "@daum.net">	daum.net</option>
				</select>
				
				<input hidden = "hidden" name = "email" id = "email">
				
				</td>
			</tr>
			
			<tr>
				<td>전화번호</td>
					<td>
						<select name = "tel1" id = "tel1">
								<option value = "010">010</option>
								<option value = "011">011</option>
								<option value = "017">017</option>
								<option value = "018">018</option>
						</select>
						-
						<input type = "text" name = "tel2" id = "tel2" size = "4">
						-
						<input type = "text" name = "tel3" id = "tel3" size = "4">
						
						<input hidden ="hidden" name = "tel" id = "tel">
					</td>
			</tr>
			<!-- 주민번호 -->
			
			<tr>
				<td>주민번호</td>
				<td>
				<input type = "text" name = "jumin1" size = "6" onKeyup="if(this.value.length==6) inForm.jumin2.focus()">
					-
				<input type = "text" name = "jumin2" size = "7" onKeyup="if(this.value.length==7) inForm.zipcode.focus()">
				</td>
			
			</tr>
			
			
			<!-- 우편번호 -->
			
			<tr>
				<td>우편번호</td>
				<td>
					<input type = "text" name = "zipcode" id = "zipcode" size = "7" readonly="readonly">
					<input type = "button" value = "주소 찾기" onClick ="findAddr()">
				</td>
			</tr>	
			
			<!-- 주소 -->
			<tr>
				<td>주소</td>
				<td>
					<input type = "text" name = "addr" id = "addr" size = "50">
				</td>
			</tr>
			
			<!-- 상세주소 -->
					<tr>
					<td>상세주소</td>
						<td >
							<input type = "text" name = "addr2" id = "addr2" size = "30" onFocus="aa()">


<!-- 							<input type = "text" name = "addr2" id = "addr2" size = "30" onFocus="aa()">
		 -->	
						</td>
					</tr>
					
					<tr>
						<td colspan = "2" align = "center">
							<input type = "submit" value = "회원가입">
							<input type = "reset" value = "다시 입력">
						
						<!-- 	
							<input type = "button" value = "가입 안함" onClick="javaScript:window.location='main.jsp'">
							<input type = "button" value = "가입 안함" onClick="javaScript:window.location='main.jsp'">
							<input type = "button" value = "가입 안함" onClick="window.location='main.jsp'">
							<input type = "button" value = "가입 안함" onClick="location='main.jsp'">
							위의 네개 다 같은거임
							 -->
						</td>
					</tr>

		</table>
	</form>
</body>
</html>