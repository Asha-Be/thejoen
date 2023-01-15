<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="mysql1.*" import="java.util.*"%>


<%
	request.setCharacterEncoding("utf-8");
%>

<!-- list.jsp -->
<%
	int pageSize = 10; //페이지당 10개
%>
<%
	String pageNum = request.getParameter("pageNum");
	//전체 페이지 받기
	if (pageNum == null) {
		pageNum = "1";
	}

	int currentPage = Integer.parseInt(pageNum);
	int starRow = (currentPage - 1) * pageSize + 1;
	//(1-1) * 10 + 1 = 1
	//(2-1) *10 +1 = 11
	//(3-1) *10 +1 = 21  페이지넘어가는 기준일라나

	int endRow = currentPage * pageSize; // 1*10 = 10, 2*10 = 20....
											// 2*10 == 10 > 2페이지 끝행
											//3* 10 == 30 >3 페이지 끝행..

	int count = 0; //총 글 갯수
	int number = 0; //글번호 처리할 변수

	List<BoardDTO> list = null;

	BoardDAO dao = BoardDAO.getInstance();
	count = dao.getCount(); //총글갯수
	if (count > 0) {

		//글이 존재한다면
		list = dao.getList(starRow, pageSize); //스타트로부터 10만큼 갯수 열개식가져올거양 그것이리스트
	}//-end

	number = count - (currentPage - 1) * pageSize;
	//count=17
	//17-(2-1)*10=>7   출력할,보여줄 글번호
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<h2>
		글 목록 (▣ 전체글 ▣ :
		<%=count%>
		)
	</h2>
	<table>
		<tr>
			<td align="right"><a href="writeForm.jsp"> 새글쓰기 </a></td>
		</tr>
	</table>

	<%
		if (count == 0) {
			out.println("<h2>게시판에 저장된 글이 없습니다.</h2>");
		} else {//글이 있으면
	%>
	<table border="1">
		<tr bgcolor="ivory">
			<th>번호</th>
			<th>글 제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
			<th>IP</th>
		</tr>
		<%
			for (int i = 0; i < list.size(); i++) {
					BoardDTO dto = (BoardDTO) list.get(i);
		%>
		<tr>
			<td><%=number--%></td>
			<td>
				<%-- 글 제목 표시 답글처리 10권 이상 조회시 hot표시 --%> <%
 	int wid = 0;

 			if (dto.getRe_level() > 0) {//답글이 있는가? 있다고한다면
 				wid = 5 * (dto.getRe_level()); //5의 배수로커진다네
 %> <img src="../imgs/level.gif" width="<%=wid%>" height="16">
				<img src="../imgs/re/gif"> <%
 	} else { //원글이면
 %> <img src="../imgs/level/gif" width="<%=wid%>" height="16">

				<%
					}//else-end
				%> <%-- 글 제목을 클릭하면 content.jsp로 가게한다. --%> <a
				href="content.jsp?num=<%=dto.getNum()%>&pageNum<%=currentPage%>">
					<%=dto.getSubject()%>
			</a> <%-- 조회수가 10번 이상이면 hot.gif 표시 --%> <%
 	if (dto.getReadcount() >= 10) {
 %> <img src="../imgs/hot.gif"> <%
 	}//if-end
 %>
			</td>

			<td><%=dto.getWriter()%></td>
			<td><%=dto.getRegdate()%></td>
			<td><%=dto.getReadcount()%></td>
			<td><%=dto.getIp()%></td>
		</tr>
		<%
			}//for-end
		%>
	</table>

	<%
		}//else-if
	%>

	<%--블럭처리,페이지 처리  --%>
	<%
		if (count > 0) {//글이 존재 하면
	%>
	<table border="1">
		<tr>
			<td align="center">
				<%
					//전체 페이지 수 구하기
						int na = (count % pageSize == 0 ? 0 : 1); //나머지
						int pageCount=count/pageSize+(count%pageSize==0?0:1);

						int pageBlock = 10; //블럭 당 10 페이지
						int startPage = (int) (currentPage / pageBlock) * 10 + 1; //시작 페이지 구하기

						int endPage = startPage + pageBlock - 1; //블럭의 끝 페이지
						//								1      +    10		-1 == 10

						if (endPage > pageCount) {
							endPage = pageCount;
						}//if-end

						//이전블럭
						 if(startPage>10){
				              %>
				           <a href="list.jsp?pageNum=<%=startPage-10%>">[이전블럭]</a>
				           <%
				           }//if-end
				
 	//페이지 처리
 	for(int i = startPage; i<=endPage; i++){
 		%>
 		<a href = "list.jsp?pageNum=<%=i %>">[<%=i%>]</a>
 		
 		<%
 	}//for-end
 	
 	
 	//다음 블럭
 	if(endPage<pageCount){
 		%>
 		<a href = "list.jsp?pageNum=<%= startPage%>">[다음블럭]</a>
 		<%
 	}//if-end
 	%>
			</td>
		</tr>
	</table>
	<%
		}//if-end
	%>

</body>
</html>