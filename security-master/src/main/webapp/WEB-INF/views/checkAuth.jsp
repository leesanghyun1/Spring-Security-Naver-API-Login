<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>">
	<title>Home</title>
</head>
<body>
<P>  <a href="<c:url value="/user/logout"/>">Logout</a> </P>

<P> auth: <c:out value="${auth }"></c:out> </P>
<br><br>
<P> vo: <c:out value="${vo }"></c:out> </P>
<br><br>
<P> name: <c:out value="${name }"></c:out> </P>

</body>
</html>
