package member;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
//DAO :비즈니스 로직 처리
public class MemberDAO {
	
	//싱글톤 객체 사용 : a메모리 절약 효과
		private static MemberDAO instance = new MemberDAO();
		
		//생성자 외부에서 객체 생성을 못하도록 만든다.
		public MemberDAO(){}
		
		//JSP에서 사용할 메서드.
		public static MemberDAO getInstance(){
			return instance;
		}
		
		//--------------
		//커넥션 풀
		//-----------
		
		private Connection getCon() throws Exception{
			Context ct = new InitialContext();
			DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
			return ds.getConnection();
			
			

		
		}//con-end
		
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		//=============================
		//id 중복 체크ㅡ

		public int confirmID(String id){
			int x=-100;
			
			try{
				con = getCon();//커넥션 얻기
				pstmt = con.prepareStatement("select id from member where id=?");
				pstmt.setString(1, id);
				
				rs=pstmt.executeQuery();//resultset은 select에서만 받아 마치query같이
				
				if(rs.next()){
					x = 1;
					//이미 사용중인 아이디
				}else{
					x =-1;
					//사용 이 가능한 마이크
				}
				
			  }catch(Exception ex){
		            System.out.println("Notice dao insertNotice     : " + ex);
		            ex.printStackTrace();
		         }finally{
		            try{
		               if(rs != null){rs.close();}
		               if(pstmt != null){pstmt.close();}
		               if(con != null){con.close();}
		            }catch(Exception ex2){}
		         }//finally
			return x;
		         }//confirmID-end
		
		//-------------------회원가입
		
		public void insertMember(MemberDTO dto){
			
			//insert라 딱히 리턴받을일이 없어서 void
			try{
					con = getCon();
					pstmt = con.prepareStatement("insert into member values(?,?,?,?,?,?,?,?,NOW())");
					
					pstmt.setString(1, dto.getId());
					pstmt.setString(2,dto.getPw());
					pstmt.setString(3,dto.getName());
					pstmt.setString(4, dto.getEmail());
					pstmt.setString(5,dto.getTel());
					pstmt.setString(6,dto.getZipcode());
					pstmt.setString(7,dto.getAddr());
					pstmt.setString(8,dto.getAddr2());
					
					pstmt.executeUpdate();
					
			}catch(Exception ex){
				System.out.println("insertMemberDAO ex" + ex);
				
			}finally{//예외 발생과 상관없이 무조건 처리한다. 앞에 return 문이 있어도 무조건 실행된다.
		            try{
		               if(rs != null){rs.close();}
		               if(pstmt != null){pstmt.close();}
		               if(con != null){con.close();}
		            }catch(Exception ex2){}
		         }//finally
		}//insertMember
		
		//-------------로그인
		
		 public int userCheck(String id,String pw){
		      int x=-100;
		      String dbpw="";
		      try{
		         con=getCon();
		         pstmt=con.prepareStatement("select pw from member where id=?");
		         pstmt.setString(1, id);
		         rs=pstmt.executeQuery();
		         
		         if(rs.next()){
		            dbpw=rs.getString("pw");
		            if(pw.equals(dbpw)){//암호가 일치
		               x=1;
				            }else{//암호 틀릴때
				               x=0;
				            }//else-end
		            
		         }else{//없는 id
		            x=-1;
		         }//else-end
		      }catch(Exception ex){
		         System.out.println("userCheck()-예외"+ex);
		      }finally{
		         try{
		            if(rs!=null){rs.close();}
		            if(pstmt!=null){pstmt.close();}
		            if(con!=null){con.close();}
		         }catch(Exception ex2){}
		      }//finally-end
		      return x;
		   }//userCheck()-end

		 //----------암호 체크
		 public int pwCheck(String id, String pw){
			 int x = -100;
			 try{
				    con=getCon();
			         pstmt=con.prepareStatement("select pw from member where id=? and pw=?");
			         pstmt.setString(1, id);
			         pstmt.setString(2, pw);
			         rs=pstmt.executeQuery();
			         
			         if(rs.next()){
			        	 x = 1 ; //암호 확인한것
			         }else{
			        	 x =-1; //없는 id의 암호
			         }
			         
			 }catch(Exception ex){
				 System.out.println("pwCheck() 예외: "+ex);
			 }finally{
		         try{
		            if(rs!=null){rs.close();}
		            if(pstmt!=null){pstmt.close();}
		            if(con!=null){con.close();}
		         }catch(Exception ex2){}
		      }//finally-end
		      return x;
			 
		 }//pwcheck-end
		 
		 //----------------업데이트
		 
		 public MemberDTO getMember (String id){
			 MemberDTO dto = null;
			 try{
				 
				 con=getCon();
				 pstmt=con.prepareStatement("select * from member where id=?");
				 pstmt.setString(1, id);
				 rs=pstmt.executeQuery();
				 
				 if(rs.next()){
					 
					 //rs내용을 dto 에 넣고 dto를 return한다 
					 
					 dto = new MemberDTO();
					 dto.setId(rs.getString("id"));
					 dto.setPw(rs.getString("pw"));
					 dto.setName(rs.getString("name"));
					 dto.setEmail(rs.getString("email"));
					 dto.setTel(rs.getString("tel"));
					 dto.setZipcode(rs.getString("zipcode"));
					 dto.setAddr(rs.getString("addr"));
					 dto.setAddr2(rs.getString("addr2"));
					 dto.setRegdate(rs.getString("regdate"));
					 
				 }//if-end
				 
			 }catch(Exception ex){
				 System.out.println("getMember :" + ex);
			 }finally{
		         try{
		            if(rs!=null){rs.close();}
		            if(pstmt!=null){pstmt.close();}
		            if(con!=null){con.close();}
		         }catch(Exception ex2){}
		      }//finally-end
			 
			 
		      return dto;
		 }//getMember-end
		 
		 
		 //--------------DB내정보 수정
		 public void updateMember(MemberDTO dto){
			 try{
				 con=getCon();
				 sql = "update member set pw=?,name=?, email=?, tel=?, zipcode=?, addr=?, addr2=? where id=?";
				 pstmt = con.prepareStatement(sql);
				 
				 pstmt.setString(1, dto.getPw());
				 pstmt.setString(2, dto.getName());
				 pstmt.setString(3, dto.getEmail());
				 pstmt.setString(4, dto.getTel());
				 pstmt.setString(5, dto.getZipcode());
				 pstmt.setString(6, dto.getAddr());
				 pstmt.setString(7, dto.getAddr2());
				 pstmt.setString(8, dto.getId());
				 
				 pstmt.executeUpdate();
				 
				 
			 }catch(Exception ex){
				 System.out.println("updateMember 예외 :" + ex);
			 }finally{
		         try{
			            if(rs!=null){rs.close();}
			            if(pstmt!=null){pstmt.close();}
			            if(con!=null){con.close();}
			         }catch(Exception ex2){}
			      }//finally-end
			 
			 
		 }//updatemember-end
		
		 //--------------탈퇴
		 
		 public int deleteMember(String id, String pw){
			 int x = -100;
			 
			 try{
				 con=getCon();
				 pstmt = con.prepareStatement("select pw from member where id=?");
				 pstmt.setString(1,id);
				 rs = pstmt.executeQuery();
				 
				 if(rs.next()){
					 String dbpw = rs.getString("pw");
					 if(pw.equals(dbpw)){
						 //암호와 입력암호가 일치하면 글 삭제
						 pstmt=con.prepareStatement("delete from member where id=?");
						 pstmt.setString(1, id);
						 pstmt.executeUpdate();
						 x=1;
					 }else{
						 x =-1; //암호 틀림
					 }//else-end
					 
				 }//if-end
				 
			 }catch(Exception ex){
				 
		System.out.println("deleteMember 예외 :" + ex);
		 }finally{
	         try{
		            if(rs!=null){rs.close();}
		            if(pstmt!=null){pstmt.close();}
		            if(con!=null){con.close();}
		         }catch(Exception ex2){}
		      }//finally-end
			 
			 
			 return x;
		 }//deleteMember
		 
		}//class-end
