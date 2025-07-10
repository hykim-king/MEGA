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
   <div id="container">
   
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    
      <!--main-->
      <main id="main">
      <div class="main-container">


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
      </div>
      </main>
      <!--//main end-------------------->

      
 <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
   </div> 
</body>
</html>

