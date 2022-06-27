package foodstore;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class FoodStoreDAO extends DAO{
	private static FoodStoreDAO dao = null;
	private FoodStoreDAO() {}
	public static FoodStoreDAO getInstance() {
		if(dao==null)
			dao=new FoodStoreDAO();
		return dao;
	}
	public void insert(FoodStore store) {
		int result = 0;
		try {
			connect();
			String sql = "INSERT INTO foodstore(id,category_id,name,address,phone,notice) VALUES(foodstore_seq.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, store.getCategory_id());
			pstmt.setString(2, store.getName());
			pstmt.setString(3, store.getAddress());
			pstmt.setString(4, store.getPhone());
			pstmt.setString(5, store.getNotice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println("= INSERT " + result + " ROWS ( FOODSTORE )");
		}
	}
	public void update(FoodStore store) {
		int result = 0;
		try {
			connect();
			String sql = "UPDATE foodstore SET name=?,address=?,phone=?,notice=? WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, store.getName());
			pstmt.setString(2, store.getAddress());
			pstmt.setString(3, store.getPhone());
			pstmt.setString(4, store.getNotice());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println("= UPDATE " + result + " ROWS ( FOODSTORE )");
		}
	}
	public void delete(int id) {
		int result = 0;
		try {
			connect();
			String sql = "DELETE FROM foodstore WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println("= DELETE " +result + " ROWS ( FOODSTORE )");
		}
	}
	public FoodStore selectOne(int id) {
		FoodStore store = null;
		try {
			connect();
			String sql = "SELECT * FROM foodstore WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				store = new FoodStore();
				store.setId(id);
				store.setCategory_id(rs.getInt("category_id"));
				store.setName(rs.getString("name"));
				store.setAddress(rs.getString("address"));
				store.setPhone(rs.getString("phone"));
				store.setNotice(rs.getString("notice"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return store;		
	}
	public List<FoodStore> selectAll(String address) {
		List<FoodStore> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM foodstore WHERE address LIKE ('%"+address+"%')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				FoodStore store = new FoodStore();
				store.setId(rs.getInt("id"));
				store.setCategory_id(rs.getInt("category_id"));
				store.setName(rs.getString("name"));
				store.setAddress(rs.getString("address"));
				store.setPhone(rs.getString("phone"));
				store.setNotice(rs.getString("notice"));
				list.add(store);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	public List<FoodStore> selectAll(int category, String address) {
		List<FoodStore> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM foodstore WHERE address LIKE ('%"+address+"%') AND category_id="+category;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				FoodStore store = new FoodStore();
				store.setId(rs.getInt("id"));
				store.setCategory_id(rs.getInt("category_id"));
				store.setName(rs.getString("name"));
				store.setAddress(rs.getString("address"));
				store.setPhone(rs.getString("phone"));
				store.setNotice(rs.getString("notice"));
				list.add(store);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
