<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="notice.*"
    import="java.text.SimpleDateFormat"
    %>
<%--content.jsp --%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>content.jsp</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	
	<script type="text/javascript">
	function deletePro(){
		
		if(confirm("정말 삭제 하시겠습니까?")){//true
			document.delForm.submit();
		}
 	}//deletePro()
	</script>
	<link rel="stylesheet" type="text/css" href="css_list.css">
</head>
<%
//list.jsp가 보내준 데이터 받기
int no=Integer.parseInt(request.getParameter("no"));
String pageNum=request.getParameter("pageNum");

NoticeDAO dao=NoticeDAO.getInstance();//dao객체 얻기 
NoticeDTO  dto=dao.getNotice(no);//dao메서드 호출 

SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//날짜 출력 형식
%>
<body>
 <h2>공지사항 글내용 보기 </h2>
 <table border="1">
   <tr>
     <td class="tdcolor">글번호</td>
     <td><%=no %></td>
     <td class="tdcolor">조회수</td>
     <td><%=dto.getReadcount() %></td>
   </tr>
   
   <tr>
     <td class="tdcolor">작성자</td>
     <td><%=dto.getWriter() %></td>
     <td class="tdcolor">작성일</td>
     <td>
     <%=dto.getStartDate() %><br>
     <%=sdf.format(dto.getStartDate())%> 
      </td>
   </tr>
   
   <tr>
     <td class="tdcolor">글제목</td>
     <td colspan="3">
       <%=dto.getTitle() %>
     </td>
   </tr>

   <tr height="150">
     <td colspan="4">
      <pre><%=dto.getContent() %></pre>
     </td>
   </tr>
   
   <tr>
     <td colspan="4" align="right">
       <a href="writeForm.jsp">[새글쓰기]</a>
       <a href="updateForm.jsp?no=<%=no%>&pageNum=<%=pageNum%>">[글수정]</a>
       <a href="javaScript:deletePro()">[글삭제]</a>
       <a href="list.jsp?pageNum=<%=pageNum%>">[리스트]</a>
     </td>
   </tr>
 </table>
 
 <form name="delForm" action="deletePro.jsp">
   <input type="hidden" name="no" value="<%=no%>">
   <input type="hidden" name="pageNum" value="<%=pageNum%>">
 </form>
</body>
</html>