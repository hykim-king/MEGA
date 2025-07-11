<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<c:set var="CP" value="${pageContext.request.contextPath }" />    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ehr/resources/assets/css/header.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/mypage_search.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/pcwk_main.css">
<title>아이디 찾기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="/ehr/resources/assets/js/common.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<style>
    body {
      font-family: 'Do Hyeon', sans-serif;
      text-align: center;
      margin: 0;
      background: #fff;
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
      border: 2px solid #222;
      border-radius: 5px;
      font-family: 'Do Hyeon', sans-serif;
      background: #fff;
      color: #222;
      box-sizing: border-box;
    }
    input::placeholder {
      color: #ccc;
      font-size: 18px;
    }
    button[type="submit"] {
      margin-top: 15px;
      padding: 15px 30px;
      font-size: 20px;
      background-color: #5C6EFF; /* 메인버튼: 진한 파랑 */
      color: white;
      border: none;
      cursor: pointer;
      border-radius: 5px;
      transition: background 0.15s;
    }
    button[type="submit"]:hover {
      background-color: #4958b8;
    }
    .btn-area {
      margin-top: 30px;
    }
    .btn-area button {
      margin: 0 8px;
      padding: 12px 22px;
      font-size: 18px;
      background-color: #F5F7FF; /* 연한 파랑 */
      color: #222;
      border: 1px solid #aaa;
      border-radius: 5px;
      transition: background 0.15s, color 0.15s, border 0.15s;
    }
    .btn-area button:hover {
      background-color: #ecefff;
      color: #4958b8;
      border-color: #4958b8;
    }
    .find-title {
        font-size: 2.3rem;
        font-weight: bold;
        margin-bottom: 35px;
        text-shadow: 2px 2px #FDFF48;
    }
    @media (max-width: 600px) {
      input[type="email"] {
        width: 92vw;
      }
      .container {
        margin-top: 35px;
      }
      .find-title { font-size: 1.5rem; }
    }
</style>
</head>
<body>
<div id="container">

  <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

  <main id="main">
    <div class="main-container">
      <div class="container">
        <div class="find-title">아이디 찾기</div>
        <form action="/ehr/login/findIdView.do" method="post">
          <input type="email" name="email" placeholder="이메일 입력" required><br>
          <button type="submit">아이디 찾기</button>
        </form>
        <div class="btn-area">
          <button type="button" onclick="location.href='/ehr/login/login.do'">로그인 홈</button>
          <button type="button" onclick="location.href='/ehr/login/findPwd.do'">비밀번호 찾기</button>
        </div>
      </div>
    </div>
  </main>

  <!-- 이메일 없을 때 경고창 & 페이지 이동 -->
  <c:if test="${not empty msg}">
    <script>
      alert("${msg}");
      location.href="/ehr/login/findId.do";
    </script>
  </c:if>

  <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
</div>
</body>
</html>
