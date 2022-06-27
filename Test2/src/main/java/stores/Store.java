package stores;

import common.BaseInfo;

public class Store extends BaseInfo{

	@Override
	public String toString() {
		return "Store [id=" + id + ", category_id=" + category_id + ", name=" + name + ", address=" + address
				+ ", phone=" + phone + ", notice=" + notice + "]";
	}
	
}
