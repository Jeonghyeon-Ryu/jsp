package common;

import java.util.List;
import java.util.Scanner;

import foodstore.FoodStore;
import foodstore.FoodStoreDAO;
import memberCourses.MemberCourse;
import memberCourses.MemberCourseDAO;
import members.Member;
import stores.Store;
import stores.StoreDAO;

public class UserSystem {
	private Scanner sc = new Scanner(System.in);
	private String mainLocation;
	private Member loginInfo;
	private MemberCourse[] course = new MemberCourse[5];
	
	public UserSystem(Member loginInfo) {
		this.loginInfo = loginInfo;
		while(true) {
			menuPrint();
			int menuNo = menuSelect();
			if(menuNo == 1) {	// 코스 짜기
				selectMainLocation();
			} else if (menuNo == 2) {	// 나의 코스 시스템
				showMyCourse();
			} else if (menuNo == 9) {	// 종료(로그아웃)
				exit();
				break;
			} else {
				showInputError();
			}
		}
	}
	private void menuPrint() {
		System.out.println("=================================");
		System.out.println(" 1.여행코스 짜기  2.나의 계획  9.로그아웃");
		System.out.println("=================================");
	}
	
	private int menuSelect() {
		System.out.print(" 메뉴 선택 > ");
		return Integer.parseInt(sc.nextLine());
	}
	private void exit() {
		System.out.println(" 종료합니다.");
	}
	private void back() {
		System.out.println(" 뒤로갑니다.");
	}
	private void showInputError() {
		System.out.println(" 메뉴를 잘못 선택하였습니다.");
	}
	
	private void selectMainLocation() {
		// 메인 장소 선택
		while(true) {
			System.out.println("============================");
			System.out.println(" 1.주소기준  2.축제기준  9.뒤로가기");
			System.out.println("============================");
			int menuNo = menuSelect();
			if(menuNo == 1) {	// 코스 짜기
				setMainLocation(menuNo);
			} else if (menuNo == 2) {	// 나의 코스 시스템
//				showMyCourse();
			} else if (menuNo == 9) {	// 종료 
				back();
				break;
			} else {
				showInputError();
			}
		}
		
		// 여행 소분류 선택
	}
	private void setMainLocation(int menuNo) {
		System.out.println("========== 장소선택으로 수정필요");
		if(menuNo==1) {
			System.out.print(" 주소입력 > ");
			mainLocation = sc.nextLine();
		}
		showCurrentCourse();
	}
	// 현재 선택 상태 출력
	// 수정할 번호 선택
	private void showCurrentCourse() {
		while(true) {
			for(int i=0; i<5; i++) {
				if(course[i]==null) {
					System.out.println((i+1)+". 계획이 비어있습니다");
				} else {
					// 장소 정보(이름:주소) 출력
					System.out.println((i+1)+". ");
				}
			}
			System.out.print(" 수정할 순번(저장:0, 종료:9) > ");
			int num = Integer.parseInt(sc.nextLine());
			if(num==0) {
				insertMemberCourse();
			} else if(num == 9) {
				break;
			} else {
				selectMainCategory(course[num-1]);
			}
		}	
	}
	// 여행 대분류 선택
	private void selectMainCategory(MemberCourse course) {
		while(true) {
			System.out.println("====================================================");
			System.out.println(" 1.카페  2.식사  3.문화생활  4.놀이공원  5.구경,산책  9.뒤로가기");
			System.out.println("====================================================");
			int menuNo = menuSelect();
			if(menuNo==1) {
				List<FoodStore> list = FoodStoreDAO.getInstance().selectAll(1, mainLocation);
				list.forEach( x -> System.out.println(x));
				int selectNo = selectStore();
				if(selectNo==0)
					continue;
				selectedFoodStore(selectNo,course);
				break;
			} else if(menuNo==2) {
				List<FoodStore> list = FoodStoreDAO.getInstance().selectAll(2, mainLocation);
				list.forEach( x -> System.out.println(x));
				int selectNo = selectStore();
				if(selectNo==0)
					continue;
				selectedFoodStore(selectNo,course);
				break;
			} else if(menuNo==3) {
				// 영화
			} else if(menuNo==4) {
				// 놀이공원
				List<Store> list = StoreDAO.getInstance().selectAll(3,mainLocation);
				list.forEach( x -> System.out.println(x));
				int selectNo = selectStore();
				if(selectNo==0)
					continue;
				selectedStore(selectNo,course);
				break;
			} else if(menuNo==5) {
				// 동물원, 아쿠아리움
				List<Store> zooList = StoreDAO.getInstance().selectAll(1, mainLocation);
				List<Store> aquaList = StoreDAO.getInstance().selectAll(2, mainLocation);
				zooList.forEach( x -> System.out.println(x));
				aquaList.forEach( x -> System.out.println(x));
				int selectNo = selectStore();
				if(selectNo==0)
					continue;
				selectedStore(selectNo,course);
				break;
			} else if(menuNo==9) {
				back();
				break;
			}
		}
	}
	private int selectStore() {
		int selectNo = 0;
		System.out.print(" 방문할 장소번호를 입력하세요(취소:0) > ");
		try {
			selectNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("장소번호를 선택하여야 합니다.");
		}
		return selectNo;
	}
	private void selectedStore(int selectNo,MemberCourse course) {
		if(StoreDAO.getInstance().selectOne(selectNo)==null) {
			System.out.println("존재하지 않는 장소입니다.");
		} else {
			course.setLocation_id(selectNo);
			course.setLocation_type(2);
		}
	}
	private void selectedFoodStore(int selectNo,MemberCourse course) {
		FoodStore fs = FoodStoreDAO.getInstance().selectOne(selectNo);
		if(fs==null) {
			System.out.println("존재하지 않는 장소입니다.");
		} else {
			course.setLocation_id(selectNo);
			course.setLocation_type(1);
		}
	}
	private void insertMemberCourse() {
		int step = 1;
		for(MemberCourse course : this.course) {
			if(course.getLocation_id()!=0) {
				course.setId(loginInfo.getId());
				course.setStep(step++);
				MemberCourseDAO.getInstance().insert(course);
			}
		}
	}
	private void showMyCourse() {
		List<MemberCourse> list = MemberCourseDAO.getInstance().selectAll(loginInfo.getId());
		list.forEach(x -> System.out.println(x));
	}
}
