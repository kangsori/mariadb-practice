package emaillist.dao.test;

import java.sql.Connection;
import java.util.List;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

public class emaillistDaoTest {

	public static void main(String[] args) {
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("둘");
		vo.setLastName("리");
		vo.setEmail("dooly@gmail.com");
		
		testInsert(vo);
		testDelete("dooly@gmail.com");
		testFindAll();
	}

	private static void testDelete(String Email) {
		new EmaillistDao().deleteByEmail(Email);
		
	}

	private static void testFindAll() {
		List<EmaillistVo> list= new EmaillistDao().fildAll();
		
		for(EmaillistVo vo:list) {
			System.out.println(vo);
		}
		
	}

	private static void testInsert(EmaillistVo vo) {
		new EmaillistDao().Insert(vo); 
		
	}
	
	

}
