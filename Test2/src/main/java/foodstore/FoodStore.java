package foodstore;

import common.BaseInfo;

public class FoodStore extends BaseInfo{

	@Override
	public String toString() {
		String result = "";
		if(category_id==1) { //카페
			result += "카페";
		} else if (category_id==2) { //한식
			result += "한식";
		} else if (category_id==3) { //중식
			result += "중식";
		} else if (category_id==4) { //일식
			result += "일식";
		} else if (category_id==5) { //양식
			result += "양식";
		}
		result += " : [ 가게번호:"+id+", 가게명:"+name+", 주소:"+address+", 전화번호:"+phone+" ]";
		return result;
	}
	
}
