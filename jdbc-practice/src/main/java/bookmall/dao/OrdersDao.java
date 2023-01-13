package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrdersVo;

public class OrdersDao {
	
	public List<OrdersVo> fildAll() {
		List<OrdersVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = getConnection();
				
			String sql =
				"  select a.no, a.order_no,a.member,a.name,a.email,a.total_price,a.address,b.book_no,c.name,b.quantity "
				+ " from orders a "
			    + " inner join order_book b on a.no=b.order_no "
			    + " inner join book c on b.book_no=c.no ";
				
			pstmt = conn.prepareStatement(sql);
				
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				OrdersVo vo = new OrdersVo();
				vo.setNo(rs.getLong(1));
				vo.setOrderNo(rs.getString(2));
				vo.setMember(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setEmail(rs.getString(5));
				vo.setTotalPrice(rs.getInt(6));
				vo.setAddress(rs.getString(7));
				vo.setBookNo(rs.getLong(8));
				vo.setBookName(rs.getString(9));
				vo.setQuantity(rs.getInt(10));
				
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

	public void Insert(OrdersVo vo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			String sql ="insert into orders(order_no, member, name, email, total_price, address) values (?,?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getOrderNo());
			pstmt.setString(2, vo.getMember());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setInt(5, vo.getTotalPrice());
			pstmt.setString(6, vo.getAddress());
			pstmt.executeQuery();
			
			String sql2 ="insert into order_book(quantity, order_no, book_no) values (?,LAST_INSERT_ID(),?)";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, vo.getQuantity());
			pstmt2.setLong(2, vo.getBookNo());
			pstmt2.executeQuery();
			
		} catch (SQLException e) {
			conn.rollback();
			System.out.println("error : "+e);
		} finally {
			try {
				if(pstmt != null ) {
					pstmt.close();
				}
				if(pstmt2 != null ) {
					pstmt2.close();
				}
				if(conn != null ) {
					conn.setAutoCommit(true);
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
