<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="notice.*"
    %>
<%--updatePro.jsp --%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="dto" class="notice.NoticeDTO">
  <jsp:setProperty name="dto" property="*"/>
</jsp:useBean>

<%
String pageNum=request.getParameter("pageNum");
NoticeDAO dao=NoticeDAO.getInstance();//dao객체 얻기 

int x=dao.updateNotice(dto);

if(x==1){
	response.sendRedirect("list.jsp?pageNum="+pageNum);
}else{//수정 실패
%>
<script>
  alert("수정 실패");
  history.back();
</script>
<%
}//else-end
%>