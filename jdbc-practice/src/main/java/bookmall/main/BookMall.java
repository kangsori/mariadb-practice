package bookmall.main;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.OrdersDao;
import bookmall.dao.UserDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.OrdersVo;
import bookmall.vo.UserVo;

public class BookMall {

	public static void main(String[] args) {
		//회원 리스트 출력
		//userDaoTest.testInsert();
		System.out.println("==========회원리스트==========");
		findAllUser();
		
		//카테고리 리스트 출력
		//categoryDaoTest.testInsert();
		System.out.println("===========카테고리===========");
		findAllCategory();

		//상품 리스트 출력
		//bookDaoTest.testInsert();
		System.out.println("============상품============");
		findAllBook();
		
		//카트 리스트 출력
		//cartDaoTest.testInsert();
		System.out.println("==========카트리스트==========");
		findAllCart();
		
		//카트 리스트 출력
	    //ordersDaoTest.testInsert();
		System.out.println("==========주문리스트==========");
		findAllOrders();
 
	}

	private static void findAllUser() {
		List<UserVo> list=new UserDao().fildAll();
		for(UserVo vo:list) {
			System.out.println("["+vo.getNo()+"] 이름:"+vo.getName()+", "
					                        + "전화번호:"+vo.getPhoneNo()+", "
					                        + "이메일:"+vo.getEmail()+", "
					                        + "비밀번호:"+vo.getPassword());
		}
	}
	
	private static void findAllCategory() {
		List<CategoryVo> list=new CategoryDao().fildAll();
		for(CategoryVo vo:list) {
			System.out.println("["+vo.getNo()+"] "+vo.getName());
		}
	}
	
	private static void findAllBook() {
		List<BookVo> list=new BookDao().fildAll();
		for(BookVo vo:list) {
			System.out.println("["+vo.getNo()+"] 카테고리:"+vo.getCategory()+", "
                    						 + "책제목:"+vo.getName()+", "
                    						 + "가격:"+vo.getPrice());
		}
	}
	
	private static void findAllCart() {
		List<CartVo> list=new CartDao().fildAll();
		for(CartVo vo:list) {
			System.out.println("["+vo.getNo()+"] 책제목:"+vo.getBookName()+", "
                    						 + "수량:"+vo.getQuantity()+", "
                    						 + "가격:"+vo.getPrice()+", "
                    						 + "-"+vo.getUserName());
		}
	}
	
	private static void findAllOrders() {
		List<OrdersVo> list=new OrdersDao().fildAll();
		for(OrdersVo vo:list) {
			System.out.println("["+vo.getOrderNo()+"] - 총 금액 :"+vo.getTotalPrice() +"\n"
					            +"주문자:"+vo.getName()+"("+(vo.getMember().equals("Y")?"회원":"비회원")+")/"+vo.getEmail()+"\n"
					            + "배송지:"+vo.getAddress()+" \n"
					            + "책번호: "+vo.getBookNo()+" - "+vo.getBookName()+"\n"
                    			+ "수량:"+vo.getQuantity());
		}
	}

}
