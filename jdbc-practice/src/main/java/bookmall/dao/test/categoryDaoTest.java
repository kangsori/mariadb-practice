package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class categoryDaoTest {

	public static void main(String[] args) {
		//testInsert();
		//testFindAll();

	}

	public static void testFindAll() {
		List<CategoryVo> list=new CategoryDao().fildAll();
		for(CategoryVo vo:list) {
			System.out.println(vo);
		}
		
	}

	public static void testInsert() {
		CategoryVo vo = null;
		CategoryDao dao = new CategoryDao();
		
		vo = new CategoryVo();
		vo.setName("소설");
		dao.Insert(vo);
		
		vo = new CategoryVo();
		vo.setName("인문학");
		dao.Insert(vo);
		
		vo = new CategoryVo();
		vo.setName("여행");
		dao.Insert(vo);
		
	}

}
