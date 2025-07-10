<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 로그인 | Hellmate</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: 'Do Hyeon', sans-serif;
            background: #fff;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 80px;
            padding: 0 32px;
        }
        .header img {
            height: 60px;
        }
        .header-menu {
            font-size: 15px;
            margin-right: 10px;
        }
        .nav-bar {
            width: 100vw;
            background: #FDFF48;
            height: 60px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 32px;
            font-weight: 700;
            letter-spacing: 0.06em;
        }
        .nav-bar span {
            margin: 0 48px;
            color: #222;
            cursor: pointer;
        }
        .nav-bar .active {
            color: #000;
            text-shadow: 2px 2px #fff;
        }
        .login-container {
            width: 100%;
            max-width: 480px;
            margin: 100px auto 0 auto;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .login-title {
            font-size: 48px;
            font-weight: bold;
            margin-bottom: 40px;
            text-shadow: 3px 3px #FDFF48;
        }
        .login-form input[type="text"],
        .login-form input[type="password"] {
            width: 400px;
            height: 60px;
            margin-bottom: 16px;
            font-size: 28px;
            padding: 0 18px;
            border: 3px solid #222;
            background: #fff;
            color: #222;
            outline: none;
            box-sizing: border-box;
            font-family: 'Do Hyeon', sans-serif;
        }
        .login-form input::placeholder {
            color: #ccc;
            font-size: 28px;
            font-family: 'Do Hyeon', sans-serif;
        }
        .login-form button {
            width: 210px;
            height: 60px;
            font-size: 28px;
            font-weight: 700;
            color: #fff;
            background: #6475F8;
            border: none;
            border-radius: 3px;
            margin-top: 24px;
            cursor: pointer;
            transition: background 0.15s;
            font-family: 'Do Hyeon', sans-serif;
        }
        .login-form button:hover {
            background: #4958b8;
        }
        .login-options {
            margin-top: 40px;
            font-size: 32px;
            font-weight: 500;
        }
        .login-options a {
            color: #222;
            text-decoration: none;
            margin: 0 12px;
            transition: color 0.15s;
        }
        .login-options a:hover {
            color: #6475F8;
        }
        @media (max-width: 600px) {
            .login-form input[type="text"],
            .login-form input[type="password"] {
                width: 95vw;
            }
            .login-container {
                max-width: 95vw;
            }
        }
    </style>
</head>
<body>
    <div class="header">
        <a href="/"><img src="/resources/img/hellmate_logo.png" alt="Hellmate Logo"></a>
        <div class="header-menu">
            <a href="/ehr/membership/doSaveView.do">가입하기</a> &nbsp;|&nbsp; 
            <a href="/ehr/login/login.do">로그인</a>
        </div>
    </div>
    <div class="nav-bar">
        <span class="nav-link" onclick="location.href='/ehr/common/main.do'">홈</span>
        <span class="nav-link" onclick="location.href='/ehr/login/login.do'">운동</span>
        <span class="nav-link" onclick="location.href='/ehr/login/login.do'">음식</span>
        <span class="nav-link" onclick="location.href='/ehr/notice/doRetrieve.do'">커뮤니티</span>
    </div>
    <div class="login-container">
        <div class="login-title">회원 로그인</div>
        <form class="login-form" action="/ehr/login/loginView.do" method="post">
            <input type="text" name="userId" placeholder="아이디 입력" required>
            <input type="password" name="password" placeholder="비밀번호 입력" required>
            <button type="submit">로그인</button>
        </form>
        <div class="login-options">
            <a href="/ehr/login/findId.do">아이디 찾기</a>  /  
            <a href="/ehr/login/findPwd.do">비밀번호 찾기</a>
        </div>
    </div>
    <c:if test="${not empty loginResult || not empty msg}">
        <script>
            <c:choose>
                <c:when test="${loginResult eq true}">
                    alert("로그인에 성공하였습니다.");
                    location.href = "/common/main.do";
                </c:when>
                <c:otherwise>
                    alert("${msg}");
                    location.href = "/login/loginView.do";
                </c:otherwise>
            </c:choose>
        </script>
    </c:if>
</body>
</html>