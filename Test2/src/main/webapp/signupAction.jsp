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
	
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>TraBee</title>
</head>
<%
	pageContext.setAttribute("aa", "hello");
%>
<body>
	${pageScope.aa} <br>
	${empty param.n?'값이 비어있습니다.':param.n} <br>
	${param.n/2 }<br>
	${header.accept }
</body>
</html>                                                                                                                                                                                   