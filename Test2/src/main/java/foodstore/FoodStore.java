package foodstore;

import common.BaseInfo;

public class FoodStore extends BaseInfo{

	@Override
	public String toString() {
		return "FoodStore [id=" + id + ", category_id=" + category_id + ", name=" + name + ", address=" + address
				+ ", phone=" + phone + ", notice=" + notice + "]";
	}
	
}
