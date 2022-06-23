package aquariums;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class AquariumDAO extends DAO{
	private void insert(Aquarium aquarium) {
		int result = 0;
		try {
			connect();
			String sql = "INSERT INTO aquarium(id,name,address,phone,notice) VALUES(aquarium_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aquarium.getName());
			pstmt.setString(2, aquarium.getAddress());
			pstmt.setString(3, aquarium.getPhone());
			pstmt.setString(4, aquarium.getNotice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(result + "개의 행이 추가되었습니다. ( 아쿠아리움등록 완료 )");
		}
	}
	public void update(Aquarium cafe) {
		int result = 0;
		try {
			connect();
			String sql = "UPDATE aquarium SET name=?,address=?,phone=?,notice=? WHERE id=?";
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
			System.out.println(result + "개의 행이 수정되었습니다. ( 아쿠아리움수정 완료 )");
		}
	}
	public void delete(int id) {
		int result = 0;
		try {
			connect();
			String sql = "DELETE FROM aquarium WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(result + "개의 행이 삭제되었습니다. ( 아쿠아리움삭제 완료 )");
		}
	}
	public Aquarium selectOne(int id) {
		Aquarium cafe = null;
		try {
			connect();
			String sql = "SELECT * FROM aquarium WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cafe = new Aquarium();
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
	public List<Aquarium> selectAll(String address) {
		List<Aquarium> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM aquarium WHERE address LIKE ('%"+address+"%')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Aquarium cafe = new Aquarium();
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
