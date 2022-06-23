package members;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class MemberDAO extends DAO {
	//�̱���
	private static MemberDAO mDAO = null;
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(mDAO == null) {
			mDAO = new MemberDAO();
		}
		return mDAO;
	}
	//CRUD
	public void insert(Member member) {
		int result = 0;
		try {
			connect();
			String sql = "INSERT INTO members(id,pw,name,sex,birth,address,phone) VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			String password = SHA256.encodeSha256(member.getPw());
			pstmt.setString(2, password);
			pstmt.setString(3, member.getName());
			pstmt.setInt(4, member.getSex());
			pstmt.setDate(5, member.getBirth());
			pstmt.setString(6, member.getAddress());
			pstmt.setString(7, member.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(result + "���� ���� �߰��Ǿ����ϴ�. ( ȸ����� �Ϸ� )");
		}
	}
	public void update(Member member) {
		int result = 0;
		try {
			connect();
			String sql = "UPDATE members SET pw=?,name=?,sex=?,birth=?,address=?,phone=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setInt(3, member.getSex());
			pstmt.setDate(4, member.getBirth());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getPhone());
			pstmt.setString(7, member.getId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(result + "���� ���� �����Ǿ����ϴ�. ( ȸ������ �Ϸ� )");
		}
	}
	public void delete(String id) {
		int result = 0;
		try {
			connect();
			String sql = "DELETE FROM members WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(result + "���� ���� �����Ǿ����ϴ�. ( ȸ��Ż�� �Ϸ� )");
		}
	}
	public Member selectOne(Member member) {
		Member loginInfo = null;
		try {
			connect();
			String sql = "SELECT * FROM members WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {	//���̵� ����
				if(rs.getString("pw").equals(SHA256.encodeSha256(member.getPw()))) {
					// ��й�ȣ ��ġ -> �α��μ���
					member.setAuthority(rs.getInt("authority"));
					loginInfo = member;
				} else {
					// ��й�ȣ ����ġ -> �α��� ����
					System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
				}
			} else {	// ���̵� ����
				System.out.println("���̵� �������� �ʽ��ϴ�.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��� ��ȸ ����");
		} finally {
			disconnect();
		}
		
		return loginInfo;
	}
	
	public List<Member> selectAll() {
		List<Member> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM members";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Member member = new Member();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setSex(rs.getInt("sex"));
				member.setBirth(rs.getDate("birth"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				member.setAuthority(rs.getInt("authority"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��� ��� ��ȸ ����");
		} finally {
			disconnect();
		}
		
		return list;
	}
}
