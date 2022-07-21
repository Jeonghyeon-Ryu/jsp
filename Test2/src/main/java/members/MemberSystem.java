package members;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
		if(member==null) {
			System.out.println("회원가입이 취소되었습니다.");
			return;
		}
		mDAO.insert(member);
	}
	public boolean isAlphaNumeric(String str) {
		return Pattern.matches("[a-zA-Z0-9]{4,12}$",str);
	}
	public boolean isEmail(String str) {
		return Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$",str);
	}
	public boolean isPersonalID(String str) {
		return Pattern.matches("^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4]$",str);
	}
	public boolean isName(String str) {
		return Pattern.matches("^[a-zA-Z가-힣]*$",str);
	}
	public boolean isPhone(String str) {
		return Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",str);
	}


	private Member inputAll() {
		Member member = new Member();
		while(true) {
			System.out.print("        > 아이디(4-12자리,특수문자 제외) : ");
			String id = sc.nextLine();
			if(isAlphaNumeric(id)) {
				if(MemberDAO.getInstance().selectOne(id)==null) {
					member.setId(id);
					break;
				} else {
					System.out.println("        > 이미 존재하는 아이디입니다.");
					continue;
				}
			} else if (id.equals("9")) {
				return null;
			} else {
				System.out.println("        > 형식에 맞게 입력해주세요(종료:9).");
				continue;
			}
		}
		System.out.print("        > 비밀번호 : ");
		member.setPw(sc.nextLine());
		while(true) {
			System.out.print("        > 이름 : ");
			String name = sc.nextLine();
			if(isName(name)) {
				member.setName(name);
				break;
			} else if (name.equals("9")) {
				return null;
			} else {
				System.out.println("        > 이름이 올바르지 않습니다.(종료:9).");
				continue;
			}
		}
		while(true) { 
			System.out.print("        > 주민등록번호(예 : 901201-1) : ");
			String birth = sc.nextLine();
			if(isPersonalID(birth)) {
//				member.setSex(Integer.parseInt(String.valueOf(birth.charAt(7))));
//				member.setBirth(convertDate(birth));
				break;
			} else if (birth.equals("9")) {
				return null;
			} else {
				System.out.println("        > 형식에 맞게 입력해주세요(종료:9).");
				continue;
			}
		}
		System.out.print("        > 주소 : ");
		member.setAddress(sc.nextLine());
		while(true) {
			System.out.print("        > 전화번호(010-0000-0000) : ");
			String phone = sc.nextLine();
			if(isPhone(phone)) {
				member.setPhone(phone);
				break;
			} else if (phone.equals("9")) {
				return null;
			} else {
				System.out.println("        > 형식이 올바르지 않습니다.(종료:9).");
				continue;
			}
		}
		System.out.print("        > 전화번호(010-0000-0000) : ");
		member.setPhone(sc.nextLine());
		return member;
	}
	private Date convertDate(String birth) {
		int year = Integer.parseInt(String.valueOf(birth.charAt(7)));
		Date result = null;
		int sysYear = Calendar.getInstance().get(Calendar.YEAR);
		if(year>2) {
			year=2000+Integer.parseInt(birth.substring(0,2));
		} else {
			year=1900+Integer.parseInt(birth.substring(0,2));
		}
		
		String temp = String.valueOf(year)+"-"+birth.substring(2, 4)+"-"+birth.substring(4, 6);
		try {
			if(year>sysYear)
				throw new Exception("당신은 미래에서 오셨습니까?");
			result = Date.valueOf(temp);
		} catch (Exception e) {
			System.out.println("        > 잘못된 주민등록번호를 입력하였습니다.  " + e.getMessage());
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
