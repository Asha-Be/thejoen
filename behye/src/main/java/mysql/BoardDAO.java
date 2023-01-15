package mysql;
import java.sql.*; //Connection, Statement,praparedStatement,Result

import javax.sql.*;
import javax.naming.*; //Context

import mysql.BoardDTO;
import java.util.*;


public class BoardDAO{

		Connection con = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "";
	
		private BoardDAO(){}
			private static BoardDAO instance = new BoardDAO();
			
			public static BoardDAO getInstance(){ //JSP에서 사용할 메서드
				return instance;
			}//pu.st.bo.
		
		//커넥션 얻기
			
			private Connection getCon() throws Exception {
				Context ct = new InitialContext();
				DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
				
			//** 리턴잇는지확인하기
				return ds.getConnection();
				}//get-end
			
			//------------글쓰기
			//답글쓰기
			//---------
			
			public void insertBoard(BoardDTO dto){
				//이때 보내준 데이터
				//글 내용 보기 content.jsp 에 답그 ㄹ쓰기 할때 보내준 데이터
				
				int num = dto.getNum();
				int ref = dto.getRef();
				int ordNo = dto.getOrdNo();
				int levelNo = dto.getLevelNo();
				
				int number = 0;//글 그룹처리
				
				try{
					con = getCon();//커넥션 받기
					pstmt = con.prepareStatement("select max(num) from qna");//최대 글번호 받기
					rs = pstmt.executeQuery();
					if(rs.next()){ //글이 존재 하면.
							number = rs.getInt(1)+1;//1은 필드 번호 //최대 글번호 +1  ref = number
							
					}else{//처음 글일대
						
						number = 1; //맨 처음거엔 1
								
					}//else-end
					
					if(num != 0){
						//답글이면.
						//답글 띄워넣기 위치 확보
						sql = "update qna set ordNo = ordNo+1 where ref=? and ordNo>?";
						pstmt = con.prepareStatement(sql);//생성시 인자 들어간다.
						
						pstmt.setInt(1, ref);
						pstmt.setInt(2, ordNo);
						pstmt.executeUpdate();
						
						ordNo=ordNo+1;
						levelNo=levelNo+1;
						
					}else {
						//원글이면
						
						ref=number;
						ordNo = 0;
						levelNo = 0;
						
						
					}//else-end
					
					sql = "insert into qna(num,writer,title,content,userid,hit,ref,ordNo,levelNo)";
					sql = sql + "values(0,?,?,?,?,?,?,?,?)";
					
					pstmt=con.prepareStatement(sql);
					//생성시 인자들어감을 안햇구낭
					
					pstmt.setString(1, dto.getWriter());
					pstmt.setString(2, dto.getTitle());
					pstmt.setString(3, dto.getContent());
					pstmt.setString(4, dto.getUserid());
					
					pstmt.setInt(5, dto.getHit());
					pstmt.setInt(6, ref);
					pstmt.setInt(7, ordNo);
					pstmt.setInt(8, levelNo);
					
					
					pstmt.executeUpdate(); //쿼리난또랴
					
				}catch(Exception ex){
					System.out.println("board dao 105 insertBoard() 예외 : " + ex);
				}finally{
					try{
						if(rs != null){rs.close();}
						if(pstmt != null){pstmt.close();}
						if(con != null){con.close();}
					}catch(Exception ex2){}
				}//finally-end
				
			}//insert-end
			
			//------------------------
			
			//글 갯수
			
			//--------------------
			
			 public int getCount(){
			      int cnt=0;
			      try{
			         con=getCon();//커넥션 얻기 
			         pstmt=con.prepareStatement("select count(*) from qna") ;
			         rs=pstmt.executeQuery();
			         
			         if(rs.next()){
			            cnt=rs.getInt(1);//필드 번호 
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

			//----------
			//리스트
			//------------
			  public List getList(int start,int cnt){
			      List<BoardDTO> list=null;

				try{
					con = getCon(); //커넥션 얻기
					
					sql = "select * from qna order by ref desc, ordNo asc limit ?,?";
					//limit ?,?
					//limit 시작 위치, 갯수 ? 부터 ?까지
					
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,  start-1); //mysql 은 0에서 부터
					pstmt.setInt(2, cnt);
					rs=pstmt.executeQuery();//쿼리실행
					
					while (rs.next()){
						//rs내용을 dto에 담는다. dto를 list에 담는다.
						//list를 리턴한다.
						
						list = new ArrayList<BoardDTO>(); //객체생 성
						do{
								BoardDTO dto = new BoardDTO();
								
								dto.setNum(rs.getInt(1)); //num
								dto.setWriter(rs.getString(2)); //write
								//맨날 일케 적을순없어!
								dto.setTitle(rs.getString("title"));
								dto.setContent(rs.getString("content"));
								dto.setUserid(rs.getString("userid"));
								//이게더귀찮지않나??
								
								
							//	System.out.println(rs.getTimestamp("regdate"));
							//	System.out.println(rs.getDate("regdate"));
							//	System.out.println(rs.getString("regdate"));
								
								//NOW()연월일시분초
								//curdate() : 년월일
								//sysdate : 오라클
								
								dto.setHit(rs.getInt("hit"));
								dto.setRef(rs.getInt("ref"));
								dto.setOrdNo(rs.getInt("ordNo"));
								dto.setLevelNo(rs.getInt("levelNo"));
								
								list.add(dto);
								
						}while(rs.next());
						
						
					}//while-end
				}catch(Exception ex){
					System.out.println("getlist 예외:" + ex);
				}finally{
					 try{
				            if(rs!=null){rs.close();}
				            if(pstmt!=null){pstmt.close();}
				            if(con!=null){con.close();}
				         }catch(Exception ex2){}
					
				}//finally-end
				
				
				return list;
			}//getlist-end
			  
			  //--------------------------
			  //조회수 증가, 글내용 보기
			  //-------------------------
			  
			  public BoardDTO getBoard(int num){
				  BoardDTO dto = null;
				  try{
					  con = getCon(); //커넥션 얻기
					  
					  //조회수 증가
					  sql="update qna set readcount=readcount+1 where num=" + num;
					  pstmt=con.prepareStatement(sql);
					  pstmt.executeUpdate();
					  
					  //글 내용 보기
					  
					  pstmt = con.prepareStatement("select * from qna where num=" + num);
					  rs = pstmt.executeQuery();
					  
					  
					  //rs내용을 dto 에 담기
					  //그리고, returndto한다.
					  
					  if(rs.next()){
						  dto = new BoardDTO();
						  
						  dto.setNum(rs.getInt("num"));
						  dto.setWriter(rs.getString("writer"));
						  dto.setTitle(rs.getString("title"));
						  dto.setContent(rs.getString("content"));
						  dto.setUserid(rs.getString("userid"));
						  dto.setRegdate(rs.getString("regdate"));
						  
						  dto.setHit(rs.getInt("hit"));
						  
						  dto.setRef(rs.getInt("ref"));
						  dto.setOrdNo(rs.getInt("ordNo"));
						  dto.setLevelNo(rs.getInt("levelNo"));
						  
					  }//if-end
				  }catch(Exception ex){
					  System.out.println("getBoard()예외 :" + ex);
				  }finally{
					  try{
				            if(rs!=null){rs.close();}
				            if(pstmt!=null){pstmt.close();}
				            if(con!=null){con.close();}
				         }catch(Exception ex2){}
				  }//finally-end
				  return dto;
			  }//getBoard-end
			
			 //-----------------------------
			  //글 수정 폼//
			  //----------------
			  
			  public BoardDTO getUpdata(int num){
				  BoardDTO dto = null;
				  String dbid = "";
				  try{
					  con = getCon();
					  pstmt = con.prepareStatement("select * from qna where num =" + num) ;
					  pstmt.setInt(1,  dto.getNum());
					  rs = pstmt.executeQuery();
					  
					  if(rs.next()){
						  //rs-내용을 dto에담고 dto를 리턴한다.
						  
						  dto = new BoardDTO();
						  dto.setNum(rs.getInt("num"));
						  
						  dto.setWriter(rs.getString("writer"));
						  dto.setTitle(rs.getString("title"));
						  dto.setContent(rs.getString("content"));
						  dto.setUserid(rs.getString("userid"));
						  
						  dto.setRegdate(rs.getTimestamp("regdate"));
						  
						  dto.setHit(rs.getInt("hit"));
						  dto.setRef(rs.getInt("ref"));
						  dto.setOrdNo(rs.getInt("re_step"));
						  dto.setLevelNo(rs.getInt("levelNo"));
						  
					  }//if-end
				  }catch(Exception ex){
					  System.out.println("board dao getupdate 예외 :" + ex);
				  }finally{
					  try{
				            if(rs!=null){rs.close();}
				            if(pstmt!=null){pstmt.close();}
				            if(con!=null){con.close();}
				         }catch(Exception ex2){}					 
				  }//finally-end
				  return dto;
			  }//getupdate-end
			  //------------------------------
			  //DB글수정
			  
			 //-------------------------
			  
			  public int updateBoard(BoardDTO dto){
				  
				  int x =- 100;
				  String dbid = " ";
				  try{
					  con = getCon();
					  pstmt = con.prepareStatement("select userid from qna where num=?");
					  pstmt.setInt(1, dto.getNum());
					  rs = pstmt.executeQuery();
					  
					  if(rs.next()){
						  dbid = rs.getString("userid");
						  if(dto.getUserid().equals(dbid)){
							  sql = "update qna set writer=?,title=?,content=? where num=?";
							  pstmt = con.prepareStatement(sql);
							  
							  pstmt.setString(1, dto.getWriter());
							  pstmt.setString(2, dto.getTitle());
							  pstmt.setString(3, dto.getContent());
							  pstmt.setInt(4, dto.getNum());
							  
							  pstmt.executeUpdate();
							  
							  x=1;
							  
							  
						  }else{
							  x=-1;
						  }
					  }//if-end
					  
					  
				  }catch(Exception ex){
					  System.out.println("updateBoard() 예외:" + ex);
				  }finally{
					  try{
				            if(rs!=null){rs.close();}
				            if(pstmt!=null){pstmt.close();}
				            if(con!=null){con.close();}
				         }catch(Exception ex2){}					 
				  }//finally-end
				  return x;
			  }//up.bo-end
			
			  
			//------------
			   //글삭제
			   //------------ //쌤이써준거
			  
			  public int deleteArticle(int num, String pw) {
				  //암호가 일치하면 글 삭제
				  
				  String dbUserid = "";
				  int x = -100;
				  try{
					  con=getCon();
			         pstmt=con.prepareStatement("select pw from qna where num = ?");//생성시 인자 들어간다 
			         rs=pstmt.executeQuery();
					  
			         if(rs.next()){
			        	 
			        	 dbUserid = rs.getString("userid");
			        	 if(pw.equals(dbUserid)) {
			        		 //번호가 일치하면 글 삭제
			        		 pstmt = con.prepareStatement("delete from qna where num="+num);
			        		 pstmt.executeUpdate();
			        		 x = 1;
			        	 }else{
			        		 x = -1;
			        	 }//else-end
			        	 
			         }//if-end
			         
				  }catch(Exception ex){
					  System.out.println("deleteArticle 예외 :" + ex );
				  }finally{
					  try{
						     	if(rs!=null){rs.close();}
					            if(pstmt!=null){pstmt.close();}
					            if(con!=null){con.close();}
					  }catch(Exception ex2){}
				  }//finally-end
				  
				  return x;
			  }//deleteAr-end
		
//DAO : 비즈니스 트릭
}//classend
