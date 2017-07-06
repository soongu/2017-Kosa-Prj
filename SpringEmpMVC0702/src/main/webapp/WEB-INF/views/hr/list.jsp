<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	background-image: url("../images/91.jpg");
	background-repeat: no-repeat;
	background-size: 2500px 1100px;
}

td {
	color: #C8EA31;
	font-size: 25px;
}

th{
	color: orange;
	font-size: 30px;
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
	<table border=1>
		<tr>
			<th>ID</th>
			<th>사원번호</th>
			<th>사원이름</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>입사일</th>
			<th>직무</th>
			<th>급여</th>
			<th>커미션</th>
			<th>담당매니저</th>
			<th>부서ID</th>

		</tr>
		<c:forEach var="emps" items="${empList}">
			<tr>
				<td>${emps.loginId}</td>
				<td><a href="<c:url value='/hr/view/${emps.employeeId}'/>">${emps.employeeId}</a></td>
				<td>${emps.firstName}${emps.lastName}</td>
				<td>${emps.email}</td>
				<td>${emps.phoneNumber}</td>
				<td>${emps.hireDate}</td>
				<td>${emps.jobId}</td>
				<td>${emps.salary}</td>
				<td>${emps.commissionPct}</td>
				<td>${emps.managerId}</td>
				<td>${emps.departmentId}</td>

			</tr>
		</c:forEach>
	</table>
	<br>
	
	<a href="login"><button>홈으로~</button></a>

</body>
</html>