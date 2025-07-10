<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ehr/resources/assets/css/food_list.css">
<title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

<h2>운동 일지</h2>
<div class="navbar">
  <div class="navbar-left">
    <div class="logo">🏋️‍♂️ 헬메이트</div>
    <ul class="main-menu">
      <li><a href="/ehr/main.do">홈</a></li>
      <li class="has-submenu">
        <a href="#">운동</a>
        <ul class="submenu">
          <li><a href="/ehr/exerciseDiary/doRetrieve.do">운동 일지</a></li>
          <li><a href="/ehr/exercise/doRetrieve.do">운동 조회</a></li>
          <li><a href="/ehr/exercise/doForm.do">운동 추가</a></li>
        </ul>
      </li>
      <li class="has-submenu">
        <a href="#">음식</a>
        <ul class="submenu">
          <li><a href="/ehr/foodDiary/doRetrieve.do">음식 일지</a></li>
          <li><a href="/ehr/food/doRetrieve.do">음식 조회</a></li>
          <li><a href="/ehr/food/doForm.do">음식 추가</a></li>
        </ul>
      </li>
      <li><a href="#">커뮤니티</a></li>
    </ul>
  </div>

  <div class="navbar-right">
    <span>🔔</span>
    <div class="circle"></div>

  <c:choose>
  <c:when test="${not empty sessionScope.userId}">
    <!-- 로그인 상태 -->
    <span>${sessionScope.userId}님</span>
    <a href="/ehr/logout.do">로그아웃</a>
  </c:when>
  <c:otherwise>
    <!-- 비로그인 상태 -->
    <a href="/ehr/login/login.do">로그인</a>
    <a href="/ehr/membership/doSaveView.do">회원가입</a>
  </c:otherwise>
</c:choose>
  </div>
</div>

<!-- 날짜 선택 폼 시작 -->
<form method="get" action="/ehr/exerciseDiary/doRetrieve.do">
    <label for="regDt">날짜 선택: </label>
    <input type="date" id="regDt" name="regDt" value="${regDt}" required />
    <button type="submit">조회</button>
</form>
<!-- 날짜 선택 폼 끝 -->

<!-- 운동 일지 추가 버튼 -->
<div style="margin-top: 10px; text-align: right;">
    <a href="/ehr/exerciseDiary/doForm.do?regDt=${regDt}">➕ 운동 일지 추가</a>
</div>

<c:forEach var="meal" items="${exerciseType}">
    <h3>
        <c:choose>
            <c:when test="${meal == '유산소'}">${meal}</c:when>
            <c:otherwise>${meal}</c:otherwise>
        </c:choose>
    </h3>

    <c:set var="hasData" value="false" />

    <c:forEach var="item" items="${list}">
        <c:if test="${item.exerciseType == meal}">
            <c:set var="hasData" value="true" />
             <div class="exercise-card">
            <p>${item.exerciseName}</p>
            <p>운동 타입: ${item.exerciseType}</p>
            
	        <!-- 유산소 -->
	        <c:if test="${item.exerciseType eq '유산소'}">
	            <p>기준 체중: ${item.weight}kg</p>
	            <p>성별: ${item.gender}</p>
	            <p>운동 시간: ${item.duration} 분</p>
	            <p>총 소모 칼로리: ${item.totalCalories} kcal</p>
	        </c:if>
	
	        <!-- 근력 -->
	        <c:if test="${item.exerciseType eq '근력'}">
	            <p>운동 부위: ${item.region}</p>
	            <p>세트 수: ${item.setCount}</p>
	            <p>세트당 반복 수: ${item.repsPerSet}</p>
	            <p>덤벨 무게: ${item.strenthWeight} kg</p>
	            <p>운동 시간: ${item.duration} 분</p>
	            <p>총 소모 칼로리: ${item.totalCalories} kcal</p>
	        </c:if>
	        
            <!-- 수정 버튼: doSelectOne.do 호출 후 수정 페이지로 이동 -->
            <a href="/ehr/exerciseDiary/selectOneWithJoin.do?edCode=${item.edCode}&eCode=${item.eCode}&regDt=${item.regDt}">수정</a>
            <button onclick="deleteDiary('${item.edCode}', '${item.regDt}')">삭제</button>
           </div>
        </c:if>
    </c:forEach>

    <c:if test="${!hasData}">
        <p>등록된 운동 일지가 없습니다.</p>
    </c:if>
</c:forEach>

<c:if test="${not empty vo}">
 <div class="exercise-card">
    <h3>🍽️ 총 소모 칼로리 및 시간 요약</h3>
    <p>
        ${vo.totalDuration} (분) /
        ${vo.totalCalories} kcal 
    </p>
   </div>
</c:if>


<script>
  function deleteDiary(edCode, regDt) {
    if (!confirm('정말 삭제하시겠습니까?')) return;

    $.ajax({
      url: '/ehr/exerciseDiary/doDelete.do',
      type: 'POST',
      data: { edCode:edCode },
      success: function(response) {
        const res = JSON.parse(response);
        alert(res.message);
        if (res.messageId === 1) {
          window.location.href = '/ehr/exerciseDiary/doRetrieve.do?regDt=' + regDt;
        }
      },
      error: function(xhr, status, error) {
        alert('삭제 중 오류 발생: ' + error);
      }
    });
  }
</script>
</body>
</html>