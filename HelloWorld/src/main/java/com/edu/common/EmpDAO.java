package com.edu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO extends DAO{
	
	public void updateMember(String name, String pass, String role) {
		connect();
		try {
			String sql = "UPDATE members SET member_pw=?,member_role=? where member_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setInt(2, Integer.parseInt(role));
			pstmt.setString(3, name);
			int result = pstmt.executeUpdate();
			if(result > 0 ) {
				System.out.println(result + "개의 행이 업데이트 되었습니다.");
			} else {
				System.out.println("회원정보 수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	public void insertMember(String name, String pass, String role ) {
		connect();
		try {
			String sql = "INSERT INTO members VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pass);
			pstmt.setInt(3, Integer.parseInt(role));
			int result = pstmt.executeUpdate();
			if(result>0) {
				System.out.println(result + "개의 행이 추가되었습니다.");
			} else {
				System.out.println("Insert Member 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	public List<Employee> getEmpInfo(String name){
		List<Employee> list = new ArrayList<>();
		connect();
		try {
			String sql = "SELECT * FROM employees WHERE first_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setSalary(rs.getInt("salary"));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	public List<Employee> selectAll(){
		List<Employee> list = new ArrayList<>();
		connect();
		try {
			String sql = "SELECT * FROM employees";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setDate(rs.getDate("hire_date"));
				emp.setSalary(rs.getInt("salary"));
				emp.setJobId(rs.getString("job_id"));
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
}
