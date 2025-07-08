<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>이메일 인증 결과</title>
</head>
<body>
    <h2>이메일 인증 결과</h2>
    <p>${msg}</p>

    <a href="<c:url value='/membership/doSaveView.do' />">회원가입 화면으로 이동</a>
</body>
</html>
