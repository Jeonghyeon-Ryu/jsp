package common;

import java.util.List;
import java.util.Scanner;

import crawling.CrawlingSystem;
import members.Member;
import members.MemberDAO;

public class AdminSystem {
	private Scanner sc = new Scanner(System.in);
	
	public AdminSystem() {
		while(true) {
			menuPrint();
			int menuNo = menuSelect();
			if(menuNo == 1) {					// 유저 관리 ( CRUD )
				memberManagement();
			} else if (menuNo == 2) {			// 데이터베이스 업데이트 ( 특정 장소 추가 )
				new CrawlingSystem();
			} else if (menuNo == 9) {
				exit();
				break;
			} else {
				showInputError();
			}
		}
	}
	private void menuPrint() {
		System.out.println("==============================");
		System.out.println(" 1.유저관리  2.DB업데이트  9.로그아웃");
		System.out.println("==============================");
	}
	private int menuSelect() {
		int menuNo = 0;
		try {
			System.out.print(" 메뉴 선택 > ");
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("        > 숫자를 입력해주세요.");
		}
		return menuNo;
	}
	private void exit() {
		System.out.println("        > 관리 시스템을 종료합니다.");
	}
	private void back() {
		System.out.println("        > 뒤로갑니다.");
	}
	private void showInputError() {
		System.out.println("        > 잘못된 메뉴를 입력하였습니다. 다시 선택해주세요.");
	}
	
	private void memberManagement() {
		while(true) {
			managementMenuPrint();
			int menuNo = menuSelect();
			if(menuNo == 1) {	// 모든 유저 조회
				showAllMember();
			} else if(menuNo == 2) {	// 특정 유저 조회
				showOneMember();
			} else if(menuNo == 3) {	// 유저 차단
				showblockMember();
			} else if(menuNo == 4) {	// 유저 차단
				blockMember();
			} else if(menuNo == 9) {	// 뒤로가기
				back();
				break;
			} else {
				showInputError();
			}
		}
	}
	private void managementMenuPrint() {
		System.out.println("==========================================================");
		System.out.println(" 1.모든유저 조회  2.특정유저 조회  3.차단유저 조회  4.유저차단  9.뒤로가기");
		System.out.println("==========================================================");
	}
	private void showAllMember() {
		List<Member> list = MemberDAO.getInstance().selectAll();
		list.forEach(x -> System.out.println(x));
	}
	private void showOneMember() {
		Member member = null;
		String id = inputId();
		member = MemberDAO.getInstance().selectOne(id);
		if(member==null) {
			System.out.println("    > 존재하지 않는 회원입니다.");
			return;
		}
		System.out.println(member);
	}
	private String inputId() {
		String id = null;
		System.out.print(" ID > ");
		id = sc.nextLine();
		return id;
	}
	private void showblockMember() {
		List<Member> list = MemberDAO.getInstance().selectBlockAll();
		list.forEach(x -> System.out.println(x));
	}
	private void blockMember() {
		Member member = new Member();
		member.setId(inputId());
		member = MemberDAO.getInstance().selectOne(member);
		if(member==null) {
			System.out.println("    > 존재하지 않는 회원입니다.");
			return;
		}
		member.setAuthority(-1);
		MemberDAO.getInstance().update(member);
	}
}
