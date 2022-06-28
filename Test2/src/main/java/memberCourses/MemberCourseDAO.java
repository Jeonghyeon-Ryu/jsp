package memberCourses;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DAO;

public class MemberCourseDAO extends DAO{
	private static MemberCourseDAO dao = null;
	private MemberCourseDAO() {}
	public static MemberCourseDAO getInstance() {
		if(dao == null)
			dao=new MemberCourseDAO();
		return dao;
	}
	
	public void insert(MemberCourse course) {
		int result = 0; 
		try {
			connect();
			String sql = "INSERT INTO member_course VALUES(member_course_seq.nextVal,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, course.getStep());
			pstmt.setString(2, course.getId());
			pstmt.setInt(3, course.getLocation_type());
			pstmt.setInt(4, course.getLocation_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "개의 행이 추가되었습니다.");
			disconnect();
		}
	}
	
	public List<MemberCourse> selectAll(String id) {
		int result = 0;
		List<MemberCourse> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM member_course WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberCourse course = new MemberCourse();
				course.setCourseId(rs.getInt("course_id"));
				course.setStep(rs.getInt("step"));
				course.setId(rs.getString("id"));
				course.setLocation_type(rs.getInt("location_type"));
				course.setLocation_id(rs.getInt("location_id"));
				list.add(course);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
