package members;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MemberSystem {
	private Scanner sc = new Scanner(System.in); 
	private MemberDAO mDAO = MemberDAO.getInstance();
	private static Member loginInfo = null;
	
	public MemberSystem() {
		while(true) {
			menuPrint();
			int menuNo = menuSelect();
			if(menuNo == 1) {	// 회원가입
				signUp();
			} else if(menuNo == 2) {	// 로그인
				login();
			} else if(menuNo == 3) {	// 회원수정
//				updateUser();
			} else if(menuNo == 4) {	// 회원탈퇴
//				deleteUser();
			} else if(menuNo == 5) {	// 내정보
//				selectOneUser();
			} else if(menuNo == 6) {	// 전체목록
				selectAllUser();
			} else if(menuNo == 9) {	// 종료
				exit();
				break;
			} else {
				showInputError();
			}
		}
	}
	private void menuPrint() {
		System.out.println("============================================================");
		System.out.println(" 1.회원가입  2.로그인  3.회원수정  4.회원탈퇴  5.내정보  6.전체목록  9.종료");
		System.out.println("============================================================");
	}
	private int menuSelect() {
		System.out.print(" 메뉴 선택 > ");
		return Integer.parseInt(sc.nextLine());
	}
	private void exit() {
		System.out.println("종료합니다.");
	}
	private void showInputError() {
		System.out.println("잘못된 메뉴를 입력하였습니다.");
	}
	private void signUp() {
		Member member = inputAll();
		mDAO.insert(member);
	}
	private Member inputAll() {
		Member member = new Member();
		System.out.print("아이디 : ");
		member.setId(sc.nextLine());
		System.out.print("비밀번호 : ");
		member.setPw(sc.nextLine());
		System.out.print("이름 : ");
		member.setName(sc.nextLine());
		System.out.print("생년월일(예: 901201-1) : ");
		String birth = sc.nextLine();
		member.setSex(Integer.parseInt(String.valueOf(birth.charAt(7))));
		member.setBirth(convertDate(birth));
		System.out.print("주소 : ");
		member.setAddress(sc.nextLine());
		System.out.print("전화번호 : ");
		member.setPhone(sc.nextLine());
		return member;
	}
	private Date convertDate(String birth) {
		int year = Integer.parseInt(birth.substring(0, 2));
		if(year>50) {
			year+=1900;
		} else {
			year+=2000;
		}
		String temp = String.valueOf(year)+"-"+birth.substring(2, 4)+"-"+birth.substring(4, 6);
		System.out.println(temp);
		Date result = Date.valueOf(temp);
		
	
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

		System.out.println("로그인 성공");
	}
	private Member inputMember() {
		Member member = new Member();
		System.out.print(" 아이디 : ");
		member.setId(sc.nextLine());
		System.out.print(" 비밀번호 : ");
		member.setPw(sc.nextLine());
		
		return member;
	}
}
