package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class bookDaoTest {

	public static void main(String[] args) {
		testInsert();
		testFindAll();

	}

	public static void testFindAll() {
		List<BookVo> list=new BookDao().fildAll();
		for(BookVo vo:list) {
			System.out.println(vo);
		}
		
	}

	public static void testInsert() {
		BookVo vo = null;
		BookDao dao = new BookDao();
		
		vo = new BookVo();
		vo.setName("오늘 밤, 세계에서 이 사랑이 사라진다 해도");
		vo.setPrice(14000);
		vo.setCategoryNo(1L);
		dao.Insert(vo);
		
		vo = new BookVo();
		vo.setName("사피엔스");
		vo.setPrice(22000);
		vo.setCategoryNo(2L);
		dao.Insert(vo);
		
		vo = new BookVo();
		vo.setName("디스 이즈 오사카 This Is Osaka (2023년 최신 개정판)");
		vo.setPrice(19800);
		vo.setCategoryNo(3L);
		dao.Insert(vo);
		
	}

}
