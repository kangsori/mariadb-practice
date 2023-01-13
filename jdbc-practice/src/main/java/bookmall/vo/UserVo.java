package bookmall.vo;

public class UserVo {
	private Long no;
	private String name;
	private String password;
	private String phoneNo;
	private String email;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phone_no) {
		this.phoneNo = phone_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", password=" + password + ", phone_no=" + phoneNo + ", email="
				+ email + "]";
	}
	
	
}
