package bookmall.dao.test;

import java.sql.SQLException;
import java.util.List;

import bookmall.dao.OrdersDao;
import bookmall.vo.OrdersVo;

public class ordersDaoTest {

	public static void main(String[] args) {
		//testInsert();
		//testFindAll();

	}

	public static void testFindAll() {
		List<OrdersVo> list=new OrdersDao().fildAll();
		for(OrdersVo vo:list) {
			System.out.println(vo);
		}
		
	}

	public static void testInsert() {
		OrdersVo vo = null;
		OrdersDao dao = new OrdersDao();
		
		
		try {
			vo = new OrdersVo();
			vo.setOrderNo("20230103-001");
			vo.setMember("Y");
			vo.setName("둘리");
			vo.setEmail("dooly@naver.com");
			vo.setTotalPrice(19000);
			vo.setAddress("서울시 무슨동 11번지 고길동집 2층");
			vo.setBookNo(3L);
			vo.setQuantity(1);
			dao.Insert(vo);
			
			vo = new OrdersVo();
			vo.setOrderNo("20230103-002");
			vo.setMember("N");
			vo.setName("강소리");
			vo.setEmail("rkdthfl22@naver.com");
			vo.setTotalPrice(9900);
			vo.setAddress("부산시 수영구 좋은집 601호");
			vo.setBookNo(1L);
			vo.setQuantity(2);
			dao.Insert(vo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
