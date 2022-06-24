package experiences;

import common.BaseInfo;

public class Experience extends BaseInfo {
	@Override
	public String toString() {
		return "Experience [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", notice="
				+ notice + "]";
	}
}
