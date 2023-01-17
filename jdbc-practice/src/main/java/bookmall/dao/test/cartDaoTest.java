package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class cartDaoTest {

	public static void main(String[] args) {
		testInsert();
		testFindAll();

	}

	public static void testFindAll() {
		List<CartVo> list=new CartDao().fildAll();
		for(CartVo vo:list) {
			System.out.println(vo);
		}
		
	}

	public static void testInsert() {
		CartVo vo = null;
		CartDao dao = new CartDao();
		
		vo = new CartVo();
		vo.setBookNo(1L);
		vo.setUserNo(1L);
		vo.setQuantity(1);
		dao.Insert(vo);
		
		vo = new CartVo();
		vo.setBookNo(2L);
		vo.setUserNo(1L);
		vo.setQuantity(2);
		dao.Insert(vo);
		
	}

}
