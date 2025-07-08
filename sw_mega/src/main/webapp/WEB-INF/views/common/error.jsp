<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>🚫 오류 발생</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      text-align: center;
      padding: 50px;
    }
    .error-box {
      border: 1px solid #ccc;
      display: inline-block;
      padding: 30px;
      border-radius: 10px;
      background-color: #fdf1f1;
    }
    .error-box h2 {
      color: #c00;
    }
    .error-box button {
      margin-top: 20px;
      padding: 8px 16px;
      background-color: #c00;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
  </style>
</head>
<body>
  <div class="error-box">
    <h2>⚠️ 오류 안내</h2>
    <p>${message}</p>

    <c:if test="${not empty nextUrl}">
      <button onclick="location.href='${nextUrl}'">이동하기</button>
    </c:if>
  </div>
</body>
</html>