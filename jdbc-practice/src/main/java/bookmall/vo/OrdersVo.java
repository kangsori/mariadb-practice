package bookmall.vo;

public class OrdersVo {
	private Long no;
	private String orderNo;
	private String member;
	private String name;
	private String email;
	private Integer totalPrice;
	private String address;
	private Long bookNo;
	private String bookName;
	private Integer quantity;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", orderNo=" + orderNo + ", member=" + member + ", name=" + name + ", email="
				+ email + ", totalPrice=" + totalPrice + ", address=" + address + ", bookNo=" + bookNo + ", bookName="
				+ bookName + ", quantity=" + quantity + "]";
	}
		
}
