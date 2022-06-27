package stores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class StoreDAO extends DAO{
	private static StoreDAO dao = null;
	private StoreDAO() {}
	public static StoreDAO getInstance() {
		if(dao==null)
			dao=new StoreDAO();
		return dao;
	}
	public void insert(Store store) {
		int result = 0;
		try {
			connect();
			String sql = "INSERT INTO store(id,category_id,name,address,phone,notice) VALUES(store_seq.nextval,?,?,?,?,?)";
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
			System.out.println("= INSERT " + result + " ROWS ( STORE )");
		}
	}
	public void update(Store store) {
		int result = 0;
		try {
			connect();
			String sql = "UPDATE store SET name=?,address=?,phone=?,notice=? WHERE id=?";
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
			System.out.println("= UPDATE " + result + " ROWS ( STORE )");
		}
	}
	public void delete(int id) {
		int result = 0;
		try {
			connect();
			String sql = "DELETE FROM store WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
			System.out.println("= DELETE " + result + " ROWS ( STORE )");
		}
	}
	public Store selectOne(int id) {
		Store store = null;
		try {
			connect();
			String sql = "SELECT * FROM store WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				store = new Store();
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
	public List<Store> selectAll(String address) {
		List<Store> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM store WHERE address LIKE ('%"+address+"%')";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Store store = new Store();
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
	public List<Store> selectAll(int category, String address) {
		List<Store> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM store WHERE address LIKE ('%"+address+"%') WHERE category_id="+category;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Store store = new Store();
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
