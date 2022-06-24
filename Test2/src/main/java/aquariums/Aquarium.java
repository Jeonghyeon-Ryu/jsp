package aquariums;

import common.BaseInfo;

public class Aquarium extends BaseInfo{
	@Override
	public String toString() {
		return "Aquarium [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", notice="
				+ notice + "]";
	}
}
