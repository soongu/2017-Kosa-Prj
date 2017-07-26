<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CoderBy</title>
<!-- Favicon -->
<link href="<c:url value='/favicon.png'/>" rel="icon" type="image/png">
<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".delete").click(function() {
		if(confirm("이 작업은 되돌릴 수 없습니다. 파일을 삭제하겠습니까?")) {
			return true;
		}else {
			return false;
		}
	})
});
</script>
<body>
<c:url var="actionURL" value='/upload/updateDir'/>
<form action="${actionURL}" method="post" enctype="multipart/form-data" class="form-horizontal">
<table border="1">
<tr>
	<th>ID</th>
	<td>경로</td>
	<td>이미지</td>
	<td>크기</td>
	<td>유형</td>
	<td>날짜</td>
	<td>삭제</td>
</tr>
<c:forEach var="file" items="${fileList}">
<tr>
	<td><input type="checkbox" name="fileIds" value="${file.fileId}">${file.fileId}</td>
	<td>${file.directoryName}</td>
	<td>
		<c:set var="len" value="${fn:length(file.fileName)}"/>
		<c:set var="filetype" value="${fn:toUpperCase(fn:substring(file.fileName, len-4, len))}"/>
		<c:if test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.PNG') or (filetype eq '.GIF')}">
		<img src='<c:url value="/img/${file.fileId}"/>' width="100" class="img-thumbnail">
		</c:if>
	</td>
	<td>
		<fmt:formatNumber value="${file.fileSize/1024}" pattern="#,###"/>KB
	</td>
	<td>${file.fileContentType}</td>
	<td>${file.fileUploadDate}</td>
	<td>
		<a href='<c:url value="/upload/delete/${file.fileId}"/>' class="delete">삭제</a>
	</td>
</tr>
</c:forEach>
</table>
선택한 파일을 <select name="directoryName">
	<option value="/">/
	<option value="/images">/이미지
	<option value="/data">/자료실
	<option value="/bigdata">/빅데이터
	<option value="/common">/공통
</select>로 <input type="submit" value="이동"><p>
<a href='<c:url value="/upload/new"/>'>업로드</a>
</form>
</body>
</html>