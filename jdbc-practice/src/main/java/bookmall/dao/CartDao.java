package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bookmall.vo.CartVo;

public class CartDao {
	
	public List<CartVo> fildAll() {
		List<CartVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = getConnection();
				
			String sql =
				"  select a.no, a.quantity, a.book_no, a.user_no, b.name, c.name,b.price"
				+ " from cart a "
			    + " inner join book b on a.book_no=b.no "
			    + " inner join user c on a.user_no=c.no ";
				
			pstmt = conn.prepareStatement(sql);
				
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				CartVo vo = new CartVo();
				vo.setNo(rs.getLong(1));
				vo.setQuantity(rs.getInt(2));
				vo.setBookNo(rs.getLong(3));
				vo.setUserNo(rs.getLong(4));
				vo.setBookName(rs.getString(5));
				vo.setUserName(rs.getString(6));
				vo.setPrice(rs.getInt(7));
				
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

	public void Insert(CartVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql ="insert into cart(quantity, book_no, user_no) values (?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getQuantity());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setLong(3, vo.getUserNo());
			
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
