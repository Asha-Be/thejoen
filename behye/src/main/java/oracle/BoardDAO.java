package oracle;
import java.sql.*; //Connection, Statement,praparedStatement,Result

import javax.sql.*;
import javax.naming.*; //Context

import java.util.*; //list.arraylist

//DAO : �����Ͻ� 
	
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
				DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/oracle"); //***
				
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
				int re_step = dto.getRe_step();
				int re_level = dto.getRe_level();
				
				int number = 0;//�� �׷�ó��
				
				try{
					con = getCon();//Ŀ�ؼ� �ޱ�
					pstmt = con.prepareStatement("select max(num) from board");//�ִ� �۹�ȣ �ޱ�
					rs = pstmt.executeQuery();
					if(rs.next()){ //���� ���� �ϸ�.
							number = rs.getInt(1)+1;//1�� �ʵ� ��ȣ //�ִ� �۹�ȣ +1  ref = number
							
					}else{//ó�� ���ϴ�
						
						number = 1; //�� ó���ſ� 1
								
					}//else-end
					
					if(num != 0){
						//����̸�.
						//��� ����ֱ� ��ġ Ȯ��
						sql = "update board set re_step = re_step + 1 where ref=? and re_step>?";
						pstmt = con.prepareStatement(sql);//������ ���� ����.
						
						pstmt.setInt(1, ref);
						pstmt.setInt(2, re_step);
						pstmt.executeUpdate();
						
						re_step=re_step+1;
						re_level=re_level+1;
						
					}else {
						//�����̸�
						
						ref=number;
						re_step = 0;
						re_level = 0;
						
						
					}//else-end
					
//				sql = "insert into board(num,writer,subject,content,pw,regdate,ref,re_step,re_level,ip)";
////					sql = sql + "values(0,?,?,?,?,NOW(),?,?,?,?)";
//					
//					sql = sql + "values(board_seq,NEXTVAL,?,?,?,?,sysdate,?,?,?)";
					
					 sql="insert into board(num,writer,subject,content,pw,regdate,ref,re_step,re_level,ip) ";
			         sql=sql+"values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,?)";
					//NOW: ����� �ú���
					//curdate () : �����
					//sysdate : ����� �ú���
					
					pstmt=con.prepareStatement(sql);
					//������ ���ڵ��� ���ޱ���
					
					pstmt.setString(1, dto.getWriter());
					pstmt.setString(2, dto.getSubject());
					pstmt.setString(3, dto.getContent());
					pstmt.setString(4, dto.getPw());
					
					pstmt.setInt(5, ref);
					pstmt.setInt(6, re_step);
					pstmt.setInt(7, re_level);
					
					pstmt.setString(8, dto.getIp());
					
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
			         pstmt=con.prepareStatement("select count(*) from board") ;
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
					
					//sql = "select * from board order by ref desc, re_step asc limit ?,?";
					//limit ?,?
					//limit ���� ��ġ, ���� ? ���� ?����
					
//					pstmt=con.prepareStatement(sql);
//					pstmt.setInt(1,  start-1); //mysql �� 0���� ����
//					pstmt.setInt(2, cnt);
//					rs=pstmt.executeQuery();//��������
					//mysql
					
					//oracle
					//***��������12345
					sql="select * from (select rownum rnum , num, writer, subject, content, pw, regdate, readcount, ref, re_step, re_level, ip from ( select * from board order by ref desc, re_step asc)) where rnum>=? and rnum<?";
					
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, start);//������ġ
					pstmt.setInt(2, start+cnt);//start���� end����//����ġ
					rs=pstmt.executeQuery();
					
					while (rs.next()){
						//rs������ dto�� ��´�. dto�� list�� ��´�.
						//list�� �����Ѵ�.
						
						list = new ArrayList<BoardDTO>(); //��ü�� ��
						do{
								BoardDTO dto = new BoardDTO();
								
								dto.setNum(rs.getInt(1)); //num
								dto.setWriter(rs.getString(2)); //write
								//�ǳ� ���� ����������!
								dto.setSubject(rs.getString("subject"));
								dto.setContent(rs.getString("content"));
								dto.setPw(rs.getString("pw"));
								//�̰Դ��������ʳ�??
								
								dto.setRegdate(rs.getTimestamp("regdate"));
								//��.��.�� ..����ǥ���ε�
								
							//	System.out.println(rs.getTimestamp("regdate"));
							//	System.out.println(rs.getDate("regdate"));
							//	System.out.println(rs.getString("regdate"));
								
								//NOW()�����Ͻú���
								//curdate() : �����
								//sysdate : ����Ŭ
								
								dto.setReadcount(rs.getInt("readcount"));
								dto.setRef(rs.getInt("ref"));
								dto.setRe_step(rs.getInt("re_step"));
								dto.setRe_level(rs.getInt("re_level"));
								dto.setIp(rs.getString("ip"));
								
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
					  sql="update board set readcount=readcount+1 where num=" + num;
					  pstmt=con.prepareStatement(sql);
					  pstmt.executeUpdate();
					  
					  //�� ���� ����
					  
					  pstmt = con.prepareStatement("select * from board where num=" + num);
					  rs = pstmt.executeQuery();
					  
					  //rs������ dto �� ���
					  //�׸���, returndto�Ѵ�.
					  
					  if(rs.next()){
						  dto = new BoardDTO();
						  
						  dto.setNum(rs.getInt("num"));
						  dto.setWriter(rs.getString("writer"));
						  dto.setSubject(rs.getString("subject"));
						  dto.setContent(rs.getString("content"));
						  dto.setPw(rs.getString("pw"));
						  dto.setRegdate(rs.getTimestamp("regdate"));
						  dto.setReadcount(rs.getInt("readcount"));
						  dto.setRef(rs.getInt("ref"));
						  dto.setRe_step(rs.getInt("re_step"));
						  dto.setRe_level(rs.getInt("re_level"));
						  dto.setIp(rs.getString("ip"));
						  
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
				  
				  try{
					  con = getCon();
					  pstmt = con.prepareStatement("select * from board where num =" + num) ;
					  rs = pstmt.executeQuery();
					  
					  if(rs.next()){
						  //rs-������ dto����� dto�� �����Ѵ�.
						  
						  dto = new BoardDTO();
						  dto.setNum(rs.getInt("num"));
						  
						  dto.setWriter(rs.getString("writer"));
						  dto.setSubject(rs.getString("subject"));
						  dto.setContent(rs.getString("content"));
						  dto.setPw(rs.getString("pw"));
						  dto.setRegdate(rs.getTimestamp("regdate"));
						  dto.setReadcount(rs.getInt("readcount"));
						  dto.setRef(rs.getInt("ref"));
						  dto.setRe_step(rs.getInt("re_step"));
						  dto.setRe_level(rs.getInt("re_level"));
						  
						  dto.setIp(rs.getString("ip"));
						  
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
				  String dbpw = " ";
				  try{
					  con = getCon();
					  pstmt = con.prepareStatement("select pw from board where num=?");
					  pstmt.setInt(1, dto.getNum());
					  rs = pstmt.executeQuery();
					  
					  if(rs.next()){
						  dbpw = rs.getString("pw");
						  if(dto.getPw().equals(dbpw)){
							  sql = "update board set writer=?,subject=?,content=? where num=?";
							  pstmt = con.prepareStatement(sql);
							  
							  pstmt.setString(1,dto.getWriter());
							  pstmt.setString(2,dto.getSubject());
							  pstmt.setString(3,dto.getContent());
							  pstmt.setInt(4,dto.getNum());
							  
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
				  
				  String dbpw = "";
				  int x = -100;
				  try{
					  con=getCon();
			         pstmt=con.prepareStatement("select pw from board where num = ?");//������ ���� ���� 
			         rs=pstmt.executeQuery();
					  
			         if(rs.next()){
			        	 
			        	 dbpw = rs.getString("pw");
			        	 if(pw.equals(dbpw)){
			        		 //��ȣ�� ��ġ�ϸ� �� ����
			        		 pstmt = con.prepareStatement("delete from board where num="+num);
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
