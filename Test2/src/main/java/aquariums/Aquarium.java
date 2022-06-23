package aquariums;

public class Aquarium {
	private int id;
	private String name;
	private String address;
	private String phone;
	private String notice;
	
	@Override
	public String toString() {
		return "Aquarium [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", notice="
				+ notice + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	
}
