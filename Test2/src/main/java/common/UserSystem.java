package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import foodstore.FoodStore;
import foodstore.FoodStoreDAO;
import memberCourses.MemberCourse;
import memberCourses.MemberCourseDAO;
import members.Member;
import members.MemberDAO;
import stores.Store;
import stores.StoreDAO;

public class UserSystem {
	private Scanner sc = new Scanner(System.in);
	private String mainLocation;
	private Member loginInfo;
	private MemberCourse[] course = new MemberCourse[5];
	
	public UserSystem(Member loginInfo) {
		this.loginInfo = loginInfo;
		for(int i=0; i<5; i++)
			course[i] = new MemberCourse();
		while(true) {
			menuPrint();
			int menuNo = menuSelect();
			if(menuNo == 1) {	// 코스 짜기
				selectMainLocation();
			} else if (menuNo == 2) {	// 나의 코스 시스템
				showMyCourse();
			} else if (menuNo == 3) {	// 나의 정보 확인
				showMyInfo();
			} else if (menuNo == 9) {	// 종료(로그아웃)
				exit();
				break;
			} else {
				showInputError();
			}
		}
	}
	private void menuPrint() {
		System.out.println("");
		System.out.println("=================================");
		System.out.println(" 1.여행코스 짜기  2.나의 계획  9.로그아웃");
		System.out.println("=================================");
	}
	
	private int menuSelect() {
		int result=0;
		System.out.print(" 메뉴 선택 > ");
		try {
			result = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("        > 메뉴 번호를 입력해주세요");
		}
		return result; 
	}
	private void exit() {
		System.out.println("        > 종료합니다.");
	}
	private void back() {
		System.out.println("        > 뒤로갑니다.");
	}
	private void showInputError() {
		System.out.println("        > 메뉴를 잘못 선택하였습니다.");
	}
	
	private void selectMainLocation() {
		// 메인 장소 선택
		while(true) {
			System.out.println("============================");
			System.out.println(" 1.주소기준  2.축제기준  9.뒤로가기");
			System.out.println("============================");
			int menuNo = menuSelect();
			if(menuNo == 1) {	
				setMainLocation(menuNo);
			} else if (menuNo == 2) {
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
			System.out.print(" 주소 입력 > ");
			mainLocation = sc.nextLine();
		}
		showCurrentCourse();
		return;
	}
	// 현재 선택 상태 출력
	// 수정할 번호 선택
	private void showCurrentCourse() {
		while(true) {
			for(int i=0; i<5; i++) {
				if(course[i].getLocation_id()==0) {
					System.out.println((i+1)+". 계획이 비어있습니다");
				} else {
					// 장소 정보(이름:주소) 출력
					if(course[i].getLocation_type()==1) {
						System.out.println((i+1)+". "+FoodStoreDAO.getInstance().selectOne(course[i].getLocation_id()));
					} else {
						System.out.println((i+1)+". "+StoreDAO.getInstance().selectOne(course[i].getLocation_id()));
					}
				}
			}
			System.out.print(" 수정할 순번(저장:0, 종료:9) > ");
			int num = Integer.parseInt(sc.nextLine());
			if(num==0) {
				insertMemberCourse();
				break;
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
				listPaging(list,course);
//				list.forEach( x -> System.out.println(x));
//				int selectNo = selectStore();
//				if(selectNo==0)
//					continue;
//				selectedFoodStore(selectNo,course);
				break;
			} else if(menuNo==2) {
				List<FoodStore> list = FoodStoreDAO.getInstance().selectAll(2, mainLocation);
				listPaging(list,course);
//				list.forEach( x -> System.out.println(x));
//				int selectNo = selectStore();
//				if(selectNo==0)
//					continue;
//				selectedFoodStore(selectNo,course);
				break;
			} else if(menuNo==3) {
				// 영화
			} else if(menuNo==4) {
				// 놀이공원
				List<Store> list = StoreDAO.getInstance().selectAll(3,mainLocation);
				listPaging(list,course);
				break;
			} else if(menuNo==5) {
				// 동물원, 아쿠아리움
				List<Store> zooList = StoreDAO.getInstance().selectAll(1, mainLocation);
				List<Store> aquaList = StoreDAO.getInstance().selectAll(2, mainLocation);
				List<Store> list = new ArrayList<>();
				list.addAll(zooList);
				list.addAll(aquaList);
				System.out.println(list.size());
				listPaging(list,course);
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
			System.out.println(" 장소번호를 선택하여야 합니다.");
		}
		return selectNo;
	}
	private void selectedStore(int selectNo,MemberCourse course) {
		if(selectNo==0) {
			
		} else if(StoreDAO.getInstance().selectOne(selectNo)==null) {
			System.out.println(" 존재하지 않는 장소입니다.");
		} else {
			course.setLocation_id(selectNo);
			course.setLocation_type(2);
		}
	}
	private void selectedFoodStore(int selectNo,MemberCourse course) {
		FoodStore fs = FoodStoreDAO.getInstance().selectOne(selectNo);
		if(fs==null) {
			System.out.println(" 존재하지 않는 장소입니다.");
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
		FoodStore foodStoreInfo = null;
		Store storeInfo = null;
		String result = "";
		System.out.println();
		for(int i=0; i<list.size(); i++) {
			foodStoreInfo = null;
			storeInfo = null;
			result = "";
			if(list.get(i).getLocation_type()==1) {
				foodStoreInfo = FoodStoreDAO.getInstance().selectOne(list.get(i).getLocation_id());
			} else {
				storeInfo = StoreDAO.getInstance().selectOne(list.get(i).getLocation_id());
			}
			result += "["+list.get(i).getId()+"]" + "님의 여행코스 [ "+list.get(i).getStep()+"단계:";
			if(foodStoreInfo!=null) {
				if(foodStoreInfo.getCategory_id()==1) { //카페
					result += "카페";
				} else if (foodStoreInfo.getCategory_id()==2) { //한식
					result += "한식";
				} else if (foodStoreInfo.getCategory_id()==3) { //중식
					result += "중식";
				} else if (foodStoreInfo.getCategory_id()==4) { //일식
					result += "일식";
				} else if (foodStoreInfo.getCategory_id()==5) { //양식
					result += "양식";
				}
				result += " | 가게명:"+foodStoreInfo.getName()+" | 주소:"+foodStoreInfo.getAddress()+" | 전화번호:"+foodStoreInfo.getPhone()+" ]";
			} else {
				if(storeInfo.getCategory_id()==1) { //동물원
					result += "동물원";
				} else if (storeInfo.getCategory_id()==2) {
					result += "아쿠아리움";
				} else if (storeInfo.getCategory_id()==3) { 
					result += "놀이공원";
				} else if (storeInfo.getCategory_id()==4) { 
					result += "체험";
				}
				result += " | 가게명:"+storeInfo.getName()+" | 주소:"+storeInfo.getAddress()+" | 전화번호:"+storeInfo.getPhone()+" ]";
			}
			System.out.println(result);
		}
	}
	private void listPaging(List<?> list, MemberCourse course) {
		if(list.size()==0) {
			System.out.println(" 해당 지역에 등록된 장소가 없습니다.");
		}
		for(int i=1; i<=list.size(); i++) {
			System.out.println(list.get(i-1));
			if(i%10==0) {
				int n = bottomPage(i,list.size());
				if(n==-2) break;
				if(n==-1) {
					if(list.get(i-1) instanceof FoodStore) {
						selectedFoodStore(selectStore(),course);
					} else if(list.get(i-1) instanceof Store) {
						selectedStore(selectStore(),course);
					}
					n=list.size()+1;
				}
				i = n;
			}
		}
		if(list.get(0) instanceof FoodStore) {
			selectedFoodStore(selectStore(),course);
		} else if(list.get(0) instanceof Store) {
			selectedStore(selectStore(),course);
		}
	}
	private int bottomPage(int i,int size){
		int result = -2;
		while(true) {
			System.out.println("===================================");
			System.out.println(" 1.이전페이지  2.다음페이지  3.선택  9.취소");
			int menuNo = menuSelect();
			if(menuNo==1) {
				if(i<=20) result=0;
				else result= (i-20);
				break;
			} else if(menuNo==2) {
				result= i;
				break;
			} else if(menuNo==3) {
				result= -1;
				break;
			} else if(menuNo==9){
				break;
			} else {
				System.out.println(" 잘못된 번호를 입력하였습니다.");
			}
		}
		return result;
	}
	private void showMyInfo() {
		System.out.println("==========================================================");
		System.out.println(MemberDAO.getInstance().selectOne(loginInfo));
	}
}
