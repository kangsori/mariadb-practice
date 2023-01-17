package bookmall.dao.test;

import java.util.List;

import bookmall.dao.UserDao;
import bookmall.vo.UserVo;

public class userDaoTest {

	public static void main(String[] args) {
		testInsert();
		testFindAll();

	}

	public static void testFindAll() {
		List<UserVo> list=new UserDao().fildAll();
		for(UserVo vo:list) {
			System.out.println(vo);
		}
		
	}

	public static void testInsert() {
		UserVo vo = null;
		UserDao dao = new UserDao();
		
		vo = new UserVo();
		vo.setName("둘리");
		vo.setPassword("둘리");
		vo.setEmail("dooly@naver.com");
		vo.setPhoneNo("010-1111-1111");
		dao.Insert(vo);
		
		vo = new UserVo();
		vo.setName("또치");
		vo.setPassword("또치");
		vo.setEmail("ddochi@naver.com");
		vo.setPhoneNo("010-2222-2222");
		dao.Insert(vo);
		
	}

}
