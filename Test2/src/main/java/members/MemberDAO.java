package members;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class MemberDAO extends DAO {
	// Singleton
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
			String sql = "INSERT INTO members(id,pw,name,identification,address,phone) VALUES(?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			String password = SHA256.encodeSha256(member.getPw());
			pstmt.setString(2, password);
			pstmt.setString(3, member.getName());
			pstmt.setInt(4, member.getIdentification());
			pstmt.setString(5, member.getAddress());
			pstmt.setString(6, member.getPhone());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(" INSERTED " + result + " ROWS (MEMBER)");
		}
	}
	public void update(Member member) {
		int result = 0;
		try {
			connect();
			String sql = "UPDATE members SET pw=?,name=?,address=?,phone=?,authority=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getPhone());
			pstmt.setInt(5, member.getAuthority());
			pstmt.setString(6, member.getId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(" UPDATED " + result + " ROWS (MEMBER)");
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
			System.out.println(" DELETED " + result + " ROWS (MEMBER)");
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
			if(rs.next()) {
				if(rs.getString("pw").equals(SHA256.encodeSha256(member.getPw()))) {
					if(rs.getInt("authority")==-1) {
						System.out.println("= Blocked User");
					} else {
						loginInfo = new Member();
						loginInfo.setId(rs.getString("id"));
						loginInfo.setPw(rs.getString("pw"));
						loginInfo.setName(rs.getString("name"));
						loginInfo.setIdentification(rs.getInt("identification"));
						loginInfo.setAddress(rs.getString("address"));
						loginInfo.setPhone(rs.getString("phone"));
						loginInfo.setAuthority(rs.getInt("authority"));
					}
				} else {
					System.out.println(" = Password not matched");
				}
			} else {
				System.out.println(" = User ID not found");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return loginInfo;
	}
	public Member selectOne(String id) {
		Member member = null;
		try {
			connect();
			String sql = "SELECT * FROM members WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setIdentification(rs.getInt("identification"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				member.setAuthority(rs.getInt("authority"));
			} else {
				System.out.println(" = 해당 아이디는 사용되고있지 않은 아이디입니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return member;
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
				member.setIdentification(rs.getInt("identification"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				member.setAuthority(rs.getInt("authority"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
	public List<Member> selectBlockAll() {
		List<Member> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM members WHERE authority=-1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Member member = new Member();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setIdentification(rs.getInt("identification"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				member.setAuthority(rs.getInt("authority"));
				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return list;
	}
}
