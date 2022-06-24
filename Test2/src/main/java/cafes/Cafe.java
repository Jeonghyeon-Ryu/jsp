package cafes;

import common.BaseInfo;

public class Cafe extends BaseInfo{	
	@Override
	public String toString() {
		return "카페이름=" + name + ", 주소=" + address + ", 전화번호=" + phone + ", 소개=" + notice
				+ "]";
	}
}
