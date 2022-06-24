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
			if(menuNo == 1) {			// 현재 세팅된 지역/정보
				showInfo();
			} else if(menuNo == 2) {			// 지역 수정
				modifySector();
			} else if(menuNo == 3) {	// 정보 수정 
				modifyStore();
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
		System.out.println("==============================================");
		System.out.println(" 1.세팅보기  2.지역수정  3.정보수정  4.추출시작  9.뒤로가기");
		System.out.println("==============================================");
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
	private void showInfo() {
		StoreDivision.readStoreDivision();
		System.out.println("================ 추출할 가게 분류");
		StoreDivision.storeDivisionList.forEach( x -> System.out.print("|"+x));
		System.out.println("|");
		System.out.println("================ 추출할 지역");
		if(StoreDivision.storeDivisionList.size() == 0)
			System.out.println("지역 세팅이 되어있지 않습니다.");
		System.out.println("|"+sector+"|");
	}
	private void modifySector() {
		System.out.print("추출할 지역을 입력하세요 : ");
		this.sector = sc.nextLine();
	}
	private void modifyStore() {
		storeList = new ArrayList<>();
		while(true) {
			System.out.print("추출할 가게 분류를 입력하세요(종료:9) : ");
			String in = sc.nextLine();
			if(in.equals("9")) {
				System.out.println("분류 입력 종료");
				break;
			}
			storeList.add(in);
		}
		if(storeList.size()==0) {
			return;
		}
		StoreDivision.setStoreDivision(storeList.toArray(new String[storeList.size()]));
	}
}
