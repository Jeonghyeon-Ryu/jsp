package amusementParks;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class AmusementParkDAO extends DAO{
	private static AmusementParkDAO dao = null;
	private AmusementParkDAO() {}
	public static AmusementParkDAO getInstance() {
		if(dao==null)
			dao=new AmusementParkDAO();
		return dao;
	}
	public void insert(AmusementPark amusementPark) {
		int result = 0;
		try {
			connect();
			String sql = "INSERT INTO amusement_park(id,name,address,phone,notice) VALUES(amusement_park_seq.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, amusementPark.getName());
			pstmt.setString(2, amusementPark.getAddress());
			pstmt.setString(3, amusementPark.getPhone());
			pstmt.setString(4, amusementPark.getNotice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(result + "���� ���� �߰��Ǿ����ϴ�. ( ���̰������ �Ϸ� )");
		}
	}
	public void update(AmusementPark cafe) {
		int result = 0;
		try {
			connect();
			String sql = "UPDATE amusement_park SET name=?,address=?,phone=?,notice=? WHERE id=?";
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
			System.out.println(result + "���� ���� �����Ǿ����ϴ�. ( ���̰������� �Ϸ� )");
		}
	}
	public void delete(int id) {
		int result = 0;
		try {
			connect();
			String sql = "DELETE FROM amusement_park WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println(result + "���� ���� �����Ǿ����ϴ�. ( ���̰������� �Ϸ� )");
		}
	}
	public AmusementPark selectOne(int id) {
		AmusementPark cafe = null;
		try {
			connect();
			String sql = "SELECT * FROM amusement_park WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cafe = new AmusementPark();
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
	public List<AmusementPark> selectAll(String address) {
		List<AmusementPark> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM amusement_park WHERE address LIKE ('%"+address+"%')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				AmusementPark cafe = new AmusementPark();
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
