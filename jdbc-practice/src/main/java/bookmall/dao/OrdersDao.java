package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;
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
				"  select no, order_no,name,email,total_price,address"
				+ " from orders ";
				
			pstmt = conn.prepareStatement(sql);
				
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				OrdersVo vo = new OrdersVo();
				vo.setNo(rs.getLong(1));
				vo.setOrderNo(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setEmail(rs.getString(4));
				vo.setTotalPrice(rs.getInt(5));
				vo.setAddress(rs.getString(6));
				
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
	
	public List<CartVo> fildAllItem(Long orderNo) {
		List<CartVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		try {
			conn = getConnection();
				
			String sql =
				"  select a.no, a.book_no,b.name,a.quantity,b.price"
				+ " from order_book a "
				+ " inner join book b on a.book_no =b.no ";
				
			pstmt = conn.prepareStatement(sql);
				
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				CartVo vo = new CartVo();
				vo.setNo(rs.getLong(1));
				vo.setBookNo(rs.getLong(2));
				vo.setBookName(rs.getString(3));
				vo.setQuantity(rs.getInt(4));
				vo.setPrice(rs.getInt(5));
				
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

	public void Insert(OrdersVo vo, List<CartVo> books) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt_id = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);

			String sql ="insert into orders(order_no, name, email, total_price, address) values (?,?,?,?,?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getOrderNo());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setInt(4, vo.getTotalPrice());
			pstmt.setString(5, vo.getAddress());
			pstmt.executeQuery();

			String sql_id="select LAST_INSERT_ID() ";
			pstmt_id = conn.prepareStatement(sql_id);
			rs = pstmt_id.executeQuery();
			rs.next();
			Long orderNo = rs.getLong(1);
		
			for(CartVo list:books) {
				String sql2 ="insert into order_book(quantity, order_no, book_no) values (?,?,?)";
				pstmt2 = conn.prepareStatement(sql2);
				pstmt2.setInt(1, list.getQuantity());
				pstmt2.setLong(2,orderNo);
				pstmt2.setLong(3,list.getBookNo());
				pstmt2.executeQuery();
			}
	
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
				if(pstmt_id != null ) {
					pstmt_id.close();
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
