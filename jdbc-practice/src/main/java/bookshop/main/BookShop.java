package bookshop.main;
import java.util.List;
import java.util.Scanner;
import bookshop.dao.BookDao;
import bookshop.vo.BookVo;


public class BookShop {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		while(true) {
			System.out.print("(1)대여 (2)반납 (3)목록보기 (4)나가기 >");
			int no = scanner.nextInt();
		
			if(no == 1) {
				doProcess("rent");
			}else if(no == 2) {
				doProcess("return");
			}else if(no ==3) {
				displayBookInfo();
			}else if(no ==4) {
				break;
			}else {
				System.out.println("=====잘못된 명령입니다 다시 입력해 주세요===== ");
			}
		}
		scanner.close();
	}

	private static void doProcess(String process) {
		displayBookInfo();
	
		while(true) {
			
			if("rent".equals(process)) {
				System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
			}else {
				System.out.print("반납하실 책의 번호를 입력해 주세요 :");
			}
			
			Long no = scanner.nextLong();
			
			BookVo vo = new BookVo();
			vo.setNo(no);
			vo.setRent("rent".equals(process)?"Y":"N");
			
			String message =new BookDao().update(vo);
			String[] tokens =message.split(":");
			System.out.println("========="+tokens[1]+"=========");

			if(tokens[0].equals("True")) {
				displayBookInfo();
				break;
			}
		}
		
	}

	private static void displayBookInfo() {
		System.out.println("*************도서 정보 출력*************");
		List<BookVo> list = new BookDao().fineAll();
		for(BookVo vo:list) {
			System.out.println("["+vo.getNo()+"] 책 제목:"+vo.getTitle()+", 작가:"+vo.getAuthor()+", 대여 유무:"+ (vo.getRent().equals("Y")?"대여중":"재고있음"));
		}
	}

}
