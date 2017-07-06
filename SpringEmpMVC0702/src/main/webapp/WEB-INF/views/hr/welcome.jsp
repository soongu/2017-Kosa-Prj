<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>

body {   
	text-align:center;   	
}
button{
	font-size: 20px;
	background-color: pink;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${firstName}${lastName}님 안녕하세요~</title>

</head>
<body>
<img src="<c:url value='/images/11.jpg'/>" width="1400px" height="750px">
<h1 style="color:#E24AF3;">러블리즈 나라에 오신 ${emps.firstName}${emps.lastName}님 환영합니다!</h1>
<a href="update"><button>사원 정보 수정</button></a>
<a href="delete"><button>사원 탈퇴</button></a>
<a href="list"><button>사원 목록 조회</button></a>
<a href="logout" ><button onclick="return confirm('정말로 로그아웃하시겠습니까?')">로그아웃</button></a>

</body>
</html>