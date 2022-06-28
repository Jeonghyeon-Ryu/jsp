package members;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import common.AdminSystem;
import common.UserSystem;

public class MemberSystem {
	private Scanner sc = new Scanner(System.in); 
	private MemberDAO mDAO = MemberDAO.getInstance();
	private static Member loginInfo = null;
	
	public MemberSystem() {
		while(true) {
			menuPrint();
			int menuNo = menuSelect();
			if(menuNo == 1) {	// ȸ������
				signUp();
			} else if(menuNo == 2) {	// �α���
				login();
			} else if(menuNo == 3) {	// ȸ������
//				updateUser();
			} else if(menuNo == 4) {	// ȸ��Ż��
//				deleteUser();
			} else if(menuNo == 5) {	// ������
//				selectOneUser();
			} else if(menuNo == 6) {	// ��ü���
				selectAllUser();
			} else if(menuNo == 9) {	// ����
				exit();
				break;
			} else {
				showInputError();
			}
		}
	}
	private void menuPrint() {
		System.out.println("========================");
		System.out.println(" 1.회원가입  2.로그인  9.종료");
		System.out.println("========================");
	}
	private int menuSelect() {
		int menuNo = 0;
		System.out.print(" 메뉴 선택 > ");
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("        > 숫자를 입력해주세요.");
		}
		return menuNo; 
	}
	private void exit() {
		System.out.println("        > 종료합니다.");
	}
	private void showInputError() {
		System.out.println("        > 메뉴를 잘못 선택하였습니다.");
	}
	private void signUp() {
		Member member = inputAll();
		mDAO.insert(member);
	}
	private Member inputAll() {
		Member member = new Member();
		System.out.print("        > 아이디 : ");
		member.setId(sc.nextLine());
		System.out.print("        > 비밀번호 : ");
		member.setPw(sc.nextLine());
		System.out.print("        > 이름 : ");
		member.setName(sc.nextLine());
		while(true) { 
			System.out.print("        > 주민등록번호(예 : 901201-1) : ");
			String birth = sc.nextLine();
			if(birth.length()!=8) {
				System.out.println("        > 형식에 맞게 입력해주세요.");
				continue;
			}
			try {
				member.setSex(Integer.parseInt(String.valueOf(birth.charAt(7))));
				member.setBirth(convertDate(birth));
			} catch(NumberFormatException e) {
				System.out.println("        > 형식에 맞게 입력해주세요.");
				member.setBirth(Date.valueOf("1000-01-01"));
			} 
			System.out.println(member.getBirth().toString());
			if(member.getBirth().toString().equals("1000-01-01")) {
				continue;
			}
			break;
		}
		System.out.print("        > 주소 : ");
		member.setAddress(sc.nextLine());
		System.out.print("        > 전화번호 : ");
		member.setPhone(sc.nextLine());
		return member;
	}
	private Date convertDate(String birth) {
		int year = Integer.parseInt(birth.substring(0, 2));
		Date result = null;
		if(year>50) {
			year+=1900;
		} else {
			year+=2000;
		}
		
		String temp = String.valueOf(year)+"-"+birth.substring(2, 4)+"-"+birth.substring(4, 6);
		System.out.println(temp);
		try {
			result = Date.valueOf(temp);
		} catch (Exception e) {
			System.out.println("        > 잘못된 주민등록번호를 입력하였습니다.");
			result = Date.valueOf("1000-01-01");
		}
	
		return result;
	}
	private void selectAllUser() {
		List<Member> list = mDAO.selectAll();
		
		list.forEach( x -> System.out.println(x) );
	}
	private void login() {
		Member member = inputMember();
		loginInfo = mDAO.selectOne(member);
		if(loginInfo==null) return;

		System.out.println("        > 로그인 되었습니다.");
		if(loginInfo.getAuthority() == 0)
			new UserSystem(loginInfo);
		else
			new AdminSystem();
	}
	private Member inputMember() {
		Member member = new Member();
		System.out.print("        > 아이디 : ");
		member.setId(sc.nextLine());
		System.out.print("        > 비밀번호 : ");
		member.setPw(sc.nextLine());
		
		return member;
	}
}
