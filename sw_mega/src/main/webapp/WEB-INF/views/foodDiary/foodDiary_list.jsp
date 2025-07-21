<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<c:set var="CP" value="${pageContext.request.contextPath }" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/ehr/resources/assets/css/header.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/mypage_search.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/pcwk_main.css">
<link rel="stylesheet" href="/ehr/resources/assets/css/diary_list.css">
<title>헬메이트</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="/ehr/resources/assets/js/common.js"></script>
</head>
<body>
   <div id="container">
   
    <jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
    
      <!--main-->
      <main id="main">
      <div class="main-container">
      
      <h1>음식 일지</h1>

<!-- 날짜 선택 폼 시작 -->
<form method="get" action="/ehr/foodDiary/doRetrieve.do">
    <label for="regDt">날짜 선택: </label>
    <input type="date" id="regDt" name="regDt" value="${regDt}" required />
    <button type="submit">조회</button>
</form>
<!-- 날짜 선택 폼 끝 -->

<!-- 음식 추가 버튼 -->
<div style="margin-top: 10px; text-align: right;">
    <a href="/ehr/foodDiary/doForm.do?regDt=${param.regDt}">➕ 음식 일지 추가</a>
</div>
<c:forEach var="meal" items="${mealList}">
    <section>
    <h3>
        <c:choose>
            <c:when test="${meal == '아침'}">${meal}</c:when>
            <c:when test="${meal == '점심'}">${meal}</c:when>
            <c:otherwise>${meal}</c:otherwise>
        </c:choose>
    </h3>
    </section>
    <c:set var="hasData" value="false" />


    <c:forEach var="item" items="${list}">
        <c:if test="${item.mealType == meal}">
            <c:set var="hasData" value="true" />
            <div class="food-item">
            <h4>${item.foodName} / ${item.grams}g</h4>
            <p>칼로리: ${item.totalCal} kcal</p>
            <p>탄수화물: ${item.totalCarb} g</p>
            <p>지방: ${item.totalFat} g</p>
            <p>단백질: ${item.totalProt} g</p>
            <p>나트륨: ${item.totalNa} mg</p>
            </p>
            <div class="actions">
            <!-- 수정 버튼: doSelectOne.do 호출 후 수정 페이지로 이동 -->
            <a href="/ehr/foodDiary/doSelectOne.do?fdCode=${item.fdCode}&userId=${item.userId}&regDt=${item.regDt}">수정</a>
            <button onclick="deleteDiary('${item.fdCode}', '${item.regDt}')">삭제</button>
            </div>
            </div>
        </c:if>
    </c:forEach>

    <c:if test="${!hasData}">
        <p>등록된 음식 일지가 없습니다.</p>
    </c:if>
</c:forEach>
<section>
</section>
<c:if test="${not empty vo}">
<div class="summary-box">
    <h3>🍽️ 총 섭취 요약</h3>
    <p>
        ${vo.totalCal} kcal /
        ${vo.totalCarb} g /
        ${vo.totalFat} g /
        ${vo.totalProt} g /
        ${vo.totalNa} mg
    </p>
    </div>
</c:if>
</div>

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
        	 window.location.href = '/ehr/foodDiary/doRetrieve.do?regDt=' + regDt;
        }
      },
      error: function(xhr, status, error) {
        alert('삭제 중 오류 발생: ' + error);
      }
    });
  }
</script>
      
      </main>
      <!--//main end-------------------->

      
 <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
   </div> 
</body>
</html>