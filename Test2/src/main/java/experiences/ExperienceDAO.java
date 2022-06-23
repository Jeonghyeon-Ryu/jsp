package experiences;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class ExperienceDAO extends DAO {
	private void insert(Experience experience) {
		int result = 0;
		try {
			connect();
			String sql = "INSERT INTO experience(id,name,address,phone,notice) VALUES(experience_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, experience.getName());
			pstmt.setString(2, experience.getAddress());
			pstmt.setString(3, experience.getPhone());
			pstmt.setString(4, experience.getNotice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(result + "개의 행이 추가되었습니다. ( 체험등록 완료 )");
		}
	}
	public void update(Experience cafe) {
		int result = 0;
		try {
			connect();
			String sql = "UPDATE experience SET name=?,address=?,phone=?,notice=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cafe.getName());
			pstmt.setString(2, cafe.getAddress());
			pstmt.setString(3, cafe.getPhone());
			pstmt.setString(4, cafe.getNotice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(result + "개의 행이 수정되었습니다. ( 체험수정 완료 )");
		}
	}
	public void delete(int id) {
		int result = 0;
		try {
			connect();
			String sql = "DELETE FROM experience WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(result + "개의 행이 삭제되었습니다. ( 체험삭제 완료 )");
		}
	}
	public Experience selectOne(int id) {
		Experience cafe = null;
		try {
			connect();
			String sql = "SELECT * FROM experience WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cafe = new Experience();
				cafe.setId(id);
				cafe.setName(rs.getString("name"));
				cafe.setAddress(rs.getString("address"));
				cafe.setPhone(rs.getString("phone"));
				cafe.setNotice(rs.getString("notice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cafe;		
	}
	public List<Experience> selectAll(String address) {
		List<Experience> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM experience WHERE address LIKE ('%"+address+"%')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Experience cafe = new Experience();
				cafe.setId(rs.getInt("id"));
				cafe.setName(rs.getString("name"));
				cafe.setAddress(rs.getString("address"));
				cafe.setPhone(rs.getString("phone"));
				cafe.setNotice(rs.getString("notice"));
				list.add(cafe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
		
	}
}
