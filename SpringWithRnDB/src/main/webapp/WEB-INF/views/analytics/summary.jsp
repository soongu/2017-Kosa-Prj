<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CoderBy</title>
</head>
<body>
<h1>열 단위 출력</h1>
<table border="1">
<tr>
<c:forEach var="colName" items="${rSummary.colNames}">
<th>${colName}</th>
</c:forEach>
</tr>
<tr>
<c:forEach var="i" begin="0" end="${fn:length(rSummary.colNames)-1}"> <!-- i는 열 하나하나 -->
<td>
<c:forEach var="j" begin="0" end="${rSummary.nrow-2}">	<!-- j는 summary의 행 하나하나 -->
${rSummary.summary[(rSummary.nrow*i)+j]}<br> <!-- rSummary 모델의 summary, nrow로 몇 행 몇 열을 표현한다. -->
</c:forEach>
</td>
</c:forEach>
</tr>
</table>
<h1>행 단위 출력</h1>
<table border="1">
<c:forEach var="i" begin="0" end="${fn:length(rSummary.colNames)-1}">
<tr>
<th>${rSummary.colNames[i]}</th>
<td>
<c:forEach var="j" begin="0" end="${rSummary.nrow-2}">
${rSummary.summary[(rSummary.nrow*i)+j]}<br>
</c:forEach>
</td>
</tr>
</c:forEach>
</table>


</body>
</html>