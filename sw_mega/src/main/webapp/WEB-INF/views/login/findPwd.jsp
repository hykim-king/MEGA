<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>비밀번호 찾기</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <style>
        body { font-family: 'Do Hyeon', sans-serif; text-align: center; margin-top: 100px; margin-bottom: 0; }
        .container { margin-top: 50px; }
        input { width: 300px; height: 50px; margin: 10px 0; font-size: 18px; padding-left: 10px; }
        .btn { background-color: #5a72ea; color: white; padding: 10px 30px; font-size: 20px; border: none; cursor: pointer; margin-top: 15px; border-radius: 5px; }
        .btn-area { margin-top: 30px; }
        .btn-area button { margin: 0 8px; }
    </style>
</head>
<body>
    <div class="container">
        <h2>비밀번호 찾기</h2>
        <form action="/login/findPwd.do" method="post">
            <input type="text" name="userId" placeholder="아이디 입력" required><br>
            <input type="text" name="email" placeholder="이메일 입력" required><br>
            <button type="submit" class="btn">비밀번호 찾기</button>
        </form>
        <div class="btn-area">
            <button onclick="location.href='/login/findId.do'" type="button" class="btn">아이디 찾기</button>
            <button onclick="location.href='/login/loginView.do'" type="button" class="btn">로그인 홈</button>
        </div>
    </div>
    <c:if test="${not empty msg}">
        <script>
            alert("${msg}");
            location.href="/login/findPwd.do";
        </script>
    </c:if>
    <c:if test="${not empty mailSent}">
        <script>
            alert("${mailSent}");
            location.href="/login/loginView.do";
        </script>
    </c:if>
</body>
</html>
