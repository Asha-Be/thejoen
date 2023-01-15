package notice;
import java.sql.*;//Connection, Statement,PreparedStatement,ResultSet

import javax.sql.*;//DataSource
import javax.naming.*;//lookup

import mysql.BoardDTO;

import java.util.*;//List,ArrayList

public class NoticeDAO {

	Connection con=null;
	PreparedStatement pstmt=null;
	Statement stmt=null;
	ResultSet rs=null;
	String sql="";

	private NoticeDAO(){}//생성자,외부에서 생성 못하게 

	private static NoticeDAO instance=new NoticeDAO();//객체생성 

	//JSP에서 사용할 메서드 
	public static NoticeDAO getInstance(){
		return instance;//dao객체 리턴 
	}

	//-------------------
	// 커넥션 얻기 
	//-------------------

	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon()-end

	//-------------
	// 글쓰기 
	//-------------
	public void insertNotice(NoticeDTO dto){
		try{

			con=getCon();//커넥션 얻기 
			sql="insert into notice(no,title,content,writer,startDate)";
			sql=sql+" values(0,?,?,?,NOW())";

			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());

			pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("insertNotice()예외 :"+ex);
		}finally{
			try{
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

	}//insertNotice()-end

	//-----------------
	// 글 갯수 (페이지처리,블럭처리)
	//-----------------
	public int getCount(){
		int cnt=0;
		try{
			con=getCon();//커넥션 얻기 
			pstmt=con.prepareStatement("select count(*) from notice") ;
			rs=pstmt.executeQuery();

			if(rs.next()){
				cnt=rs.getInt(1);//필드 번호 , 글 갯수
			}
		}catch(Exception ex){
			System.out.println("getCount() 예외 :"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return cnt;
	}//getCount()-end

	//---------------
	// 리스트
	//----------------
	public List getList(int start,int cnt){
		List<NoticeDTO> list=null;
		try{
			con=getCon();//커넥션 얻기 
			sql="select * from notice order by no desc limit ?,?";//no desc
			//limit ?,?
			//limit 시작위치, 갯수

			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
			pstmt.setInt(1, start-1);//mysql  0부터 
			pstmt.setInt(2, cnt);
			rs=pstmt.executeQuery();

			/*while(rs.next()){
				//rs내용을 dto에 담는다 , dto를 list에 담는다  return list
				list=new ArrayList<NoticeDTO>();
				do{
					NoticeDTO dto=new NoticeDTO();

					dto.setNo(rs.getInt("no"));//no
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setWriter(rs.getString("writer"));//writer
					dto.setStartDate(rs.getTimestamp("startDate"));//년월일 시분초
					dto.setReadcount(rs.getInt("readcount"));

					list.add(dto);//****

				}while(rs.next());

			}//while-end
			*/
			list=new ArrayList<NoticeDTO>();
			while(rs.next()){
				//rs내용을 dto에 담는다 , dto를 list에 담는다  return list
				NoticeDTO dto=new NoticeDTO();

				dto.setNo(rs.getInt("no"));//no
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));//writer
				dto.setStartDate(rs.getTimestamp("startDate"));//년월일 시분초
				dto.setReadcount(rs.getInt("readcount"));

				list.add(dto);//****

			}//while-end
				
			 
		}catch(Exception ex){
			System.out.println("getList() 예외 :"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return list;//*************

	}//getList()-end

	//-------------------
	// 조횟수 증가, 글내용 보기 
	//-------------------
	public NoticeDTO getNotice(int no){
		NoticeDTO dto=null;
		try{
			con=getCon();//커넥션 얻기 

			//조횟수 증가 
			sql="update notice set readcount=readcount+1 where no="+no;
			pstmt=con.prepareStatement(sql);//생성자 인자 들어간다 
			pstmt.executeUpdate();
			//조횟수 증가  끝

			//글내용보기
			pstmt=con.prepareStatement("select * from notice where no="+no);
			rs=pstmt.executeQuery();

			//rs내용을 dto담는다.  return dto
			if(rs.next()){
				dto=new NoticeDTO();

				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setStartDate(rs.getDate("startDate"));//년월일
				//dto.setStartDate(rs.getTimestamp("startDate"));//년월일 시분초
				dto.setReadcount(rs.getInt("readcount"));
			}//if-end
		}catch(Exception ex){
			System.out.println("getBoard() 예외 :"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return dto;
	}//getBoard()-end
	
	//-----------------
	// 글 수정 폼 
	//-----------------
	public NoticeDTO getUpdate(int no){
		
		NoticeDTO dto=null;
		try{
			con=getCon();//커넥션 얻기 
			pstmt=con.prepareStatement("select * from notice where no="+no);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new NoticeDTO();
				
				dto.setNo(rs.getInt("no"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setWriter(rs.getString("writer"));
				dto.setStartDate(rs.getTimestamp("startDate"));
				dto.setReadcount(rs.getInt("readcount"));
			}//if-end
			
		}catch(Exception ex){
			System.out.println("getUpdate() 예외 :"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return dto;
	}//getUpdate()-end
	//-----------------
	// DB 글수정 
	//-----------------
	public int updateNotice(NoticeDTO dto){
		
		int x=-100;
		try{
			con=getCon();//커넥션 얻기 
			pstmt=con.prepareStatement("select * from notice where no="+dto.getNo());
			rs=pstmt.executeQuery();
			
			if(rs.next()){//no에 해당 하는 글이 존재 하면 글 수정 
				sql="update notice set writer=?, title=?, content=?, updateDate=NOW() where no="+dto.getNo();
				pstmt=con.prepareStatement(sql);
				
				pstmt.setString(1,dto.getWriter());
				pstmt.setString(2,dto.getTitle());
				pstmt.setString(3,dto.getContent());
				
				pstmt.executeUpdate();
				x=1;
			}else{//글수정 실패 
				x=-1;
			}
		}catch(Exception ex){
			System.out.println("updateNotice()예외 :"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return x;
	}//updateNotice()-end
	
	//------------------
	// 공지사항 삭제 
	//------------------
	
	public void deleteNotice(int no){
		try{
			con=getCon();//커넥션 얻기
			pstmt=con.prepareStatement("delete from notice where no="+no);
			pstmt.executeUpdate();//쿼리 실행 
			
		}catch(Exception ex){
			System.out.println("deleteNotice() 예외 :"+ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
	}//deleteNotice()-end
}//class-end
