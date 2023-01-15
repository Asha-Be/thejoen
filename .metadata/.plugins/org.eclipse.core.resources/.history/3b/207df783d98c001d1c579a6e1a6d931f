package shopdb;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

import com.oreilly.servlet.*; //cos.jar
import com.oreilly.servlet.multipart.*; //cos.jar
import javax.servlet.http.*;//httpServletRequest request

//���� ���ε�
//jdk/jre/lib/ext/cos.jar
//WebContent/WEB-INF/lib/cos.jar ==> tomcat/lib/cos.jar �� ����.

//��ǰ ���ε� �Ҷ� cos.jar �ʿ���

//DAO : ����Ͻ� ����
public class ProductDAO {

	private ProductDAO(){}//������. �ܺο��� ��ü ���� ���ϰ�.
	
	private static ProductDAO instance = new ProductDAO(); //��ü ����
	
	//JSP���� ����� �޼�
	public static ProductDAO getInstance(){
		return instance;		
	}//productdeogetI-end
	
	//Ŀ�ؼ�---------------------------
	
	private Connection getCon() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}//getCon-end
	
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";

//-=============��ǰ ��� ����Ʈ----------------
	
	public List getGoodList(){
		List<ProductDTO> list=new ArrayList<ProductDTO>();
		
		try{
			con=getCon(); //Ŀ�ؼ� �޾ƿ���
			sql="select * from shop_info";
			stmt=con.createStatement();//statement ����
			rs=stmt.executeQuery(sql);//�������� < sql select �ϱ� query.
			
			while(rs.next()){
				//�ϳ��� �о�´�.
				//rs ������ dto�� ��� dto�� list�� �ִ´�. 
			
				ProductDTO dto = new ProductDTO();
				dto.setPro_no(rs.getInt("pro_no"));
				dto.setCode(rs.getString("code"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setDetail(rs.getString("detail"));
				dto.setComp(rs.getString("comp"));
				//dto.setRegdate(rs.getTimestamp("regdate"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setImage(rs.getString("image"));
				
				list.add(dto);
				
			}//while-end
			
		}catch(Exception ex){
			System.out.println("getGoodlist ����Ʈ : " + ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return list;		
	}//getGoodlist-end

	//--------------------------------

	//�󼼺���
	public ProductDTO getDetail(String code){
		ProductDTO dto=new ProductDTO();
		try{
			con=getCon();
			sql="select * from shop_info where code='"+code+"'";
			stmt=con.createStatement(); //stmt ����.
			rs=stmt.executeQuery(sql);//��������
			
			if(rs.next()){
				//rs�� ������  dto����� dto�� f����
				
				dto.setPro_no(rs.getInt("pro_no"));
				dto.setCode(rs.getString("code"));
				dto.setName(rs.getString("name"));
				
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				
				dto.setDetail(rs.getString("detail"));
				dto.setComp(rs.getString("comp"));
				
				dto.setRegdate(rs.getDate("regdate"));
				dto.setImage(rs.getString("image"));
			
			}//if-end
			
			
		}catch(Exception ex){
			System.out.println("getDetail ���� ex : " + ex);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return dto;
	}//dtogetDetail-end









}//class-end
