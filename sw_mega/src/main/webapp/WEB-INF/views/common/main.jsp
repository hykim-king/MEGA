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
    <link rel="stylesheet" href="/ehr/resources/assets/css/header.css">
    <link rel="stylesheet" href="/ehr/resources/assets/css/pcwk_main.css">
    <style>
        body {
            background: #fff;
            color: #222;
            font-family: 'Do Hyeon', sans-serif;
            margin: 0;
        }
        .main-section {
            width: 100%;
            max-width: 850px;
            margin: 48px auto 0 auto;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .main-title {
            font-size: 2.3rem;
            font-weight: 700;
            margin-bottom: 18px;
            text-align: center;
            line-height: 1.35;
            letter-spacing: 0.05em;
        }
        .main-title .highlight {
            color: #6475F8;
            background: #fdff48b0;
            padding: 0 4px;
            border-radius: 7px;
        }
        .main-btn {
            margin-top: 16px;
            margin-bottom: 30px;
            padding: 17px 48px;
            background: #6475F8;
            color: #fff;
            font-size: 1.45rem;
            font-weight: 600;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            box-shadow: 0 2px 8px #00000011;
            transition: background 0.17s;
        }
        .main-btn:hover {
            background: #4958b8;
        }
        .main-img {
            width: 100%;
            max-width: 500px;
            border-radius: 15px;
            margin: 16px 0 40px 0;
            box-shadow: 0 6px 32px #0001;
        }
        .feature-block {
            width: 100%;
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 32px;
            margin-bottom: 36px;
        }
        .feature {
            flex: 1 1 220px;
            max-width: 270px;
            min-width: 170px;
            background: #fff;
            border-radius: 13px;
            box-shadow: 0 4px 24px #00000013;
            padding: 32px 20px;
            text-align: center;
        }
        .feature-title {
            font-size: 1.4rem;
            margin-bottom: 18px;
            font-weight: 600;
            color: #222;
        }
        .feature-img {
            width: 90%;
            max-width: 180px;
            border-radius: 8px;
            margin-bottom: 6px;
        }
        .section-quote {
            margin: 45px 0 0 0;
            text-align: center;
        }
        .section-quote .quote-text {
            font-size: 1.17rem;
            color: #4958b8;
            font-weight: 500;
            margin-bottom: 18px;
            line-height: 1.65;
        }
        .quote-img {
            width: 100%;
            max-width: 370px;
            border-radius: 13px;
            box-shadow: 0 2px 18px #00000018;
        }
        @media (max-width: 900px) {
            .main-section { max-width: 99vw; padding: 0 2vw;}
            .feature-block { gap: 18px; }
            .feature { max-width: 90vw; }
        }
        @media (max-width: 600px) {
            .main-title { font-size: 1.25rem; }
            .main-btn { font-size: 1.01rem; padding: 9px 17px;}
            .main-section { padding: 0 3vw;}
            .feature { padding: 16px 7px; font-size: 0.9rem;}
            .feature-title { font-size: 1.08rem;}
        }
    </style>
</head>
<body>
<div id="container">

    <%-- 헤더는 별도 파일에서 인클루드 --%>
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

    <!-- main section -->
    <main id="main">
        <div class="main-section">
            <!-- 메인 슬로건 -->
            <div class="main-title">
                당신의 건강한 삶<br>
                지금부터 <span class="highlight">HELLMATE</span>와 함께 하세요
            </div>
            <button class="main-btn" onclick="location.href='/ehr/login/login.do'">시작하기</button>
            <img class="main-img" src="${pageContext.request.contextPath}/resources/images/운동3.jpg" alt="운동 시작">

            <!-- 주요 서비스 안내 -->
            <div class="feature-block">
                <div class="feature">
                    <div class="feature-title">식단 관리</div>
                    <img class="feature-img" src="${pageContext.request.contextPath}/resources/images/음식1.jpg" alt="식단">
                </div>
                <div class="feature">
                    <div class="feature-title">운동 일지</div>
                    <img class="feature-img" src="${pageContext.request.contextPath}/resources/images/운동1.jpg" alt="운동 일지">
                </div>
                <div class="feature">
                    <div class="feature-title">헬스케어 지원</div>
                    <img class="feature-img" src="${pageContext.request.contextPath}/resources/images/헬스케어1.jpg" alt="헬스케어 지원">
                </div>
            </div>

            <!-- 응원 메시지/러닝 이미지 -->
            <div class="section-quote">
                <div class="quote-text">
                    365일 멈추지 않는 헬메이트는<br>
                    오직 당신의 건강한 매일을 응원합니다.
                </div>
                <img class="quote-img" src="${pageContext.request.contextPath}/resources/images/운동2.jpg" alt="러닝 이미지">
            </div>
        </div>
    </main>

    <%-- 푸터는 별도 파일에서 인클루드 --%>
    <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
</div>
</body>
</html>
