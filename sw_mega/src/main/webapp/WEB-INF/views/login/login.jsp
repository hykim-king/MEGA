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
<title>회원 로그인 | Hellmate</title>
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
    input[type="text"], input[type="password"] {
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
      font-family: 'Do Hyeon', sans-serif;
    }
    button, .btn-area button {
      margin-top: 15px;
      padding: 15px 30px;
      font-size: 20px;
      background-color: #5C6EFF;
      color: white;
      border: none;
      cursor: pointer;
      border-radius: 5px;
      transition: background 0.15s;
    }
    button:hover, .btn-area button:hover {
      background-color: #4958b8;
    }
    .btn-area {
      margin-top: 30px;
    }
    .btn-area button {
      margin: 0 8px;
      padding: 12px 22px;
      font-size: 18px;
      background-color: #F5F7FF;
      color: #222;
      border: 1px solid #aaa;
    }
    .btn-area button:hover {
      background-color: #ecefff;
      color: #4958b8;
      border-color: #4958b8;
    }
    .login-title {
        font-size: 2.3rem;
        font-weight: bold;
        margin-bottom: 35px;
        text-shadow: 2px 2px #FDFF48;
    }
    @media (max-width: 600px) {
      input[type="text"], input[type="password"] {
        width: 92vw;
      }
      .container {
        margin-top: 35px;
      }
      .login-title { font-size: 1.5rem; }
    }
</style>
</head>
<body>
  <div id="container">

    <%-- header.jsp 전체 포함 --%>
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>

    <!-- main -->
    <main id="main">
      <div class="main-container">
        <div class="container">
          <div class="login-title">회원 로그인</div>
          <form action="/ehr/login/loginView.do" method="post">
            <input type="text" name="userId" placeholder="아이디 입력" required><br>
            <input type="password" name="password" placeholder="비밀번호 입력" required><br>
            <button type="submit">로그인</button>
          </form>
          <div class="btn-area">
            <button type="button" onclick="location.href='/ehr/login/findId.do'">아이디 찾기</button>
            <button type="button" onclick="location.href='/ehr/login/findPwd.do'">비밀번호 찾기</button>
          </div>
        </div>
      </div>
    </main>
    <!-- //main end -->

    <c:if test="${not empty loginResult || not empty msg}">
      <script>
        <c:choose>
          <c:when test="${loginResult eq true}">
            alert("로그인에 성공하였습니다.");
            location.href = "/ehr/common/main.do";
          </c:when>
          <c:otherwise>
            alert("${msg}");
            location.href = "/ehr/login/loginView.do";
          </c:otherwise>
        </c:choose>
      </script>
    </c:if>

    <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
  </div>
</body>
</html>
