package com.web.jsp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class calc extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String paramX = request.getParameter("x");
		String paramY = request.getParameter("y");
		String operator = request.getParameter("calc-btn");
		
		int x=0;
		int y=0;
		
		if(paramX != null && !paramX.equals("")) {
			x = Integer.parseInt(paramX);
		}
		if(paramY != null && !paramY.equals("")) {
			y = Integer.parseInt(paramY);
		}
		if(operator=="µ¡¼À") {
			response.getWriter().println(x+y);
		}else if(operator=="»¬¼À") {
			response.getWriter().println(x-y);
		}			
	}
}
