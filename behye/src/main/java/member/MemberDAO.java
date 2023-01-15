package member;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
//DAO :����Ͻ� ���� ó��
public class MemberDAO {
	
	//�̱��� ��ü ��� : a�޸� ���� ȿ��
		private static MemberDAO instance = new MemberDAO();
		
		//������ �ܺο��� ��ü ������ ���ϵ��� �����.
		public MemberDAO(){}
		
		//JSP���� ����� �޼���.
		public static MemberDAO getInstance(){
			return instance;
		}
		
		//--------------
		//Ŀ�ؼ� Ǯ
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
		//id �ߺ� üũ��

		public int confirmID(String id){
			int x=-100;
			
			try{
				con = getCon();//Ŀ�ؼ� ���
				pstmt = con.prepareStatement("select id from member where id=?");
				pstmt.setString(1, id);
				
				rs=pstmt.executeQuery();//resultset�� select������ �޾� ��ġquery����
				
				if(rs.next()){
					x = 1;
					//�̹� ������� ���̵�
				}else{
					x =-1;
					//��� �� ������ ����ũ
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
		
		//-------------------ȸ������
		
		public void insertMember(MemberDTO dto){
			
			//insert�� ���� ���Ϲ������� ��� void
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
				
			}finally{//���� �߻��� ������� ������ ó���Ѵ�. �տ� return ���� �־ ������ ����ȴ�.
		            try{
		               if(rs != null){rs.close();}
		               if(pstmt != null){pstmt.close();}
		               if(con != null){con.close();}
		            }catch(Exception ex2){}
		         }//finally
		}//insertMember
		
		//-------------�α���
		
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
		            if(pw.equals(dbpw)){//��ȣ�� ��ġ
		               x=1;
				            }else{//��ȣ Ʋ����
				               x=0;
				            }//else-end
		            
		         }else{//���� id
		            x=-1;
		         }//else-end
		      }catch(Exception ex){
		         System.out.println("userCheck()-����"+ex);
		      }finally{
		         try{
		            if(rs!=null){rs.close();}
		            if(pstmt!=null){pstmt.close();}
		            if(con!=null){con.close();}
		         }catch(Exception ex2){}
		      }//finally-end
		      return x;
		   }//userCheck()-end

		 //----------��ȣ üũ
		 public int pwCheck(String id, String pw){
			 int x = -100;
			 try{
				    con=getCon();
			         pstmt=con.prepareStatement("select pw from member where id=? and pw=?");
			         pstmt.setString(1, id);
			         pstmt.setString(2, pw);
			         rs=pstmt.executeQuery();
			         
			         if(rs.next()){
			        	 x = 1 ; //��ȣ Ȯ���Ѱ�
			         }else{
			        	 x =-1; //���� id�� ��ȣ
			         }
			         
			 }catch(Exception ex){
				 System.out.println("pwCheck() ����: "+ex);
			 }finally{
		         try{
		            if(rs!=null){rs.close();}
		            if(pstmt!=null){pstmt.close();}
		            if(con!=null){con.close();}
		         }catch(Exception ex2){}
		      }//finally-end
		      return x;
			 
		 }//pwcheck-end
		 
		 //----------------������Ʈ
		 
		 public MemberDTO getMember (String id){
			 MemberDTO dto = null;
			 try{
				 
				 con=getCon();
				 pstmt=con.prepareStatement("select * from member where id=?");
				 pstmt.setString(1, id);
				 rs=pstmt.executeQuery();
				 
				 if(rs.next()){
					 
					 //rs������ dto �� �ְ� dto�� return�Ѵ� 
					 
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
		 
		 
		 //--------------DB������ ����
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
				 System.out.println("updateMember ���� :" + ex);
			 }finally{
		         try{
			            if(rs!=null){rs.close();}
			            if(pstmt!=null){pstmt.close();}
			            if(con!=null){con.close();}
			         }catch(Exception ex2){}
			      }//finally-end
			 
			 
		 }//updatemember-end
		
		 //--------------Ż��
		 
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
						 //��ȣ�� �Է¾�ȣ�� ��ġ�ϸ� �� ����
						 pstmt=con.prepareStatement("delete from member where id=?");
						 pstmt.setString(1, id);
						 pstmt.executeUpdate();
						 x=1;
					 }else{
						 x =-1; //��ȣ Ʋ��
					 }//else-end
					 
				 }//if-end
				 
			 }catch(Exception ex){
				 
		System.out.println("deleteMember ���� :" + ex);
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
