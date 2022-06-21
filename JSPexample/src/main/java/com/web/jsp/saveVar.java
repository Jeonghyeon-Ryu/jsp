package com.web.jsp;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saveVar")
public class saveVar extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		ServletContext application = req.getServletContext();
		int temp = 0;
		int result = 0;
		
		String x = req.getParameter("x");	// 4
		String op = req.getParameter("button");	//= 
		
		
		if(op.equals("=")) {
			if(x!=null && !x.equals("")) {
				result = Integer.parseInt(x);	//4
			} 
			temp = (int) application.getAttribute("value");	//3
			op = (String)application.getAttribute("operator");	//+
			if(op.equals("+"))
				result = temp+result;	// 3 + 4
			else if(op.equals("-"))
				result = temp-result;
			resp.getWriter().println(result);
		} else {
			
			if(x!=null && !x.equals("")) {
				application.setAttribute("value", x);
			} else {
				application.setAttribute("value", "0");
			}
			application.setAttribute("operator", op);
			resp.getWriter().println(application.getAttribute("value"));
			resp.getWriter().println(application.getAttribute("operator"));
		}
	}
}
