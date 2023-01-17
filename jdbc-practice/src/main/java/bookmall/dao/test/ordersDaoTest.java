package bookmall.dao.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.dao.OrdersDao;
import bookmall.vo.CartVo;
import bookmall.vo.OrdersVo;

public class ordersDaoTest {

	public static void main(String[] args) {
		testInsert();
		testFindAll();
	}

	public static void testFindAll() {
		List<OrdersVo> list=new OrdersDao().fildAll();
		for(OrdersVo vo:list) {
			System.out.println(vo);
			List<CartVo> item=new OrdersDao().fildAllItem(1L);
			for(CartVo cvo:item) {
				System.out.println(cvo);
			}
		}
		
	}

	public static void testInsert() {
		OrdersVo vo = null;
		OrdersDao dao = new OrdersDao();
		List<CartVo> cart = new ArrayList<>();
		CartVo book = null;
		
		try {
			String orderNo="20230103-001";
			
			vo = new OrdersVo();
			vo.setOrderNo(orderNo);
			vo.setName("둘리");
			vo.setEmail("dooly@naver.com");
			vo.setTotalPrice(49900);
			vo.setAddress("서울시 무슨동 11번지 고길동집 2층");

			Long[] bookNo = {1L,3L};
			Integer[] qty = {1,2};
			
			for(int i=0; i <bookNo.length; i++) {
				book = new CartVo();
				book.setBookNo(bookNo[i]);
				book.setQuantity(qty[i]);
				cart.add(book);
			}

			dao.Insert(vo,cart);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
