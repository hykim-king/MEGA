<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/ehr/resources/assets/css/food_list.css">
    <title>🍽️ 음식 일지</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<h2>🍽️ 음식 일지</h2>
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

<!-- 날짜 선택 폼 시작 -->
<form method="get" action="/ehr/foodDiary/doRetrieve.do">
    <input type="hidden" name="userId" value="${userId}" />
    <label for="regDt">날짜 선택: </label>
    <input type="date" id="regDt" name="regDt" value="${regDt}" required />
    <button type="submit">조회</button>
</form>
<!-- 날짜 선택 폼 끝 -->

<!-- 음식 추가 버튼 -->
<div style="margin-top: 10px; text-align: right;">
    <a href="/ehr/foodDiary/doForm.do?userId=${param.userId}&regDt=${param.regDt}">➕ 음식 일지 추가</a>
</div>
<c:forEach var="meal" items="${mealList}">
    <h3>
        <c:choose>
            <c:when test="${meal == '아침'}">${meal}</c:when>
            <c:when test="${meal == '점심'}">${meal}</c:when>
            <c:otherwise>${meal}</c:otherwise>
        </c:choose>
    </h3>

    <c:set var="hasData" value="false" />

    <c:forEach var="item" items="${list}">
        <c:if test="${item.mealType == meal}">
            <c:set var="hasData" value="true" />
            <p>${item.foodName} / ${item.grams}g</p>
            <p>
                ${item.totalCal} kcal /
                ${item.totalCarb} g /
                ${item.totalFat} g /
                ${item.totalProt} g /
                ${item.totalNa} mg
            </p>
            <!-- 수정 버튼: doSelectOne.do 호출 후 수정 페이지로 이동 -->
            <a href="/ehr/foodDiary/doSelectOne.do?fdCode=${item.fdCode}&userId=${item.userId}&regDt=${item.regDt}">수정</a>
            <button onclick="deleteDiary('${item.fdCode}', '${item.userId}', '${item.regDt}')">삭제</button>
            
        </c:if>
    </c:forEach>

    <c:if test="${!hasData}">
        <p>등록된 음식 일지가 없습니다.</p>
    </c:if>
</c:forEach>

<c:if test="${not empty vo}">
    <h3>🍽️ 총 섭취 요약</h3>
    <p>
        ${vo.totalCal} kcal /
        ${vo.totalCarb} g /
        ${vo.totalFat} g /
        ${vo.totalProt} g /
        ${vo.totalNa} mg
    </p>
</c:if>

<script>
  function deleteDiary(fdCode, userId, regDt) {
    if (!confirm('정말 삭제하시겠습니까?')) return;

    $.ajax({
      url: '/ehr/foodDiary/doDelete.do',
      type: 'POST',
      data: { fdCode: fdCode },
      success: function(response) {
        const res = JSON.parse(response);
        alert(res.message);
        if (res.messageId === 1) {
          window.location.href = '/ehr/foodDiary/doRetrieve.do?userId=' + userId + '&regDt=' + regDt;
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