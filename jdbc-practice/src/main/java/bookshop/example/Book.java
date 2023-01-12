package bookshop.example;

public class Book {
	//필드 정의
	private int bookNo;
	private String title;
	private String author;
	private int stateCode;
	
	//bookNo,title,author를 매개변수로 받는 생성자 
	public Book(int bookNo,String title,String author) {
		this.bookNo=bookNo;
		this.title=title;
		this.author=author;
		//생성자호출시 1로 설정
		this.stateCode=1;
	}

	//setter,getter 메소드
	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	
	//대여하는 메소드
	public void rent() {
		//대여중일때의 유효성 체크 추가 
		if(this.stateCode==0) {
			System.out.println("["+this.bookNo+"] "+this.title+"이(가) 대여중 입니다.");
		}else {
			System.out.println("["+this.bookNo+"] "+this.title+"이(가) 대여 됐습니다.");
			this.stateCode=0;
		}
	}
	
	//출력하는 메소드
	public void print() {
		System.out.println("["+bookNo+"] 책 제목:"+title+", 작가:"+author+", 대여 유무:"+((stateCode==1) ? "재고있음" : "대여중"));
	}

	
	
}
