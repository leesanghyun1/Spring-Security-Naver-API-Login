<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${errMsg }
<form action='<c:url value="/user/login"/>' method="post">
	<input type="text" name="id" id="id"/>
	<input type="password" name="password" id="password"/>
	<input id="remember_me" name="_spring_security_remember_me" type="checkbox">Remember me
	<input type="submit" value="로그인">
</form>


<form action='<c:url value="/user/insertUser"/>' method="post">
	id: <input type="text" name="id" id="id"/>
	pw: <input type="password" name="password" id="password"/>
	이름: <input type="text" name="name" id="name"/>
	<select name="authority">
		<option value="ROLE_USER">사용자</option>
		<option value="ROLE_ADMIN">관리자</option>
	</select>
	<input type="submit" value="회원가입">
</form>
<br>
<!-- 네이버 로그인 -->
<div id="naver_id_login" style="text-align: center"> 
	<a href="${url}">
	<img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/></a> 
	</div>
</body>
</html>