package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;

public class BookDao {
	
	public List<BookVo> fildAll() {
		List<BookVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = getConnection();
				
			String sql =
				"  select a.no, a.name, a.price, a.category_no, b.name"
				+ " from book a "
			    + " inner join category b on a.category_no=b.no ";
				
			pstmt = conn.prepareStatement(sql);
				
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				BookVo vo = new BookVo();
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setPrice(rs.getInt(3));
				vo.setCategoryNo(rs.getLong(4));
				vo.setCategory(rs.getString(5));
				
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error : "+e);
		} finally {
			try {
				if(rs != null ) {
					rs.close();
				}
				if(pstmt != null ) {
					pstmt.close();
				}
				if(conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void Insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql ="insert into book(name, price, category_no) values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setLong(3, vo.getCategoryNo());
			
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			System.out.println("error : "+e);
		} finally {
			try {
				
				if(pstmt != null ) {
					pstmt.close();
				}
				if(conn != null ) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.10.102:3307/bookmall?charset=utf8";
			
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 :" +e);
		}
	
		return conn;
	}

	
	
}
