<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>에러 발생!!</h1>
<p> 에러가 발생했습니다. 관리자에게 문의하세요</p>

<!--  
	Failed URL: ${url}
	Exception: ${exception.message}
		<c:forEach items="${exception.stackTrace}" var="ste"> ${ste}
	</c:forEach>

-->
</body>
</html>