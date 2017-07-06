<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>


button{
	font-size: 20px;
	background-color: pink;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/include/staticHeader.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
회원 ID : ${emps.loginId}<br>
사원번호 : ${emps.employeeId}<br>
사원이름 : ${emps.firstName} ${emps.lastName}<br> 
이메일 : ${emps.email}<br>
전화번호 : ${emps.phoneNumber}<br>
입사일 : ${emps.hireDate}<br>
직무 : ${emps.jobId}<br>
급여 : ${emps.salary}<br>
커미션 : ${emps.commissionPct}<br>
매니저ID : ${emps.managerId}<br>
부서ID : ${emps.departmentId}<br>

<a href="../list"><button>뒤로</button></a>
<a href="../welcome"><button>홈으로~</button></a>

<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
</body>
</html>