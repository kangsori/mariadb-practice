package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bookshop.vo.BookVo;


public class BookDao {
	
	public List<BookVo> fineAll() {
		List<BookVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =
				"  select a.no, a.title, a.rent, a.author_no, b.name "
				+ "from book a "
				+ "left join author b on author_no=b.no "
				+ "order by no";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String rent = rs.getString(3);
				Long authorNo = rs.getLong(4);
				String author = rs.getString(5);

				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setRent(rent);
				vo.setAuthorNo(authorNo);
				vo.setAuthor(author);
				
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

	public void insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql ="insert into book(title,author_no) values (?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getAuthorNo());
			
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

	public String update(BookVo vo) {
		String result ="False";
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		
		try {
			conn = getConnection();
			
			if(vo.getRent().equals("Y")) {
				//대여가능한 책인지 확인
				String sql1 ="select * from book where no=? and rent='N'";
				pstmt1 = conn.prepareStatement(sql1);
				
				pstmt1.setLong(1, vo.getNo());
				ResultSet rs=pstmt1.executeQuery();
				
				if(!rs.next()) {
					result ="False:이미 대여중인 책입니다.";
					return result;
				}
			}
			
			String sql2 ="update book set rent=? where no=?";
			pstmt2 = conn.prepareStatement(sql2);
			
			pstmt2.setString(1, vo.getRent());
			pstmt2.setLong(2, vo.getNo());
			
			pstmt2.executeQuery();
			
			if(vo.getRent().equals("Y")) {
				result ="True:["+vo.getNo()+"]번 책이 대여 되었습니다.";
			}else {
				result ="True:["+vo.getNo()+"]번 책이 반납 되었습니다.";
			}
			
		} catch (SQLException e) {
			System.out.println("error : "+e);
		} finally {
			try {
				if(pstmt1 != null ) {
					pstmt1.close();
				}
				if(pstmt2 != null ) {
					pstmt2.close();
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
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.10.102:3307/webdb?charset=utf8";
			
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 :" +e);
		}
	
		return conn;
	}

	

	

}
