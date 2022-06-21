package com.web.jsp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/submitArr")
public class submitArr extends HttpServlet {
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		int result = 0;
		String[] sArr = req.getParameterValues("num");
		for(int i=0; i<sArr.length; i++) {
			result += Integer.parseInt(sArr[i]);
		}
		
		res.getWriter().println(result);
	}
}
