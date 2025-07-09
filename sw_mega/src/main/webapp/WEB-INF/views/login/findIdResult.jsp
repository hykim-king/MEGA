<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>아이디 결과</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
  <style>
    body { font-family: 'Do Hyeon', sans-serif; text-align: center; margin: 0; }
    nav { background-color: yellow; padding: 10px 0; font-size: 24px; }
    nav a { margin: 0 30px; text-decoration: none; color: black; }
    .header { display: flex; justify-content: space-between; padding: 10px 40px; }
    .container { margin-top: 150px; }
    button {
      display: block; margin: 15px auto; padding: 15px 30px; font-size: 20px;
      background-color: #5C6EFF; color: white; border: none; cursor: pointer;
      border-radius: 5px;
    }
  </style>
</head>
<body>
  <div class="header">
    <a href="/"><img src="/resources/img/hellmate_logo.png" alt="Hellmate Logo" height="50"></a>
    <div>
      <a href="/signup.jsp">가입하기</a> |
      <a href="/login.jsp">로그인</a>
    </div>
  </div>
  <nav>
    <a href="#">홈</a>
    <a href="#">운동</a>
    <a href="#">음식</a>
    <a href="#">커뮤니티</a>
  </nav>
  <div class="container">
    <c:choose>
      <c:when test="${not empty maskedId}">
        <h2>귀하의 아이디는 <strong>${maskedId}</strong> 입니다.</h2>
      </c:when>
      <c:otherwise>
        <h2>일치하는 이메일이 없습니다.</h2>
      </c:otherwise>
    </c:choose>
    <button onclick="location.href='findPwd.do'">비밀번호 찾기</button>
    <button onclick="location.href='login.do'">로그인 홈으로</button>
    <button onclick="history.back();">뒤로가기</button>
  </div>
</body>
</html>
