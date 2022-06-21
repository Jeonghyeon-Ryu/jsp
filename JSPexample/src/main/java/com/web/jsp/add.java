package com.web.jsp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class add extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String paramX = request.getParameter("x");
		String paramY = request.getParameter("y");
		int result = 0;
		
		if(paramX != null && !paramX.equals("")) {
			result += Integer.parseInt(paramX);
		}
		if(paramY != null && !paramY.equals("")) {
			result += Integer.parseInt(paramY);
		}
		
		
		response.getOutputStream().println(result);
	}
}
