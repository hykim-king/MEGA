<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>비밀번호 찾기 결과</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="/ehr/resources/assets/css/header.css">
  <link rel="stylesheet" href="/ehr/resources/assets/css/pcwk_main.css">
  <style>
    body {
      font-family: 'Do Hyeon', sans-serif;
      text-align: center;
      margin: 0;
      background: #f9fafd;
    }
    .container {
      margin-top: 110px;
      background: #fff;
      border-radius: 16px;
      box-shadow: 0 6px 16px #dde5ff50;
      max-width: 420px;
      padding: 50px 40px 40px 40px;
      margin-left: auto;
      margin-right: auto;
    }
    .alert {
      margin-bottom: 30px;
      padding: 18px 0;
      border-radius: 10px;
      background: #eef2ff;
      color: #4153c8;
      font-size: 21px;
      font-weight: bold;
    }
    .error {
      background: #ffebee;
      color: #c84444;
    }
    .btn-group {
      display: flex;
      flex-direction: column;
      gap: 12px;
      margin-top: 30px;
      align-items: center;
    }
    .btn-group button {
      width: 90%;
      max-width: 300px;
      min-width: 180px;
      margin: 0 auto;
      padding: 15px 0;
      font-size: 20px;
      background-color: #F5F7FF;
      color: #222;
      border: 1px solid #aaa;
      border-radius: 5px;
      transition: background 0.13s, color 0.13s, border 0.13s;
      cursor: pointer;
    }
    .btn-group button:hover {
      background: #ecefff;
      color: #4958b8;
      border-color: #4958b8;
    }
    /* 메인(성공) 버튼만 진한 파랑 */
    .btn-group .main-btn {
      background-color: #5C6EFF;
      color: #fff;
      border: none;
      transition: background 0.15s;
    }
    .btn-group .main-btn:hover {
      background-color: #4958b8;
      color: #fff;
    }
    @media (max-width: 600px) {
      .container {
        max-width: 99vw;
        padding: 24px 3vw 24px 3vw;
        margin-top: 30px;
      }
      .btn-group button { font-size: 17px; padding: 12px 0; }
      .alert { font-size: 17px; padding: 13px 0;}
    }
  </style>
</head>
<body>
<div id="container">

  <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

  <main id="main">
    <div class="main-container">
      <div class="container">
        <c:choose>
          <c:when test="${not empty mailSent}">
            <div class="alert">
              ${mailSent}
            </div>
            <div class="btn-group">
              <button class="main-btn" onclick="location.href='/ehr/login/login.do'">로그인 하러가기</button>
              <button type="button" onclick="location.href='/ehr/login/findPwd.do'">비밀번호 다시 찾기</button>
              <button type="button" onclick="location.href='/ehr/login/findId.do'">아이디 찾기</button>
            </div>
          </c:when>
          <c:when test="${not empty msg}">
            <div class="alert error">
              ${msg}
            </div>
            <div class="btn-group">
              <button type="button" onclick="location.href='/ehr/login/findPwd.do'">비밀번호 다시 찾기</button>
              <button type="button" onclick="location.href='/ehr/login/findId.do'">아이디 찾기</button>
              <button type="button" onclick="history.back();">뒤로가기</button>
            </div>
          </c:when>
        </c:choose>
      </div>
    </div>
  </main>

</div>
</body>
</html>
