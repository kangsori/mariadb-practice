package bookmall.vo;

public class CartVo {
	private Long no;
	private Integer quantity;
	private Long bookNo;
	private Long userNo;
	private String bookName;
	private String userName;
	private Integer price;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "CartVo [no=" + no + ", quantity=" + quantity + ", bookNo=" + bookNo + ", userNo=" + userNo
				+ ", bookName=" + bookName + ", userName=" + userName + ", price=" + price + "]";
	}

}
