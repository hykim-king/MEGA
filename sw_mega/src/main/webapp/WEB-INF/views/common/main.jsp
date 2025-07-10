<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>HELLMATE | 메인 페이지</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Do Hyeon Font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Do Hyeon', sans-serif;
        }

        body {
            background-color: #fff;
            color: #000;
        }

        nav {
            background-color: yellow;
            padding: 12px 0;
            font-size: 24px;
            display: flex;
            justify-content: center;
            gap: 30px;
        }
        .nav-link {
            cursor: pointer;
            text-decoration: none;
            color: black;
            padding: 5px 15px;
            border-radius: 5px;
            transition: background 0.1s;
        }
        .nav-link:hover {
            background-color: #fffacb;
        }

        header {
            background-color: #f8f8f8;
            padding: 15px 20px;
            text-align: right;
        }

        header a {
            margin-left: 15px;
            text-decoration: none;
            color: #333;
        }

        .section {
            text-align: center;
            padding: 40px 20px;
        }

        .section img {
            width: 100%;
            max-width: 500px;
            height: auto;
            margin: 20px 0;
        }

        .section .title {
            font-size: 1.8rem;
            margin-bottom: 20px;
        }

        .section .btn {
            padding: 10px 20px;
            background-color: #000;
            color: #fff;
            border: none;
            font-size: 1.2rem;
            cursor: pointer;
            border-radius: 5px;
        }

        .highlight {
            color: #0066ff;
        }

        .quote {
            font-size: 1.2rem;
            margin-top: 20px;
        }
        
        .footer {
            background-color: #f1f1f1;
            color: #333;
            text-align: center;
            padding: 20px;
            font-size: 0.9rem;
            border-top: 1px solid #ccc;
            margin-top: 40px;
        }

        @media (max-width: 768px) {
            nav { font-size: 18px; gap: 15px; }
            .section .title { font-size: 1.4rem; }
            .section .btn { font-size: 1rem; padding: 8px 16px; }
            .quote { font-size: 1rem; }
            .footer { font-size: 0.8rem; padding: 15px; }
        }
    </style>
</head>
<body>

<nav>
    <span class="nav-link" onclick="location.href='/ehr/common/main.do'">홈</span>
    <span class="nav-link" onclick="location.href='/ehr/login/login.do'">운동</span>
    <span class="nav-link" onclick="location.href='/ehr/login/login.do'">음식</span>
    <span class="nav-link" onclick="location.href='/ehr/notice/doRetrieve.do'">커뮤니티</span>
</nav>

<header>
     <c:choose>
    <c:when test="${not empty sessionScope.userId}">
      <span>${sessionScope.userId}님 환영합니다!</span>
      <a href="/ehr/login/logout.do">로그아웃</a>
    </c:when>
    <c:otherwise>
      <a href="/ehr/login/login.do">로그인</a>
      <a href="/ehr/membership/doSaveView.do">회원가입</a>
    </c:otherwise>
  </c:choose>
</header>

<div class="section">
    <div class="title">
        당신의 건강한 삶<br>
        지금부터 <span class="highlight">HELLMATE와 함께</span> 하세요
    </div>
    <button class="btn" onclick="location.href='/ehr/login/login.do'">시작하기</button>
    <img src="${pageContext.request.contextPath}/resources/images/main_1.jpg" alt="운동 시작">
</div>

<div class="section">
    <div class="title">식단 관리</div>
    <img src="${pageContext.request.contextPath}/resources/images/meal.jpg" alt="식단">
</div>

<div class="section">
    <div class="title">운동 일지</div>
    <img src="${pageContext.request.contextPath}/resources/images/exercise.jpg" alt="운동 일지">
</div>

<div class="section">
    <div class="title">헬메이트는 언제나 당신과 함께</div>
    <img src="${pageContext.request.contextPath}/resources/images/support.jpg" alt="헬스케어 지원">
</div>

<div class="section">
    <div class="quote">
        365일 멈추지 않는 헬메이트는<br>
        오직 당신의 건강한 매일을 응원합니다.
    </div>
    <img src="${pageContext.request.contextPath}/resources/images/running.jpg" alt="러닝 이미지">
</div>

<footer class="footer">
    <p>이 프로젝트는 소규모 프로젝트로 향후 업데이트 가능성이 열려있습니다.</p>
</footer>
</body>
</html>
