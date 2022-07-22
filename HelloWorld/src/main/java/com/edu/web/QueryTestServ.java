package com.edu.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/html/queryTest")
public class QueryTestServ extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String name = req.getParameter("name");
		String[] hobby = req.getParameterValues("hobby");
		String gender = req.getParameter("gender");
		String religion = req.getParameter("religion");
		String introduction = req.getParameter("introduction");
		
		out.print("<h3>입력받은 값</h3>");
		out.print("<p>ID : "+ id + "</p>");
		out.print("<p>PW : "+ pw + "</p>");
		out.print("<p>NAME : "+ name + "</p>");
		out.print("<ul>");
		for(String x : hobby) {
			out.print("<li>Hobby : "+ x + "</li>");
		}
		out.print("</ul>");
		out.print("<p>Gender : "+ gender + "</p>");
		out.print("<p>Religion : "+ religion + "</p>");
		out.print("<p>Intro : "+ introduction + "</p>");
		out.print("질의문자열 : " + req.getQueryString());
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
//		String id = req.getParameter("id");
//		String pw = req.getParameter("pw");
//		String name = req.getParameter("name");
//		String[] hobby = req.getParameterValues("hobby");
//		String gender = req.getParameter("gender");
//		String religion = req.getParameter("religion");
//		String introduction = req.getParameter("introduction");
//		out.print("<meta charset='UTF-8'>");
//		out.print("<h3>입력받은 값</h3>");
//		out.print("<p>ID : "+ id + "</p>");
//		out.print("<p>PW : "+ pw + "</p>");
//		out.print("<p>NAME : "+ name + "</p>");
//		out.print("<ul>");
//		for(String x : hobby) {
//			out.print("<li>Hobby : "+ x + "</li>");
//		}
//		out.print("</ul>");
//		out.print("<p>Gender : "+ gender + "</p>");
//		out.print("<p>Religion : "+ religion + "</p>");
//		out.print("<p>Intro : "+ introduction + "</p>");
		
		ServletInputStream sis = req.getInputStream();
		int len = req.getContentLength();
		byte[] buf = new byte[len];
		sis.readLine(buf, 0, len);
		String queryString = new String(buf);
		out.print("질의문자열 : " + queryString);
		sis.close();
		out.close();
	}
}
