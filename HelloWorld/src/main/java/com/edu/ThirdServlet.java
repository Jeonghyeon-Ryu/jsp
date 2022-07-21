package com.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/third")
public class ThirdServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		// 요청정보 : 질의문자열(값)
		String key = req.getParameter("key");
		// DB 파싱
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpInfo(key);
		
		PrintWriter wr = resp.getWriter();
		wr.print("<table border='1'>");
		wr.print("<thead><tr><th>사원번호</th><th>성</th><th>이름</th><th>이메일</th><th>고용일</th><th>급여</th><th>직무</th></tr></thead>");
		wr.print("<tbody>");
		list.forEach(x->{ 
			wr.print("<tr><td>"+x.getEmployeeId()+"</td><td>"+x.getFirstName()+"</td><td>"+x.getLastName()+"</td><td>"+x.getEmail()+"</td><td>"+x.getDate()+"</td><td>"+x.getSalary()+"</td><td>"+x.getJobId()+"</td></tr>");
		});
		wr.print("</tbody>");
		wr.print("</table>");
		
	}
}
