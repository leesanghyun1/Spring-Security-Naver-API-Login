<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자</title>
</head>
<body>
관리자 페이지 


<P>  <a href="<c:url value="/user/logout"/>">Logout</a> </P>

<P> auth: <c:out value="${auth }"></c:out> </P>
<br><br>
<P> vo: <c:out value="${vo }"></c:out> </P>
<br><br>
<P> name: <c:out value="${name }"></c:out> </P>

</body>
</html>