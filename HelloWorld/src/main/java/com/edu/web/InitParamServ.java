package com.edu.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitParamServ extends HttpServlet{
	// 생성(생성자) -> ServletConfig -> init(sc) -> service(rq,rs)
	
	String id;
	String pw;
	
	public InitParamServ() {
		System.out.println("생성자 호출됨.");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		id = config.getInitParameter("id");
		pw = config.getInitParameter("pw");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		out.print("<h3>서블릿 초기변수 설정</h3>");
		out.print("<p>ID : " + id + "</p>");
		out.print("<p>PW : " + pw + "</p>");
		
		out.close();
	}
}
