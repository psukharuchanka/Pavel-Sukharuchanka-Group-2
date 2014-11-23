<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.bank.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Bank</title>
</head>
<body>
	<h3>Account</h3>

	<c:out value="${account.name}" />
	<br>
	<c:forEach items="${account.balance}" var="balance">
		&nbsp;&nbsp;<c:out value="${balance.name}" />-<c:out
			value="${balance.amount}" />
		<br>
	</c:forEach>

	<form method="post" action="getAccount">
	    <input type="hidden" name=id value="<c:out value='${account.id}' />">
		<input type="hidden" name="currency" value="USDtoEUR">
		<input type="submit" value="Convert USD to EUR">
	</form>

	<form method="post" action="getAccount">
	    <input type="hidden" name=id value="<c:out value='${account.id}' />">
		<input type="hidden" name="currency" value="EURtoUSD">
		<input type="submit" value="Convert EUR to USD">
	</form>

	<a href="getBank">Back to list</a>

</body>
</html>