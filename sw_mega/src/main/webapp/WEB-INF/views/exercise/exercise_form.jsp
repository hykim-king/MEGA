<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="/ehr/resources/assets/css/food_list.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="navbar">
  <div class="navbar-left">
    <div class="logo">🏋️‍♂️ 헬메이트</div>
    <ul class="main-menu">
      <li><a href="#">홈</a></li>
      <li class="has-submenu">
        <a href="#">운동</a>
        <ul class="submenu">
          <li><a href="/ehr/exerciseDiary/doRetrieve.do?userId=user01">운동 일지</a></li>
          <li><a href="/ehr/exercise/doRetrieve.do">운동 조회</a></li>
          <li><a href="/ehr/exercise/doForm.do?userId=user01">운동 추가</a></li>
        </ul>
      </li>
      <li class="has-submenu">
        <a href="#">음식</a>
        <ul class="submenu">
          <li><a href="/ehr/foodDiary/doRetrieve.do?userId=user01">음식 일지</a></li>
          <li><a href="/ehr/food/doRetrieve.do?userId=user01">음식 조회</a></li>
          <li><a href="/ehr/food/doForm.do?userId=user01">음식 추가</a></li>
        </ul>
      </li>
      <li><a href="#">커뮤니티</a></li>
    </ul>
  </div>

  <div class="navbar-right">
    <span>🔔</span>
    <div class="circle"></div>
    <span>로그인</span>
  </div>
</div>

<h2>음식 추가</h2>
<br>
<h4>세부 내용:</h4>
  <form id="exerciseForm">
    <input type="hidden" name="userId" value="${param.userId}" />
    
    <label for="exerciseName">운동명:</label>
    <input type="text" id="exerciseName" name="exerciseName" required >
    <br/>
    <label for="exerciseType">운동 타입:</label>
    <input type="text" id="exerciseType" name="exerciseType" required placeholder="유산소 or 근력">
    <br/>
    <label for="region">운동부위:</label>
    <input type="text" id="region" name="region"  placeholder="ex) 하체 (근력 운동만 입력)">
    <br/>
    <label for="gender">성별:</label>
    <input type="text" id="gender" name="gender"  placeholder="남성 or 여성 (유산소 운동만 입력)">
    <br/>
    <label for="weight">기준체중:</label>
    <input type="number" id="weight" name="weight" placeholder="kg (유산소 운동만 입력)" >
    <br/>
    <label for="calBurn">소모칼로리:</label>
    <input type="number" id="calBurn" name="calBurn" required >
    <br/>
    <button type="button" id="saveBtn">등록</button>
  </form>
  
  <script>
  $('#saveBtn').click(function() {
      const formData = $('#exerciseForm').serialize();

      $.post('/ehr/exercise/doSave.do', formData, function(response) {
          const res = JSON.parse(response);
          alert(res.message);
          if (res.messageId === 1) {
            location.href = "/ehr/exercise/doRetrieve.do?userId=" + $('[name=userId]').val();
          }
        });
  });
    </script>
</body>
</html>