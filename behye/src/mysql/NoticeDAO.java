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
			
			
			//커넥션 얻기
			
			private Connection getCon() throws Exception{
				Context ct = new InitialContext();
				DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/notice");
				
				return ds.getConnection();
			}//getcon-end
			
			
			//--------글쓰기
			
			public void insertNotice(NoticeDTO dto){
				
				
				int no = dto.getNo();
				
				
				try{
					con = getCon();
					pstmt = con.prepareStatement("select max(num) from notice");
					rs = pstmt.executeQuery();
					if(rs.next()){ //글이 존재 하면.
						no = rs.getInt(1)+1;//1은 필드 번호 //최대 글번호 +1  ref = number
						
				}else{//처음 글일대
					
					no = 1; //맨 처음거엔 1
							
				}//else-end
					
				
					
					sql="insert into notice(no,title,content,writer,startDate)";
					sql=sql+"values(0,?,?,?,NOW())";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, dto.getTitle());
					pstmt.setString(2, dto.getContent());
					pstmt.setString(3, dto.getWriter());
					
					
					
			}catch(Exception ex){
				System.out.println("Notice dao insertNotice 예외: " + ex);
			}finally{
				try{
					if(rs != null){rs.close();}
					if(pstmt != null){pstmt.close();}
					if(con != null){con.close();}
				}catch(Exception ex2){}
			}//finally-end
		
			}//insert-end

			
			//----------글 개수
			
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
					System.out.println("getCount() 예외 : "+ex);
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
