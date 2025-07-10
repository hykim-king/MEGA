<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>아이디 찾기</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Do Hyeon', sans-serif;
      text-align: center;
      margin: 0;
    }
    nav {
      background-color: yellow;
      padding: 10px 0;
      font-size: 24px;
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
    .header {
      display: flex;
      justify-content: space-between;
      padding: 10px 40px;
    }
    .container {
      margin-top: 100px;
    }
    input[type="email"] {
      width: 300px;
      height: 40px;
      font-size: 18px;
      padding: 5px 10px;
      margin-bottom: 15px;
    }
    button {
      margin-top: 15px;
      padding: 15px 30px;
      font-size: 20px;
      background-color: #5C6EFF;
      color: white;
      border: none;
      cursor: pointer;
      border-radius: 5px;
    }
    .btn-area {
      margin-top: 30px;
    }
    .btn-area button {
      margin: 0 8px;
    }
  </style>
</head>
<body>
  <div class="header">
    <a href="/"><img src="/resources/img/hellmate_logo.png" alt="Hellmate Logo" height="50"></a>
    <div>
      <a href="/ehr/membership/doSaveView.do">가입하기</a> |
      <a href="/ehr/login/login.do">로그인</a>
    </div>
  </div>

  <nav>
    <span class="nav-link" onclick="location.href='/ehr/common/main.do'">홈</span>
    <span class="nav-link" onclick="location.href='/ehr/login/login.do'">운동</span>
    <span class="nav-link" onclick="location.href='/ehr/login/login.do'">음식</span>
    <span class="nav-link" onclick="location.href='/ehr/notice/doRetrieve.do'">커뮤니티</span>
  </nav>

  <div class="container">
    <h1>아이디 찾기</h1>
    <form action="/ehr/login/findIdView.do" method="post">
      <input type="email" name="email" placeholder="이메일 입력" required><br>
      <button type="submit">아이디 찾기</button>
    </form>
    <div class="btn-area">
      <button onclick="location.href='/ehr/login/login.do'" type="button">로그인 홈</button>
      <button onclick="location.href='/ehr/login/findPwd.do'" type="button">비밀번호 찾기</button>
    </div>
  </div>
</body>
</html>
