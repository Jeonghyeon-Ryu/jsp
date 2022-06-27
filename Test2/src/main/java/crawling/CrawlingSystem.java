package crawling;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CrawlingSystem {
	private Scanner sc = new Scanner(System.in);
	String sector = null;
	List<String> storeList = null;
	
	public CrawlingSystem() {
		while(true) {
			menuPrint();
			int menuNo = menuSelect();
			if(menuNo == 1) {			// 음식점 추출
				extractFoodStore();
			} else if(menuNo == 2) {	// 음식점 외 추출
				extractStore();
			} else if(menuNo == 4) {	// 추출 시작
				if(sector==null)
					modifySector();
				try {
					new Crawling(sector);
				} catch (InterruptedException e) {
					System.out.println("추출 중단");
					e.printStackTrace();
				}
			} else if(menuNo == 9) {	// 메인메뉴 돌아가기
				exit();
				break;
			} else {
				showInputError();
			}
		}
	}
	
	private void menuPrint() {
		System.out.println("====================================");
		System.out.println(" 1.음식점 추출  2.음식점 외 추출  9.뒤로가기");
		System.out.println("====================================");
	}
	private int menuSelect() {
		System.out.print(" 메뉴 선택 > ");
		return Integer.parseInt(sc.nextLine());
	}
	private void exit() {
		System.out.println("DB 정보 추출 종료");
	}
	private void showInputError() {
		System.out.println("메뉴 선택을 잘못하였습니다.");
	}
	private void setFoodStoreDivision() {
		storeList = new ArrayList<>();
		storeList.add("한식");
//		storeList.add("중식");
//		storeList.add("일식");
//		storeList.add("양식");
//		storeList.add("카페");
	}
	private void setStoreDivision() {
		storeList = new ArrayList<>();
		storeList.add("동물원");
		storeList.add("놀이공원");
		storeList.add("아쿠아리움");
	}
	private void extractFoodStore() {
		setFoodStoreDivision();
		StoreDivision.setStoreDivision(storeList.toArray(new String[storeList.size()]));
		modifySector();
		try {
			new Crawling(sector);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void extractStore() {
		setStoreDivision();
		StoreDivision.setStoreDivision(storeList.toArray(new String[storeList.size()]));
		try {
			new CrawlingStore();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void modifySector() {
		System.out.print("추출할 지역을 입력하세요 : ");
		this.sector = sc.nextLine();
	}
}
