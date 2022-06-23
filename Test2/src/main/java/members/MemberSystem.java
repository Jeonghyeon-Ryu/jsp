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
		System.out.println("============================================================");
		System.out.println(" 1.ȸ������  2.�α���  3.ȸ������  4.ȸ��Ż��  5.������  6.��ü���  9.����");
		System.out.println("============================================================");
	}
	private int menuSelect() {
		System.out.print(" �޴� ���� > ");
		return Integer.parseInt(sc.nextLine());
	}
	private void exit() {
		System.out.println("�����մϴ�.");
	}
	private void showInputError() {
		System.out.println("�߸��� �޴��� �Է��Ͽ����ϴ�.");
	}
	private void signUp() {
		Member member = inputAll();
		mDAO.insert(member);
	}
	private Member inputAll() {
		Member member = new Member();
		System.out.print("���̵� : ");
		member.setId(sc.nextLine());
		System.out.print("��й�ȣ : ");
		member.setPw(sc.nextLine());
		System.out.print("�̸� : ");
		member.setName(sc.nextLine());
		System.out.print("�������(��: 901201-1) : ");
		String birth = sc.nextLine();
		member.setSex(Integer.parseInt(String.valueOf(birth.charAt(7))));
		member.setBirth(convertDate(birth));
		System.out.print("�ּ� : ");
		member.setAddress(sc.nextLine());
		System.out.print("��ȭ��ȣ : ");
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

		System.out.println("�α��� ����");
	}
	private Member inputMember() {
		Member member = new Member();
		System.out.print(" ���̵� : ");
		member.setId(sc.nextLine());
		System.out.print(" ��й�ȣ : ");
		member.setPw(sc.nextLine());
		
		return member;
	}
}
