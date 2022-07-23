package com.edu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.common.EmpDAO;

@WebServlet("/addmember")
public class AddMemberServ extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		// 사용자 User_name=user2 & User_pass=1234 & Role=1
		String name = req.getParameter("user_name");
		String pass = req.getParameter("user_pass");
		String role = req.getParameter("role");
		
		EmpDAO dao = new EmpDAO();
		// get:수정, post:입력
		if(req.getMethod().toUpperCase().equals("GET")) {
<<<<<<< HEAD
			if(dao.updateMember(name, pass, role)>0) {
				resp.getWriter().print("회원 정보 수정 성공");
			}else {
				resp.getWriter().print("<script>alert('회원 정보 수정 실패')</script>");
=======
			if(dao.updateMember(name, pass, role)>0){
				resp.getWriter().print("회원 정보 수정 성공");
			}else {
				resp.sendRedirect("html/get.html");
				resp.getWriter().print("<script>alert('정보수정 실패')</script>");
>>>>>>> branch 'master' of https://github.com/Jeonghyeon-Ryu/jsp.git
			}
		} else {
<<<<<<< HEAD
			if(dao.insertMember(name, pass, role)>0) {
				resp.getWriter().print("회원가입 성공");
			}else {
				resp.getWriter().print("<script>alert('회원가입 실패')</script>");
			}
			
=======
			if(dao.insertMember(name, pass, role)>0){
				resp.getWriter().print("회원가입 성공");
			}else {
				resp.getWriter().print("<script>alert('회원가입 실패')</script>");
				resp.sendRedirect("html/get.html");
			}
>>>>>>> branch 'master' of https://github.com/Jeonghyeon-Ryu/jsp.git
		}
<<<<<<< HEAD
=======
		
>>>>>>> branch 'master' of https://github.com/Jeonghyeon-Ryu/jsp.git
	}
}
