<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
body {   
	text-align:center;
	background-image:url("../images/3.jpg");
   	background-repeat: no-repeat; 	
   
}

table {	
	height: 20px;
	text-align:center;
	margin: auto;	
	font-size:30px;
}
button{
	font-size: 20px;
	background-color: pink;
}
input{
	font-size: 20px;
	background-color: pink;
}
.abc {
	text-align:left;
	color:#443FD6;
	font-weight:bold;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여름나라의 러블리즈</title>
</head>
<body>
<p></p>
<br><br><br><br>
<h1 style="color:#D918EF;">~~~~~~~lovelinus 회원 정보 변경~~~~~~~</h1>
<br><br><br><br><br>

<form action="update" method="post">
<table border=1>
<tr>
<td class="abc">회원 ID :</td><td> <input type="text" name="loginId" value="${emps.loginId}" readonly></td></tr>
<tr>
<td class="abc">비밀번호 :</td><td> <input type="password" name="loginPassword"></td></tr>
<tr>
<td class="abc">사원번호:</td><td> <input type="text" name="employeeId" value="${emps.employeeId}"></td></tr>
<tr>
<td class="abc">성 :</td><td> <input type="text" name="firstName" value="${emps.firstName}"></td></tr>
<tr>
<td class="abc">이름 :</td><td> <input type="text" name="lastName" value="${emps.lastName}"></td></tr>
<tr>
<td class="abc">이메일 :</td><td> <input type="text" name="email" placeholder="xxxx@xxxx.xxx" value="${emps.email}"></td></tr>
<tr>
<td class="abc">전화번호 :</td><td> <input type="text" name="phoneNumber" placeholder="010.xxxx.xxxx" value="${emps.phoneNumber}"></td></tr>



<tr>
<td class="abc">직무 : </td>
<td class="abc"><select name="jobId">
<c:forEach var="job" items="${JobId}">
	<option value="${job.jobId}">${job.title}</option>
</c:forEach>
</select></td></tr>

<tr>
<td class="abc">급여 :</td><td> <input type="text" name="salary" value="${emps.salary}"></td></tr>
<tr>
<td class="abc">보너스율 :</td><td> <input type="text" name="commissionPct" value="${emps.commissionPct}"></td></tr>
<tr>
<td class="abc">매니저ID :</td><td> <input type="text" name="managerId" placeholder="ex) 100" value="${emps.managerId}"></td></tr>

<tr>
<td class="abc">부서 : </td>
<td class="abc">
<select name="departmentId">
<c:forEach var="map" items="${DeptId}">
<c:forEach var="dept" items="${map}">
	<option value="${dept.key}">${dept.value}</option>
</c:forEach>
</c:forEach>
</select></td></tr>

</table>
<input type="submit" value="수 정 " onclick="return confirm('회원 정보 수정을 완료하시겠습니까?')">
<a href="login"><input type="button" value=" 취 소 " ></a>
</form>

</body>
</html>