package emaillist.main;

import java.util.List;
import java.util.Scanner;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

public class EmaillistApp {
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		
		
		while(true) {
			System.out.println("(l)ist (d)elete (i)nsert (q)uit > ");
		
			String command = scanner.nextLine();
			
			if("l".equals(command)) {
				doList();
			}else if("d".equals(command)) {
				doDelete();
			}else if("i".equals(command)) {
				doInsert();
			}else if("q".equals(command)) {
				break;
			}
		}

	}
	
	private static void doDelete() {
		System.out.println("이메일:");
		String email =scanner.nextLine();
		
		new EmaillistDao().deleteByEmail(email);
		doList();
	}

	private static void doInsert() {
		EmaillistVo vo = new EmaillistVo();
		
		System.out.println("성:");
		vo.setFirstName(scanner.nextLine());
		
		System.out.println("이름:");
		vo.setLastName(scanner.nextLine());
		
		System.out.println("이메일:");
		vo.setEmail(scanner.nextLine());
		
		new EmaillistDao().Insert(vo); 
		doList();
	}

	private static void doList() {
		List<EmaillistVo> list= new EmaillistDao().fildAll();
		for(EmaillistVo vo:list) {
			System.out.println("이름:"+vo.getFirstName()+" "+vo.getLastName()+", 이메일:"+vo.getEmail());
		}
	}

}
