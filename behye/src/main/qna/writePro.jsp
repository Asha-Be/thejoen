<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "mysql.*"
    
    %>
    
    <%
    request.setCharacterEncoding("utf-8");
    %>
    <jsp:useBean id="dto" class = "mysql.BoardDTO">
    	<jsp:setProperty name = "dto" property = "*"/>
    </jsp:useBean>
    
    <%-- <jsp:useBean> 액션태그는 JSP 페이지에서 사용할 자바빈 객체를 지정해주는 기능을 한다. 
    
    <jsp:useBean id="[이름]" class="[자바빈클래스이름]" scope="[범위]"/>
    
    id - jsp페이지에서 자바빈 객체에 접근할 때  사용할 이름을 명시한다.

	class - 패캐지 이름을 포함한 자바빈 클래스의 완전한 이름을 명시한다.
	
	scope - 자바빈 객체가 저장될 영역을 지정한다.
	 page ,request, session, application 중 하나를 값으로 갖는다. 기본값은 page이다.
	    
      --%>
      
      <%
      
      BoardDAO dao = BoardDAO.getInstance();//객체보기, 객체 생성.
      dao.insertBoard(dto); //dao의 정보를가진 insertBoard쪽으로? dao 메서드를 호출한다. 
      
      response.sendRedirect("list.jsp");
      //렇게 특정 처리 후, 또는 특정 조건일 때에 지정한 페이지로
      //이동하고 싶은 경우 많이 사용되는 것이 response.sendRedirect() 메소드이다.
      
      
      
      
      %>