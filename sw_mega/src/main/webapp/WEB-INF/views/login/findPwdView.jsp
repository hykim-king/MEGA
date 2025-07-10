<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>비밀번호 찾기 결과</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Do Hyeon', sans-serif;
      text-align: center;
      margin: 0;
      background: #f9fafd;
    }
    .header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 16px 40px;
      background: #fff;
      border-bottom: 1px solid #eee;
    }
    .header img {
      height: 50px;
    }
    .header a {
      text-decoration: none;
      color: #333;
      margin-left: 20px;
      font-size: 18px;
    }
    nav {
      background-color: #fffacb;
      padding: 13px 0;
      font-size: 23px;
      display: flex;
      justify-content: center;
      gap: 50px;
      border-bottom: 1px solid #f2f2f2;
    }
    nav a {
      text-decoration: none;
      color: #333;
      padding: 6px 25px;
      border-radius: 7px;
      transition: background 0.15s;
    }
    nav a:hover {
      background-color: #fffbe6;
    }
    .container {
      margin: 110px auto 0 auto;
      background: #fff;
      border-radius: 16px;
      box-shadow: 0 6px 16px #dde5ff50;
      max-width: 440px;
      padding: 50px 40px 40px 40px;
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
    button {
      display: block;
      margin: 18px auto 0 auto;
      padding: 15px 30px;
      font-size: 20px;
      background-color: #5C6EFF;
      color: white;
      border: none;
      cursor: pointer;
      border-radius: 8px;
      transition: background 0.13s;
      min-width: 200px;
      box-shadow: 0 2px 8px #dde5ff40;
    }
    button:hover {
      background: #4153c8;
    }
    .btn-group {
      display: flex;
      flex-direction: column;
      gap: 12px;
      margin-top: 30px;
    }
  </style>
</head>
<body>
  <div class="header">
    <a href="/ehr/common/main.do"><img src="/resources/img/hellmate_logo.png" alt="Hellmate Logo"></a>
    <div>
      <a href="/ehr/membership/doSaveView.do">가입하기</a>
      <a href="/ehr/login/login.do" style="margin-left: 15px;">로그인</a>
    </div>
  </div>
  <nav>
    <a href="/ehr/common/main.do">홈</a>
    <a href="/ehr/login/login.do">운동</a>
    <a href="/ehr/login/login.do">음식</a>
    <a href="/ehr/notice/doRetrieve.do">커뮤니티</a>
  </nav>
  <div class="container">
    <c:choose>
      <c:when test="${not empty mailSent}">
        <div class="alert">
          ${mailSent}
        </div>
        <div class="btn-group">
          <button onclick="location.href='/ehr/login/login.do'">로그인 하러가기</button>
          <button onclick="location.href='/ehr/login/findPwd.do'">비밀번호 다시 찾기</button>
          <button onclick="location.href='/ehr/login/findId.do'">아이디 찾기</button>
        </div>
      </c:when>
      <c:when test="${not empty msg}">
        <div class="alert error">
          ${msg}
        </div>
        <div class="btn-group">
          <button onclick="location.href='/ehr/login/findPwd.do'">비밀번호 다시 찾기</button>
          <button onclick="location.href='/ehr/login/findId.do'">아이디 찾기</button>
          <button onclick="history.back();">뒤로가기</button>
        </div>
      </c:when>
    </c:choose>
  </div>
</body>
</html>
