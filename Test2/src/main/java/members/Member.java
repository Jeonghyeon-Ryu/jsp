package members;

import java.sql.Date;

public class Member {
	private String id;
	private String pw;
	private String name;
	private int sex;
	private Date birth;
	private String address;
	private String phone;
	private int authority;
	// authority : 0-user, 1-admin
	
	// toString for TEST
	@Override
	public String toString() {
		return "[ID=" + id + ", PW=" + pw + ", 이름=" + name + ", 성=" + sex + ", 생일=" + birth + ", 주소="
				+ address + ", 전화번호=" + phone + ", 권한=" + authority + "]";
	}
	// Getter Setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	
	
}
