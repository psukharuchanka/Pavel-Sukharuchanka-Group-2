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
    <h3>Accounts</h3>
    <c:forEach items="${banks}" var="bank">
        <c:forEach items="${bank.accounts}" var="account">
            <a href="getAccount?id=<c:out value='${account.id}' />"><c:out value="${account.name}" /></a> <br>
            <c:forEach items="${account.balance}" var="balance">
				&nbsp;&nbsp;
                <c:out value="${balance.name}" />-<c:out value="${balance.amount}" /><br>
            </c:forEach>
        </c:forEach>
    </c:forEach>
		
    <h3>Rates</h3>
    <c:forEach items="${banks}" var="bank">
        <c:forEach items="${bank.rates}" var="rate">
            <c:out value="${rate.firstCurrency}" />/<c:out value="${rate.secondCurrency}" />=
            <c:out value="${rate.rate}" /><br>
        </c:forEach>
    </c:forEach>
</body>
</html>