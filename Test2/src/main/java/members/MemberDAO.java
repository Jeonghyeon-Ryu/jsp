package members;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class MemberDAO extends DAO {
	//싱글톤
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
			System.out.println(result + "개의 행이 추가되었습니다. ( 회원등록 완료 )");
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
			System.out.println(result + "개의 행이 수정되었습니다. ( 회원수정 완료 )");
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
			System.out.println(result + "개의 행이 삭제되었습니다. ( 회원탈퇴 완료 )");
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
			if(rs.next()) {	//아이디 존재
				if(rs.getString("pw").equals(SHA256.encodeSha256(member.getPw()))) {
					// 비밀번호 일치 -> 로그인성공
					member.setAuthority(rs.getInt("authority"));
					loginInfo = member;
				} else {
					// 비밀번호 불일치 -> 로그인 실패
					System.out.println("비밀번호가 틀렸습니다.");
				}
			} else {	// 아이디 없음
				System.out.println("아이디가 존재하지 않습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("멤버 조회 실패");
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
			System.out.println("멤버 목록 조회 실패");
		} finally {
			disconnect();
		}
		
		return list;
	}
}
