<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">


</script>
<style>
.error{
	color: red;
	font-size: 15px;
}

body {
	text-align: center;
	background-image: url("../images/3.jpg");
	background-repeat: no-repeat;
}

table {
	font-size: 30px;
	height: 20px;
	text-align: left;
	margin: auto;
}

button {
	font-size: 20px;
	background-color: pink;
}

input {
	font-size: 20px;
	background-color: pink;
}

option {
	font-size: 20px;
	background-color: pink;
}

.abc {
	text-align: left;
	color: #443FD6;
	font-weight: bold;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>여름나라의 러블리즈</title>
</head>
<body>
	<p></p>
	<br>
	<br>
	<br>
	<br>
	<h1 style="color: #D918EF;">~~~~~~~lovelinus 회원 가입~~~~~~~</h1>
	<br>
	<br>
	<c:url value="/hr/insert" var="actionURL" scope="page"/>
	<form:form name="userInfo" onsubmit="return checkValue()" action="${actionURL}" modelAttribute="emp">
		<table border=1>
			<tr>
				<td class="abc">회원 ID :</td>
				<td><form:input name="id" path="loginId" required="required" onkeydown="inputIdChk()"/>
					<form:errors path="loginId" cssClass="error"/>
						<input type="button" value="ID 중복확인" onclick="openIdChk()">
						<input type="hidden" name="idDuplication" value="idUncheck"></td>
			</tr>
			<tr>
				<td class="abc">비밀번호 :</td>
				<td><form:input type="password" name="password" path="loginPassword" required="required"/>
					<form:errors path="loginPassword" cssClass="error"/></td>
			</tr>
			<tr>
				<td class="abc">비밀번호 확인 :</td>
				<td><input type="password" name="passwordcheck" required="required"/></td>
			</tr>
			<tr>
				<td class="abc">사원번호:</td>
				<td><form:input path="employeeId" required="required"/>
					<form:errors path="employeeId" cssClass="error"/></td>
			</tr>
			<tr>
				<td class="abc">성 :</td>
				<td><form:input path="firstName" required="required"/>
					<form:errors path="firstName" cssClass="error"/></td>
			</tr>
			<tr>
				<td class="abc">이름 :</td>
				<td><form:input path="lastName" required="required"/>
					<form:errors path="lastName" cssClass="error"/></td>
			</tr>
			<tr>
				<td class="abc">이메일 :</td>
				<td><form:input path="email" required="required"/>
					<form:errors path="email" cssClass="error"/></td>
			</tr>
			<tr>
				<td class="abc">전화번호 :</td>
				<td><form:input path="phoneNumber" required="required"/>
					<form:errors path="phoneNumber" cssClass="error"/></td>
			</tr>



			<tr>
				<td class="abc">직무 :</td>
				<td class="abc"><select name="jobId">
						<c:forEach var="job" items="${JobId}">
							<option value="${job.jobId}">${job.title}</option>
						</c:forEach>
				</select></td>
			</tr>

			<tr>
				<td class="abc">급여 :</td>
				<td><form:input path="salary" type="number" required="required"/>
					<form:errors path="salary" cssClass="error"/></td>
			</tr>
			<tr>
				<td class="abc">보너스율 :</td>
				<td><form:input path="commissionPct" type="number" step="0.1" min="0" max="0.99"/>
					<form:errors path="commissionPct" cssClass="error"/></td>
			</tr>
			<tr>
				<td class="abc">매니저ID :</td>
				<td><form:input path="managerId" />
					<form:errors path="managerId" cssClass="error"/></td>
			</tr>

			<tr>
				<td class="abc">부서 :</td>
				<td class="abc"><select name="departmentId">
						<c:forEach var="map" items="${DeptId}">
							<c:forEach var="dept" items="${map}">
								<option value="${dept.key}">${dept.value}</option>
							</c:forEach>
						</c:forEach>
				</select></td>
			</tr>

		</table>
		<input type="submit" value=" 가 입 "
			onclick="return confirm('회원 가입을 완료하시겠습니까?')"> <a href="../"><input
			type="button" value=" 취 소 "></a> <input type="reset" value="초기화">
	</form:form>

</body>
</html>