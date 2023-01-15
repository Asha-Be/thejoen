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
			
			public static BoardDAO getInstance(){ //JSP���� ����� �޼���
				return instance;
			}//pu.st.bo.
		
		//Ŀ�ؼ� ���
			
			private Connection getCon() throws Exception {
				Context ct = new InitialContext();
				DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
				
			//** �����մ���Ȯ���ϱ�
				return ds.getConnection();
				}//get-end
			
			//------------�۾���
			//��۾���
			//---------
			
			public void insertBoard(BoardDTO dto){
				//�̶� ������ ������
				//�� ���� ���� content.jsp �� ��� ������ �Ҷ� ������ ������
				
				int num = dto.getNum();
				int ref = dto.getRef();
				int ordNo = dto.getOrdNo();
				int levelNo = dto.getLevelNo();
				
				int number = 0;//�� �׷�ó��
				
				try{
					con = getCon();//Ŀ�ؼ� �ޱ�
					pstmt = con.prepareStatement("select max(num) from qna");//�ִ� �۹�ȣ �ޱ�
					rs = pstmt.executeQuery();
					if(rs.next()){ //���� ���� �ϸ�.
							number = rs.getInt(1)+1;//1�� �ʵ� ��ȣ //�ִ� �۹�ȣ +1  ref = number
							
					}else{//ó�� ���ϴ�
						
						number = 1; //�� ó���ſ� 1
								
					}//else-end
					
					if(num != 0){
						//����̸�.
						//��� ����ֱ� ��ġ Ȯ��
						sql = "update qna set ordNo = ordNo+1 where ref=? and ordNo>?";
						pstmt = con.prepareStatement(sql);//������ ���� ����.
						
						pstmt.setInt(1, ref);
						pstmt.setInt(2, ordNo);
						pstmt.executeUpdate();
						
						ordNo=ordNo+1;
						levelNo=levelNo+1;
						
					}else {
						//�����̸�
						
						ref=number;
						ordNo = 0;
						levelNo = 0;
						
						
					}//else-end
					
					sql = "insert into qna(num,writer,title,content,userid,hit,ref,ordNo,levelNo)";
					sql = sql + "values(0,?,?,?,?,?,?,?,?)";
					
					pstmt=con.prepareStatement(sql);
					//������ ���ڵ��� ���ޱ���
					
					pstmt.setString(1, dto.getWriter());
					pstmt.setString(2, dto.getTitle());
					pstmt.setString(3, dto.getContent());
					pstmt.setString(4, dto.getUserid());
					
					pstmt.setInt(5, dto.getHit());
					pstmt.setInt(6, ref);
					pstmt.setInt(7, ordNo);
					pstmt.setInt(8, levelNo);
					
					
					pstmt.executeUpdate(); //�������Ƿ�
					
				}catch(Exception ex){
					System.out.println("board dao 105 insertBoard() ���� : " + ex);
				}finally{
					try{
						if(rs != null){rs.close();}
						if(pstmt != null){pstmt.close();}
						if(con != null){con.close();}
					}catch(Exception ex2){}
				}//finally-end
				
			}//insert-end
			
			//------------------------
			
			//�� ����
			
			//--------------------
			
			 public int getCount(){
			      int cnt=0;
			      try{
			         con=getCon();//Ŀ�ؼ� ��� 
			         pstmt=con.prepareStatement("select count(*) from qna") ;
			         rs=pstmt.executeQuery();
			         
			         if(rs.next()){
			            cnt=rs.getInt(1);//�ʵ� ��ȣ 
			         }
			      }catch(Exception ex){
			         System.out.println("getCount() ���� :"+ex);
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
			//����Ʈ
			//------------
			  public List getList(int start,int cnt){
			      List<BoardDTO> list=null;

				try{
					con = getCon(); //Ŀ�ؼ� ���
					
					sql = "select * from qna order by ref desc, ordNo asc limit ?,?";
					//limit ?,?
					//limit ���� ��ġ, ���� ? ���� ?����
					
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1,  start-1); //mysql �� 0���� ����
					pstmt.setInt(2, cnt);
					rs=pstmt.executeQuery();//��������
					
					while (rs.next()){
						//rs������ dto�� ��´�. dto�� list�� ��´�.
						//list�� �����Ѵ�.
						
						list = new ArrayList<BoardDTO>(); //��ü�� ��
						do{
								BoardDTO dto = new BoardDTO();
								
								dto.setNum(rs.getInt(1)); //num
								dto.setWriter(rs.getString(2)); //write
								//�ǳ� ���� ����������!
								dto.setTitle(rs.getString("title"));
								dto.setContent(rs.getString("content"));
								dto.setUserid(rs.getString("userid"));
								//�̰Դ��������ʳ�??
								
								
							//	System.out.println(rs.getTimestamp("regdate"));
							//	System.out.println(rs.getDate("regdate"));
							//	System.out.println(rs.getString("regdate"));
								
								//NOW()�����Ͻú���
								//curdate() : �����
								//sysdate : ����Ŭ
								
								dto.setHit(rs.getInt("hit"));
								dto.setRef(rs.getInt("ref"));
								dto.setOrdNo(rs.getInt("ordNo"));
								dto.setLevelNo(rs.getInt("levelNo"));
								
								list.add(dto);
								
						}while(rs.next());
						
						
					}//while-end
				}catch(Exception ex){
					System.out.println("getlist ����:" + ex);
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
			  //��ȸ�� ����, �۳��� ����
			  //-------------------------
			  
			  public BoardDTO getBoard(int num){
				  BoardDTO dto = null;
				  try{
					  con = getCon(); //Ŀ�ؼ� ���
					  
					  //��ȸ�� ����
					  sql="update qna set readcount=readcount+1 where num=" + num;
					  pstmt=con.prepareStatement(sql);
					  pstmt.executeUpdate();
					  
					  //�� ���� ����
					  
					  pstmt = con.prepareStatement("select * from qna where num=" + num);
					  rs = pstmt.executeQuery();
					  
					  
					  //rs������ dto �� ���
					  //�׸���, returndto�Ѵ�.
					  
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
					  System.out.println("getBoard()���� :" + ex);
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
			  //�� ���� ��//
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
						  //rs-������ dto����� dto�� �����Ѵ�.
						  
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
					  System.out.println("board dao getupdate ���� :" + ex);
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
			  //DB�ۼ���
			  
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
					  System.out.println("updateBoard() ����:" + ex);
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
			   //�ۻ���
			   //------------ //���̽��ذ�
			  
			  public int deleteArticle(int num, String pw) {
				  //��ȣ�� ��ġ�ϸ� �� ����
				  
				  String dbUserid = "";
				  int x = -100;
				  try{
					  con=getCon();
			         pstmt=con.prepareStatement("select pw from qna where num = ?");//������ ���� ���� 
			         rs=pstmt.executeQuery();
					  
			         if(rs.next()){
			        	 
			        	 dbUserid = rs.getString("userid");
			        	 if(pw.equals(dbUserid)) {
			        		 //��ȣ�� ��ġ�ϸ� �� ����
			        		 pstmt = con.prepareStatement("delete from qna where num="+num);
			        		 pstmt.executeUpdate();
			        		 x = 1;
			        	 }else{
			        		 x = -1;
			        	 }//else-end
			        	 
			         }//if-end
			         
				  }catch(Exception ex){
					  System.out.println("deleteArticle ���� :" + ex );
				  }finally{
					  try{
						     	if(rs!=null){rs.close();}
					            if(pstmt!=null){pstmt.close();}
					            if(con!=null){con.close();}
					  }catch(Exception ex2){}
				  }//finally-end
				  
				  return x;
			  }//deleteAr-end
		
//DAO : ����Ͻ� Ʈ��
}//classend
