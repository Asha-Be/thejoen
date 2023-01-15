package notice;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;



public class NoticeDAO {
		Connection con = null;
		PreparedStatement pstmt = null; 
		Statement stmt = null;
		ResultSet rs =  null;
		String sql ="";
		
		private NoticeDAO(){}
			private static NoticeDAO instance = new NoticeDAO();
			
			public static NoticeDAO getInstance(){
				return instance;
			}//noticedao getin-end
			
			
			//Ŀ�ؼ� ���
			
			private Connection getCon() throws Exception{
				Context ct = new InitialContext();
				DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/notice");
				
				return ds.getConnection();
			}//getcon-end
			
			
			//--------�۾���
			
			public void insertNotice(NoticeDTO dto){
				
				
				int no = dto.getNo();
				
				
				try{
					con = getCon();
					pstmt = con.prepareStatement("select max(num) from notice");
					rs = pstmt.executeQuery();
					if(rs.next()){ //���� ���� �ϸ�.
						no = rs.getInt(1)+1;//1�� �ʵ� ��ȣ //�ִ� �۹�ȣ +1  ref = number
						
				}else{//ó�� ���ϴ�
					
					no = 1; //�� ó���ſ� 1
							
				}//else-end
					
				
					
					sql="insert into notice(no,title,content,writer,startDate)";
					sql=sql+"values(0,?,?,?,NOW())";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, dto.getTitle());
					pstmt.setString(2, dto.getContent());
					pstmt.setString(3, dto.getWriter());
					
					
					
			}catch(Exception ex){
				System.out.println("Notice dao insertNotice ����: " + ex);
			}finally{
				try{
					if(rs != null){rs.close();}
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
		
			}//insert-end

			
			//----------�� ����
			
			public int getCount(){
				int cnt = 0;
				
				try{
					con = getCon();
					pstmt=con.prepareStatement("select count(*) from notice");
					rs=pstmt.executeQuery();
					
					if(rs.next()){
						cnt = rs.getInt(1);
					}
				}catch(Exception ex){
					System.out.println("getCount() ���� : "+ex);
				}finally{
				try{
				    if(rs!=null){rs.close();}
		            if(pstmt!=null){pstmt.close();}
		            if(con!=null){con.close();}
		         }catch(Exception ex2){}
		      }//finally-end
		      
		      return cnt;
			}//getcount-end
}//NoticeDAO-end
