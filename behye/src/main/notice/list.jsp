<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="notice.*"
    import="java.util.*"
    %>
<%--list.jsp --%>
<%
request.setCharacterEncoding("utf-8");
%>

<%!
int pageSize=10;//  페이지당 10개
%>

<%
String pageNum=request.getParameter("pageNum");//현재 페이지 받기
if(pageNum==null){
	pageNum="1";
}

int currentPage=Integer.parseInt(pageNum);//문자열을 정수로 변환 
int startRow=(currentPage-1)*pageSize+1;//페이지의 시작 글번호(시작행)
//                     (1-1)*10+1  => 1    1 페이지 시작행
//                     (2-1)*10+1===> 11   2 페이지 시작행 
//                     (3-1)*10+1===> 21   3 페이지 시작행 
//                     (4-1)*10+1 ==> 31   4 페이지 시작행 
//                     (5-1)*10+1 ==> 41   5 페이지 시작행 

int endRow=currentPage*pageSize;//페이지의 끝 행
//                   1*10 ==>10  1 페이지 끝행
//                   2*10 ==>20  2 페이지 끝행
//                   3*10 ==>30  3 페이지 끝행

int count=0;//총 글갯수 
int number=0;//보여줄 글번호 처리할 변수 
List<NoticeDTO> list=null;

NoticeDAO dao=NoticeDAO.getInstance();//dao객체 얻기 
count=dao.getCount();//총 글 갯수 

if(count>0){//글이 존재 하면 
	list=dao.getList(startRow, pageSize);
}//if-end

number=count-(currentPage-1)*pageSize;//보여줄 글번호
//     count=17
//     17-(2-1)*10=>
//     17-10 => 7    6 5 4 3 2 1     
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css_list.css">

<style>
		p{
		                font-size: 140%;
		                font-weight : bold;
		            }
		a#new{
					text-align: right;
		}  
</style>
</head>

<body>
  <h2>공지사항</h2>
  (전체글:<%=count%>)
  
  <br>
  <table>
    <tr>
      <td>
      &nbsp;&nbsp;&nbsp;&nbsp;회사 공지사항을 조회합니다.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      &nbsp;
        <a href="writeForm.jsp" id = "new">새글쓰기</a>
      </td>
    </tr>
  </table>
  
  <%
  if(count==0){//글이 없으면 
	  out.println("<h2>게시판에 저장된 글이 없습니다</h2>");
  }else{//글이 있으면 
	%>
	  <table border="1">
	    <tr>
	      <th>번호</th>
	      <th>글제목</th>
	      <th>작성자</th>
	      <th>작성일자</th>
	      <th>조회수</th>
	       
	    </tr>
	    <%
	    for(int i=0; i<list.size(); i++){
	    	NoticeDTO dto=(NoticeDTO)list.get(i);
	    	%>
	    	<tr>
		    	<td><%=number--%></td>
		    	<td> 
 		    	   <%--글제목을 클릭하면 content.jsp가게 한다  --%>
		    	   <a href="content.jsp?no=<%=dto.getNo()%>&pageNum=<%=currentPage%>">
		    	     <%=dto.getTitle()%>
		    	   </a>
		    	   
		    	   <%--조횟수가 10번 이상이면 hot.gif 표시  --%>
		    	   <%
		    	   if(dto.getReadcount()>=10){
		    		   %>
		    		   <img src="../imgs/hot.gif">
		    		   <%
		    	   }//if-end
		    	   %>
		    	</td>
		    	
		    	<td><%=dto.getWriter() %></td>
		    	
		    	<td><%=dto.getStartDate() %></td>
		    	<td><%=dto.getReadcount() %></td>
		    	 
	    	</tr>
	    	<%
	    }//for-end
	    %>
	  </table>
	 <%
  }//else-end 글이 있으면 
  %>
  
  <%--블럭처리,페이지 처리  --%>
  <%
  if(count>0){//글이 존재 하면
	  %>
	  <table border="1">
	     <tr>
	       <td align="center">
	         <%
	         //전체페이지 수 구하기 
	         //int na=(count%pageSize==0?0:1);//나머지
	         int pageCount=count/pageSize+(count%pageSize==0?0:1);//몫+나머지  37/10=3  37%10=7
	         
	         int pageBlock=10;//블럭당 10개 페이지 
	         int startPage=(int)(currentPage/pageBlock)*10+1;// 블럭의 시작 페이지
	         //                            (1/10)*10+1==>1
	         //                            (5/10)*10+1==>1
	         //                            (9/10)*10+1==>1
	         
	         //                            (10/10)*10+1==>11
	         //                            (15/10)*10+1==>11
	         //                            (19/10)*10+1==>11
	         
	         //                            (20/10)*10+1==>21
	         //                            (25/10)*10+1==>21
	         //                            (29/10)*10+1==>21
	         
	         int endPage=startPage+pageBlock-1;//블럭의  끝 페이지 
	         //                  1+10-1===>10
	         //                  11+10-1===>20
	         //                  21+10-1===>30
	         
	         if(endPage>pageCount){
	        	 //에러 방지 
	        	 endPage=pageCount;
	         }//if-end
	         
	         //이전블럭 
	         if(startPage>10){
	        	 %>
	        	 <a href="list.jsp?pageNum=<%= startPage-10%>">[이전블럭]</a>
	        	 <%
	         }//if-end 이전블럭 
	         
	         
	         //페이지 처리 
	         for(int i=startPage; i<=endPage; i++){
	        	 %>
	        	 <a href="list.jsp?pageNum=<%=i%>">[<%=i%>]</a>
	        	 <%
	         }//for-end
	         
	         
	         //다음블럭
	         if(endPage<pageCount){
	        	 %>
	        	 <a href="list.jsp?pageNum=<%=startPage+10%>">[다음블럭]</a>
	        	 <%
	         }//if-end
	         %>
	       </td>
	     </tr>
	  </table>
	  <%
  }//if-end //글이 존재 하면
  
  //글 검색
  %>
  
</body>
</html>s