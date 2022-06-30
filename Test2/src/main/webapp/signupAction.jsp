<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="members.Member"%>
<%@ page import="members.MemberDAO"%>
<%@ page import="java.io.PrintWriter" %>
<jsp:useBean id="member" class="members.Member" scope="page"/>
<jsp:setProperty name="member" property="id"/>
<jsp:setProperty name="member" property="pw"/>
<jsp:setProperty name="member" property="name"/>
<% 
	MemberDAO mDAO = MemberDAO.getInstance();
	List<Member> a = mDAO.selectAll();
	System.out.println(member.getId());
	System.out.println(member.getPw());
	System.out.println(member.getName());
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TraBee</title>
</head>
<body>

</html>                                                                                                                                                                                   