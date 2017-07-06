<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html >
<html>
<head>
<style>
body {   
	text-align:center;  
	color:gray;
	font-size:20px;     	
}
table {
	font-size:30px;
	height: 20px;
	text-align:center;
	margin: auto;		
}
button{
	font-size: 20px;
	background-color: pink;
}
input{
	font-size: 20px;
	background-color: pink;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여름나라의 러블리즈</title>
</head>
<body>
<img src="<c:url value='../images/111.jpg'/>" width="1400px" height="650px">
<form action="delete" method="post">
<h1 style="color:#D918EF;">회원을 탈퇴하시려면 비밀번호를 입력해주세요.</h1>
<table>
<tr>
<td>ID :</td><td> <input type="text" name="loginId" value="${emps.loginId}" readonly ></td></tr>
<tr>
<td>PW :</td><td> <input type="password" name="loginPassword"></td></tr>

</table>
<input type="submit" value=" 확 인 " onclick="return confirm('정말로 삭제하시겠습니까?')">
<a href="login"><input type="button" value=" 취 소 " ></a>
</form>
</body>
</html>