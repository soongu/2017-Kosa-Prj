<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<!DOCTYPE html>
<html>
<head>
<style>
body {   
	text-align:center;  
	color:gray;
	font-size:20px;     	
}
button{
	font-size: 20px;
	background-color: pink;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여름나라의 러블리즈</title>
</head>
<body>
<img src="<c:url value='../images/89.jpg'/>" width="1400px" height="650px">
<h1 style="color:#D918EF;">${firstName}${lastName}님! 비밀 번호가 틀렸습니다!!</h1><br>
<a href="delete"><button>뒤로</button></a>
<a href="login"><button>홈으로</button></a>
</body>
</html>