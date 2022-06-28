package stores;

import common.BaseInfo;

public class Store extends BaseInfo{

	@Override
	public String toString() {
		String result = "";
		if(category_id==1) { //동물원
			result += "동물원";
		} else if (category_id==2) {
			result += "아쿠아리움";
		} else if (category_id==3) { 
			result += "놀이공원";
		} else if (category_id==4) { 
			result += "체험";
		} 
		result += " : [ 가게번호:"+id+", 가게명:"+name+", 주소:"+address+", 전화번호:"+phone+" ]";
		return result;
	}
	
}
