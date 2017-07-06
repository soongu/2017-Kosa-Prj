<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
.btn{
	background-color: #FFF44E;
}
</style>
	<title>~~여름나라의 러블리즈~~</title>
</head>
<body>
<p>
<img src="<c:url value='/images/102.jpg'/>" width="1400px" height="720px">
</p>

<h1 style="color:pink;">
♥♥♥♥Lovelyz Entertainment♥♥♥♥
</h1>
<form id="login" action="hr/login" method="post">
<table>
<tr>
<td>ID :</td><td> <input type="text" name="loginId" ></td></tr>
<tr>
<td>PW :</td><td> <input type="password" name="loginPassword"></td></tr>

</table>
</form>
<input class="btn" type="submit" value=" 로그인 " form="login"> 
<a href="hr/insert"><button class="btn">회원 가입</button></a>
</body>
</html>
