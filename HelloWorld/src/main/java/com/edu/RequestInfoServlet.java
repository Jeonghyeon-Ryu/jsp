package com.edu;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reqinfo")
public class RequestInfoServlet extends HttpServlet{
	// init() -> request, response 생성  -> service() 
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8");
		// 네트워크 정보
		PrintWriter out = resp.getWriter();
		out.print("<p>Request Schema : " + req.getScheme() + "</p>");
		out.print("<p>Server Name : " + req.getServerName() + "</p>" );
		out.print("<p>Server Address : " + req.getLocalAddr() + "</p>" );
		out.print("<p>Server Port : " + req.getServerPort() + "</p>" );
		out.print("<p>Client Address : " + req.getRemoteAddr() + "</p>" );
		out.print("<p>Client Host : " + req.getRemoteHost() + "</p>" );
		out.print("<p>Client Port : " + req.getRemotePort() + "</p>" );
		
		String str = "<h3>URL정보</h3>";
		str += "<p>Request URI: " + req.getRequestURI() + "</p>";
		str += "<p>Request URL: " + req.getRequestURL() + "</p>";
		str += "<p>Context Path: " + req.getContextPath() + "</p>";
		str += "<p>Request Protocol : " + req.getProtocol() + "</p>";
		str += "<p>Servlet Path : " + req.getServletPath() + "</p>";
		
		str += "<h3>요청 헤더 정보</h3>";
		Enumeration<String> en = req.getHeaderNames();
		while(en.hasMoreElements()) {
			String headerName = en.nextElement();
			String headerValue = req.getHeader(headerName);
			
			str += "<p>"+ headerName +" : " + headerValue + "</p>";
		}
		
		
		out.print(str);
		
		out.close();
	}
}
